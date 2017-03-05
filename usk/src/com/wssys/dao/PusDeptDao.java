package com.wssys.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.PusDeptBean;
import com.wssys.entity.PusDept;
import com.wssys.utils.Finder;
import com.wssys.utils.StringUtil;

@Repository
@Transactional
public class PusDeptDao extends BaseDao{
	private Logger logger = LoggerFactory.getLogger(PusMenuDao.class);
	
	@Autowired
	private SessionFactory sessionFactory;
	
	public PusDept getById(int id) {
		return (PusDept) sessionFactory.getCurrentSession().get(
				PusDept.class, id);
	}

	
	
	public int save(PusDept pmu) {
		return (Integer) sessionFactory.getCurrentSession().save(pmu);

	}
	
	public List<PusDept> getPusDeptList(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusDept.class);
		if (!StringUtil.stringIsNull(name).equals("")) {
			criteria.add(Restrictions.eq("name", name));
		}
		List<PusDept> list = criteria.list();
		return list;
	}
	
	
	public List<PusDept> getPusDeptList() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusDept.class);
		List<PusDept> list = criteria.list();
		return list;
	}
	
	// 查询是否有重复的
		public int checkPusDeptName(String name) {
			Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
					PusDept.class);
			criteria.add(Restrictions.eq("name", name));
			if (criteria.list().size() > 0) {
				return 1;
			} else {
				return 0;
			}
		}
		
		
		public int batchDelete(String strid) {

			String hqlDelete = "delete from   pus_dept   where id in(" + strid
					+ ")";
			int deletedEntities = sessionFactory.getCurrentSession()
					.createSQLQuery(hqlDelete).executeUpdate();
			return deletedEntities;
		}
		
		/**
		 * 是否有子部门
		 * @param id
		 * @return
		 */
		public int pidcount(Integer id){
			String hql = "SELECT COUNT(*) FROM pus_dept WHERE pid="+id;
			Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
			return ((BigInteger) query.uniqueResult()).intValue();
		}
		
		
		public void update(PusDept pd) {
			sessionFactory.getCurrentSession().merge(pd);
		}
		
		
		public List<PusDept> findPusDeptByUserid(Integer userid) throws Exception {
			if (userid == null) {
				return new ArrayList<PusDept>();
			}
			Finder finder = new Finder(
					"SELECT pd.* "+
" FROM pus_dept pd INNER JOIN urd r WHERE pd.id=r.deptid AND r.userid="
							+ userid);

			Query query = ((SQLQuery) sessionFactory.getCurrentSession()
					.createSQLQuery(finder.getSql())
					.setResultTransformer(Transformers.aliasToBean(PusDept.class)))
					.addScalar("deptgrade", Hibernate.INTEGER)
					.addScalar("id", Hibernate.INTEGER)
					.addScalar("name", Hibernate.STRING)
					.addScalar("pid", Hibernate.INTEGER)
					.addScalar("description", Hibernate.STRING)
					.addScalar("createtime", Hibernate.TIMESTAMP);
			return query.list();
		}
		
		
		public List<PusDeptBean> findPusDeptBeanByUserid(Integer userid)
				throws Exception {
			if (userid == null) {
				return new ArrayList<PusDeptBean>();
			}
			Finder finder = new Finder(
					"SELECT a.* FROM ( SELECT pd.*,rd.userid FROM pus_dept pd " +
					"LEFT JOIN urd rd ON (pd.id=rd.deptid) ) a LEFT JOIN pus_sys_user u ON" +
					" (a.userid=u.id) WHERE u.id="
							+ userid);

			Query query = ((SQLQuery) sessionFactory
					.getCurrentSession()
					.createSQLQuery(finder.getSql())
					.setResultTransformer(
							Transformers.aliasToBean(PusDeptBean.class)));
			return query.list();
		}
}
