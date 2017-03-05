package com.wssys.dao;

import java.math.BigInteger;
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
import com.wssys.entity.Mailclient;
import com.wssys.entity.Maildomains;
import com.wssys.entity.Mailreceipt;
import com.wssys.entity.Receivingmail;
@Repository
@Transactional
public class MailreceiptDao extends BaseDao{
	private Logger logger = LoggerFactory.getLogger(MailreceiptDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public Mailreceipt getById(int id) {
		return (Mailreceipt) sessionFactory.getCurrentSession().get(
				Mailreceipt.class, id);
	}
	
	public int save(Mailreceipt mr) {
		return (Integer) sessionFactory.getCurrentSession().save(mr);

	}
	
	public Page queryUserPagesByrmail(String rmailaddress, String uniquemailid,String batchnumber,Integer status,Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Mailreceipt.class).addOrder(Order.desc("sendtime"));
		if (!rmailaddress.equals("")) {
			cri.add(Restrictions.eq("rmailaddress", rmailaddress));
		}
		if (!uniquemailid.equals("")) {
			cri.add(Restrictions.eq("uniquemailid", uniquemailid));
		}
		if (!batchnumber.equals("")) {
			cri.add(Restrictions.eq("batchnumber", batchnumber));
		}
		if (status!=null) {
			cri.add(Restrictions.eq("status", status));
		}
		return getQueryPage(cri, page);
	}
	
	public Page queryUserPagesBybatchnumber(String batchnumber,Page page) {
		String hql = "";
		if(batchnumber.equals("")){
			hql = "SELECT batchnumber, COUNT(DISTINCT batchnumber) FROM mailreceipt WHERE batchnumber IS NOT NULL  GROUP BY batchnumber";
		}else{
			hql = "SELECT batchnumber, COUNT(DISTINCT batchnumber) FROM mailreceipt WHERE batchnumber IS NOT NULL and batchnumber='"+batchnumber+"' GROUP BY batchnumber";
		}
		 
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);

		
		return getQueryPage(query, page);
	}
	
	
	public List queryList() {

		String hql = "SELECT batchnumber, COUNT(DISTINCT batchnumber) FROM mailreceipt WHERE batchnumber IS NOT NULL GROUP BY batchnumber";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return query.list();
	}
	
	
	public int sumsendcount(String batchnumber){
		String hql = "SELECT count(*) FROM mailreceipt WHERE batchnumber='"+batchnumber+"'";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return ((BigInteger) query.uniqueResult()).intValue();
		//return  query.list().size();
	}
	
}
