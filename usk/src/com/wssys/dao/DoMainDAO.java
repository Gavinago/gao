package com.wssys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.Maildomains;
import com.wssys.entity.Sendmail;

@Repository
@Transactional
public class DoMainDAO extends BaseDao{
	private Logger logger = LoggerFactory.getLogger(DoMainDAO.class);
	@Autowired
	private SessionFactory sessionFactory;

	public int save(Maildomains rm) {
		return (Integer) sessionFactory.getCurrentSession().save(rm);

	}

	// 查询一个服务是否有重复的域名称
	public int checkDomainName(String domainName,int sid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Maildomains.class);
		criteria.add(Restrictions.and(Restrictions.eq("domainName", domainName), Restrictions.eq("sid", sid)));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

//	/**
//	 * 分页查询方法
//	 * 
//	 * @param cri
//	 *            ：查询条件对象
//	 * @param page
//	 *            ：分页对象
//	 * @return page:分页对象
//	 */
//	protected Page getQueryPage(Criteria cri, Page page) {
//
//		int count = ((Integer) cri.setProjection(Projections.rowCount())
//				.uniqueResult()).intValue();
//		cri.setProjection(null);
//		page.setSumcount(count);// 总记录数
//		int pagenum = count / page.getPagesize();
//		if (count % page.getPagesize() != 0)
//			pagenum += 1;
//		page.setPagecount(pagenum);// 总页数
//		int startNo = (page.getCurpage() - 1) * page.getPagesize();
////		int endNo = startNo + page.getPagesize();
//		int endNo =  page.getPagesize();
//
//		cri.setFirstResult(startNo);
//		cri.setMaxResults(endNo);
//
//		page.setResult(cri.list());
//		return page;
//
//	}

	public Page queryUserPages(Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Maildomains.class);
		return getQueryPage(cri, page);

	}

	public Page queryUserPagesByDomainName(String domainName,Integer sid, Page page,String sort,String order) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Maildomains.class);	//.createAlias("smail", "smails")
		if (!domainName.equals("")) {
			cri.add(Restrictions.eq("domainName", domainName));
		}
		if (sid!=null) {
			cri.add(Restrictions.eq("sid", sid));
		}
		
		if(sort.equals("domainDayuse")){
			if(order.equals("desc")){
				cri.addOrder(Order.desc("domainDayuse"));
			}else if(order.equals("asc")){
				cri.addOrder(Order.asc("domainDayuse"));
			}
		}
		
		
		return getQueryPage(cri, page);

	}

	public Maildomains getById(int id) {
		return (Maildomains) sessionFactory.getCurrentSession().get(
				Maildomains.class, id);
	}

	public void update(Maildomains smail) {
		sessionFactory.getCurrentSession().merge(smail);
	}

	public int batchDelete(String strid) {

		String hqlDelete = "delete from maildomains  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	public int batchDeletebysid(String strid) {

		String hqlDelete = "delete from maildomains  where sid in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	//根据发送邮件服务主键获得邮件域列表信息
	public List<Maildomains> getMaildomainsList(Integer sendmailid){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Maildomains.class).add(Restrictions.eq("sid", sendmailid));
		List<Maildomains> list=criteria.list();
		return list;
	}
	
	
	public void updateDomain_dayuse(int id){  
   
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update maildomains  set domain_dayuse=IFNULL(domain_dayuse,0)+1 where id ="+id);  
        query.executeUpdate();  
    }
	
	public void updateDomain_dayuseAll(){  
		   
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update maildomains  set domain_dayuse=0");  
        query.executeUpdate();  
    }
	
	public void resetDomain_dayuse(){  
		   
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update maildomains  set domain_dayuse=0");  
        query.executeUpdate();  
    }
}
