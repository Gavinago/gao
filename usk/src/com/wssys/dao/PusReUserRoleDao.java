package com.wssys.dao;

import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.entity.PusReUserRole;

@Repository
@Transactional
public class PusReUserRoleDao {
	private Logger logger = LoggerFactory.getLogger(PusReUserRoleDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public int save(PusReUserRole prru) {
		return (Integer) sessionFactory.getCurrentSession().save(prru);

	}

	/**
	 * 保存前先删除以前的关系
	 * 
	 * @param userid
	 * @return
	 */
	public int batchDelete(Integer userid) {
		String hqlDelete = "delete from pus_re_user_role  where  userid="
				+ userid + "";
		int deletedEntities = sessionFactory.getCurrentSession()
				.createSQLQuery(hqlDelete).executeUpdate();
		return deletedEntities;
	}
}
