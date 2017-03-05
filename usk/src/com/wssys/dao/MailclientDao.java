package com.wssys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.Mailclient;

@Repository
@Transactional
public class MailclientDao extends BaseDao{
	@Autowired
	private SessionFactory sessionFactory;
	
	public int save(Mailclient rm) {
		return (Integer) sessionFactory.getCurrentSession().save(rm);

	}

	// 查询是否有重复的clintname
	public int checkclintname(String clintname) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Mailclient.class);
		criteria.add(Restrictions.eq("clintname", clintname));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	

	public Page queryUserPages(Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Mailclient.class);
		return getQueryPage(cri, page);

	}

	public Page queryUserPagesByclintname(String mailAddress, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Mailclient.class);
		if (!mailAddress.equals("")) {
			cri.add(Restrictions.eq("clintname", mailAddress));
		}
		return getQueryPage(cri, page);

	}

	public Mailclient getById(int id) {
		return (Mailclient) sessionFactory.getCurrentSession().get(
				Mailclient.class, id);
	}

	public void update(Mailclient smail) {
		sessionFactory.getCurrentSession().merge(smail);
	}

	public int batchDelete(String strid) {

		String hqlDelete = "delete from mailclient  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	public List<Mailclient> getMailclientList(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Mailclient.class);
		List<Mailclient> list=criteria.list();
		return list;
	}
}
