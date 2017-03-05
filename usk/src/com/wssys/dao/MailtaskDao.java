package com.wssys.dao;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.entity.Mailtask;

@Repository
@Transactional
public class MailtaskDao {
	private Logger logger = LoggerFactory.getLogger(MailtaskDao.class);

	@Autowired
	private SessionFactory sessionFactory;

	public int save(Mailtask mt) {
		return (Integer) sessionFactory.getCurrentSession().save(mt);

	}

	public void deleteAll() {
		Query q = sessionFactory.getCurrentSession().createQuery(
				"delete from Mailtask");
		q.executeUpdate();
	}

	public Mailtask getMailtask() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Mailtask");
		if(query.list().size()>0){
			Mailtask mt = (Mailtask) query.list().get(0);
			return mt;
		}else{
			return null;
		}
		
	}
	
	public void updateMailtaskSented(){  
		   
        Query query = sessionFactory.getCurrentSession().createSQLQuery("update mailtask  set sented=IFNULL(sented,0)+1 ");  
        query.executeUpdate();  
    }
}
