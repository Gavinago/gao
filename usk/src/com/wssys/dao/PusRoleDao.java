package com.wssys.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.PusMenu;
import com.wssys.entity.PusRole;
import com.wssys.entity.PusRole_WithoutChild;
import com.wssys.entity.PusSysUser;
import com.wssys.entity.Receivingmail;
import com.wssys.utils.Finder;

@Repository
@Transactional
public class PusRoleDao extends BaseDao {
	private Logger logger = LoggerFactory.getLogger(PusRoleDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public int save(PusRole psu) {
		return (Integer) sessionFactory.getCurrentSession().save(psu);

	}
	
	public void update(PusRole pr) {
		sessionFactory.getCurrentSession().merge(pr);
	}
	
	public PusRole getById(int id) {
		return (PusRole) sessionFactory.getCurrentSession().get(
				PusRole.class, id);
	}

	// 查询是否有重复的
	public int checkRoleName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusRole.class);
		criteria.add(Restrictions.eq("name", name));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	
	public Page queryUserPagesByname(String name, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				PusRole_WithoutChild.class);
		if (!name.equals("")) {
			cri.add(Restrictions.eq("name", name));
		}
		
		cri.add(Restrictions.eq("state", 1));
		cri.addOrder(Order.desc("createtime"));
		return getQueryPage(cri, page);

	}
	
	public int batchDelete(String strid) {

		String hqlDelete = "delete from  pus_role  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	
	public List<PusRole> findRoleByUserId(Integer userid) throws Exception {
		if (userid == null) {
			return new ArrayList<PusRole>();
		}
//		Finder finder = new Finder(
//				"SELECT r.* from pus_role r,pus_re_user_role  re where re.userid=:userid and re.roleid=r.id");
		Finder finder = new Finder(
				"SELECT pr.* FROM pus_role pr ,pus_re_user_role prr "+
"WHERE pr.id=prr.roleid and pr.state=1 AND prr.userid="+userid);
		
		//finder.setParam("roleid", roleid);
		Query query = ((SQLQuery) sessionFactory.getCurrentSession()
				.createSQLQuery(finder.getSql())
				.setResultTransformer(Transformers.aliasToBean(PusRole.class)));
//		Query query = ((SQLQuery) sessionFactory.getCurrentSession()
//				.createSQLQuery(finder.getSql())
//				.setResultTransformer(Transformers.aliasToBean(PusMenu.class))).addScalar("state", Hibernate.SHORT)
//				.addScalar("type", Hibernate.SHORT).addScalar("id", Hibernate.INTEGER)
//				.addScalar("name", Hibernate.STRING)
//				.addScalar("menupid", Hibernate.INTEGER)
//				.addScalar("description", Hibernate.STRING)
//				.addScalar("pageurl", Hibernate.STRING)
//				.addScalar("createtime", Hibernate.TIMESTAMP);
//		Query query = sessionFactory.getCurrentSession()
//				.createSQLQuery(finder.getSql());
		//query.setInteger("roleid",roleid);
		return query.list();
	}
	
	
	public List<PusRole> getPusRoleList(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusRole.class);
		criteria.add(Restrictions.eq("state", 1));
		List<PusRole> list=criteria.list();
		return list;
	}
}
