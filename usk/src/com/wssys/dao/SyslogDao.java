package com.wssys.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.Syslog;
@Repository
@Transactional
public class SyslogDao extends BaseDao{
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;
	
	public Syslog getById(int id) {
		return (Syslog) sessionFactory.getCurrentSession().get(Syslog.class, id);
	}
	
	public int save(Syslog o) {
		return (Integer) sessionFactory.getCurrentSession().save(o);
	}
	
	public Page querySysLogPagesByAccount(String account, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Syslog.class);
		if (!account.equals("")) {
			cri.add(Restrictions.eq("loginName", account));
		}
		cri.addOrder(Order.desc("createtime"));
		return getQueryPage(cri, page);

	}
}
