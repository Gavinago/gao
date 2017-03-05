package com.wssys.dao;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.Htmltemp;

@Repository
@Transactional
public class HtmltempDao extends BaseDao {
	@Autowired
	private SessionFactory sessionFactory;

	// 查询是否有重复的clintname 判断
	public int checktempname(String tempname) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Htmltemp.class);
		criteria.add(Restrictions.eq("tempname", tempname));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public int save(Htmltemp rm) {
		return (Integer) sessionFactory.getCurrentSession().save(rm);

	}

	public Htmltemp getById(int id) {
		return (Htmltemp) sessionFactory.getCurrentSession().get(
				Htmltemp.class, id);
	}

	public void delete(int id) {
		Htmltemp c = getById(id);
		sessionFactory.getCurrentSession().delete(c);

	}

	public Page queryHtmltempPagesBytempname(String tempname, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Htmltemp.class);
		if (!tempname.equals("")) {
			cri.add(Restrictions.eq("tempname", tempname));
		}
		return getQueryPage(cri, page);

	}
	
	public int batchDelete(String strid) {

		String hqlDelete = "delete from htmltemp  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
}
