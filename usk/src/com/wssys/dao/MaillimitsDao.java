package com.wssys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.Maillimits;
import com.wssys.entity.Mailreceipt;

import java.math.*;

@Repository
@Transactional
public class MaillimitsDao  extends BaseDao{
private Logger logger = LoggerFactory.getLogger(MaillimitsDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public int save(Maillimits ml) {
		return (Integer) sessionFactory.getCurrentSession().save(ml);

	}
	
	
	public Page queryUserPagesBybatchnumber(String batchnumber,String uniquenum, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Maillimits.class).addOrder(Order.desc("opentime"));
		if (!batchnumber.equals("")) {
			cri.add(Restrictions.eq("batchnumber", batchnumber));
		}
		if (!uniquenum.equals("")) {
			cri.add(Restrictions.eq("smailId", uniquenum));
		}
		return getQueryPage(cri, page);
	}
	
	public int firsopencount(String batchnumber){
		String hql = "SELECT COUNT(*) firstopencount FROM (SELECT * FROM (SELECT * FROM maillimits ORDER BY opentime ASC) biaoming WHERE batchnumber='"+batchnumber+"' GROUP BY smail_id ORDER BY opentime ASC) bm";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return ((BigInteger) query.uniqueResult()).intValue();
		//return  query.list().size();
	}
	
	public int sumopencount(String batchnumber){
		String hql = "SELECT count(*) FROM maillimits WHERE batchnumber='"+batchnumber+"'";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
	
	public List<Maillimits> queryList(String batchnumber) {

//		String hql = "SELECT * FROM maillimits WHERE batchnumber='"+batchnumber+"'";
//		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
//		return query.list();
		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Maillimits.class);
		if (!batchnumber.equals("")) {
			cri.add(Restrictions.eq("batchnumber", batchnumber));
		}
		return cri.list();
	}
	
	
	public int queryListCountbybatchnumber(String batchnumber) {

		String hql = "SELECT count(*) FROM maillimits WHERE batchnumber='"+batchnumber+"'";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return ((BigInteger) query.uniqueResult()).intValue();
	}
}
