package com.wssys.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.PusSysUser;
import com.wssys.entity.SysuserLoginlog;
@Repository
@Transactional
public class SysuserLoginlogDao extends BaseDao {
private Logger logger = LoggerFactory.getLogger(SysuserLoginlogDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public int save(SysuserLoginlog o) {
		return (Integer) sessionFactory.getCurrentSession().save(o);
	}
	
	public Page querySysuserLoginlogPagesByAccount(String account, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				SysuserLoginlog.class);
		if (!account.equals("")) {
			cri.add(Restrictions.eq("useraccount", account));
		}
		cri.addOrder(Order.desc("createtime"));
		return getQueryPage(cri, page);

	}
}
