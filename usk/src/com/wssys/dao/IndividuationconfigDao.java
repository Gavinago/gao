package com.wssys.dao;

import java.util.ArrayList;

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
import com.wssys.entity.Individuationconfig;
import com.wssys.entity.Maildomains;
import com.wssys.entity.Receivingmail;

@Repository
@Transactional
public class IndividuationconfigDao extends BaseDao {
	private Logger logger = LoggerFactory.getLogger(IndividuationconfigDao.class);
	@Autowired
	private SessionFactory sessionFactory;

	public int save(Individuationconfig ic) {
		return (Integer) sessionFactory.getCurrentSession().save(ic);

	}

	public Individuationconfig findByconfigkey(String configkey) {
		String hql = "from Individuationconfig as i where i.configkey=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, configkey);
		ArrayList list = (ArrayList) query.list(); // 返回的是一个集合
		return (Individuationconfig) list.get(0);
	}

	public int batchDelete(String strid) {

		String hqlDelete = "delete from individuationconfig  where id in("
				+ strid + ")";
		int deletedEntities = sessionFactory.getCurrentSession()
				.createSQLQuery(hqlDelete).executeUpdate();
		return deletedEntities;
	}

	public Individuationconfig getById(int id) {
		return (Individuationconfig) sessionFactory.getCurrentSession().get(
				Individuationconfig.class, id);
	}

	// 查询一个服务是否有重复的配置名称
	public int checkIcName(String IcName) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Individuationconfig.class);
		criteria.add(Restrictions.eq("configkey", IcName));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public Page queryUserPages(Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Individuationconfig.class);

		return getQueryPage(cri, page);

	}

	public void update(Individuationconfig ic) {
		sessionFactory.getCurrentSession().merge(ic);
	}
}
