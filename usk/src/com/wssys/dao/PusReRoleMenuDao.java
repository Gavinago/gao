package com.wssys.dao;

import java.math.BigInteger;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.entity.PusReRoleMenu;

@Repository
@Transactional
public class PusReRoleMenuDao {
	private Logger logger = LoggerFactory.getLogger(PusReRoleMenuDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public int save(PusReRoleMenu prrm) {
		return (Integer) sessionFactory.getCurrentSession().save(prrm);

	}
	
	public int haveprrmcount(Integer roleid,Integer menuid){
		String hql = "SELECT count(*) FROM pus_re_role_menu WHERE roleid="+roleid+" and menuid="+menuid+" and state=1";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
	
	public int batchDelete(Integer roleid) {

		String hqlDelete = "delete from pus_re_role_menu  where roleid="+roleid;
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	public int batchDelete(Integer roleid,Integer menuid) {

		String hqlDelete = "delete from pus_re_role_menu  where roleid="+roleid+" and mennuid="+menuid;
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
}
