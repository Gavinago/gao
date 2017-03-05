package com.wssys.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.ExpenseManagement;

@Repository
@Transactional
public class ExpenseManagementDao extends BaseDao {
	private Logger logger = LoggerFactory.getLogger(ExpenseManagementDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public Page queryEMPagesByname(String expensename, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				ExpenseManagement.class);
		if (!expensename.equals("")) {
			cri.add(Restrictions.eq("expensename", expensename));
		}

		return getQueryPage(cri, page);

	}

	// 查询是否有重复的
	public int checkEmName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				ExpenseManagement.class);
		criteria.add(Restrictions.eq("expensename", name));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}
	
	public ExpenseManagement getFyByExpensename(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				ExpenseManagement.class);
		criteria.add(Restrictions.eq("expensename", name));
		if (criteria.list().size() > 0) {
			return (ExpenseManagement)criteria.list().get(0);
		} else {
			return new ExpenseManagement();
		}
	}
	
	public ExpenseManagement getById(int id) {
		return (ExpenseManagement) sessionFactory.getCurrentSession().get(
				ExpenseManagement.class, id);
	}
	
	public void update(ExpenseManagement em) {
		sessionFactory.getCurrentSession().merge(em);
	}

	public int save(ExpenseManagement em) {
		return (Integer) sessionFactory.getCurrentSession().save(em);
	}
	
	public int batchDelete(String strid) {

		String hqlDelete = "delete from     expense_management  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
}
