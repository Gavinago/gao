package com.wssys.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.entity.PusReRoleMenu;
import com.wssys.entity.Urd;

@Repository
@Transactional
public class UrdDao {
	private Logger logger = LoggerFactory.getLogger(PusReRoleMenuDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	
	public int batchDelete(Integer userid) {

		String hqlDelete = "delete from urd  where userid="+userid;
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	public int save(Urd urd) {
		return (Integer) sessionFactory.getCurrentSession().save(urd);

	}
}
