package com.wssys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.FetchMode;
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
import com.wssys.entity.Receivingmail;
import com.wssys.entity.Sendmail;

@Repository
@Transactional
public class RMailDAO extends BaseDao{
	private Logger logger = LoggerFactory.getLogger(RMailDAO.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public int save(Receivingmail o) {
		return (Integer) sessionFactory.getCurrentSession().save(o);
	}

	// 查询是否有重复的email地址
	public int checkMailAddress(String mailAddress,Integer cid) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Receivingmail.class);
		criteria.add(Restrictions.eq("mailAddress", mailAddress));
		criteria.add(Restrictions.eq("cid", cid));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	

	public Page queryUserPages(Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Receivingmail.class);
		return getQueryPage(cri, page);

	}

	public Page queryUserPagesBymailAddress(String mailAddress,Integer cid, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Receivingmail.class).setFetchMode("mc", FetchMode.JOIN);
		if (!mailAddress.equals("")) {
			cri.add(Restrictions.eq("mailAddress", mailAddress));
		}
		if (cid!=null) {
			cri.add(Restrictions.eq("cid", cid));
		}
		cri.addOrder(Order.desc("createtime"));
		return getQueryPage(cri, page);

	}

	public Receivingmail getById(int id) {
		return (Receivingmail) sessionFactory.getCurrentSession().get(
				Receivingmail.class, id);
	}

	public void update(Receivingmail smail) {
		sessionFactory.getCurrentSession().merge(smail);
		
		
	}

	public int batchDelete(String strid) {

		String hqlDelete = "delete from receivingmail  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	public List<Receivingmail> getReceivingmailList(Integer cid){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Receivingmail.class).add(Restrictions.eq("cid", cid));
		List<Receivingmail> list=criteria.list();
		return list;
	}
}
