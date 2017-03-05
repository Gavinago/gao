package com.wssys.dao;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.FetchMode;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.Mailclient;
import com.wssys.entity.PusMenu;
import com.wssys.entity.PusMenuSingle;
import com.wssys.entity.PusMenub;
import com.wssys.entity.PusRole;
import com.wssys.entity.PusSysUser;
import com.wssys.utils.Finder;
import com.wssys.utils.StringUtil;

@Repository
@Transactional
public class PusMenuDao extends BaseDao {
	private Logger logger = LoggerFactory.getLogger(PusMenuDao.class);
	@Autowired
	private SessionFactory sessionFactory;
	

	public PusMenu getById(int id) {
		return (PusMenu) sessionFactory.getCurrentSession().get(PusMenu.class,
				id);
	}

	

	public int save(PusMenu pmu) {
		return (Integer) sessionFactory.getCurrentSession().save(pmu);

	}

	// 查询是否有重复的
	public int checkMenuName(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusMenu.class);
		criteria.add(Restrictions.eq("name", name));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public Page queryMenuPagesByname(String name, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				PusMenuSingle.class);
		if (!name.equals("")) {
			cri.add(Restrictions.eq("name", name));
		}
		short s = 1;
		cri.add(Restrictions.eq("state", s));
		return getQueryPage(cri, page);

	}

	public void update(PusMenu pm) {
		sessionFactory.getCurrentSession().merge(pm);
	}

	public List<PusMenu> getPusMenuList() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusMenu.class);
		List<PusMenu> list = criteria.list();
		return list;
	}

	public List<PusMenu> getPusMenuList(String name) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusMenu.class);
		if (!StringUtil.stringIsNull(name).equals("")) {
			//criteria.add(Restrictions.eq("name", name));	//精确查询
			// Restrictions.like(属性名, 查询条件的值, 匹配方式)：
			criteria.add(Restrictions.like("name", name, MatchMode.ANYWHERE));	//模糊查询 忽略大小写
			
		}
		List<PusMenu> list = criteria.list();
		return list;
	}

	public List<PusMenu> getPusLeftMenuList() {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusMenu.class);
		short s = 1;
		criteria.add(Restrictions.eq("type", s));
		List<PusMenu> list = criteria.list();
		return list;
	}
	
	public List<PusMenu> CriteriafindMenuByUserId(Integer userid) {
		//目前还没实现
		short s = 1;
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusMenu.class);
		 Criteria criteria1 = criteria.createAlias("pusReRoleMenu", "rolemenu",CriteriaSpecification.INNER_JOIN);
	        Criteria criteria2 = criteria1.createAlias("pusReUserRole", "ur",CriteriaSpecification.INNER_JOIN);
	        criteria2.add(Restrictions.eq("ur.userid", userid));
	        criteria.add(Restrictions.eq("type", s));
		List<PusMenu> list = criteria.list();
		return list;
	}
	
	public List<PusMenu> HqlfindMenuByUserId(Integer userid) {
		//
		String hql = "SELECT menu FROM PusMenu menu, PusReRoleMenu rolemenu , " +
				" PusReUserRole ur WHERE menu.id=rolemenu.menuid AND" +
				" rolemenu.roleid=ur.roleid AND ur.userid=6 AND menu.type=1";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		List<PusMenu> list = query.list();
		return list;
	}

	public List<PusMenu> findMenuByUserId(Integer userid) throws Exception {
		if (userid == null) {
			return new ArrayList<PusMenu>();
		}
		Finder finder = new Finder(
				"SELECT menu.* FROM pus_menu menu,pus_re_role_menu rolemenu, "
						+ "pus_re_user_role ur WHERE menu.`id`=rolemenu.`menuid` AND"
						+ " rolemenu.`roleid`=ur.`roleid` AND ur.`userid`="
						+ userid + " AND menu.`type`=1 order by sortfiled ");

		Query query = ((SQLQuery) sessionFactory.getCurrentSession()
				.createSQLQuery(finder.getSql())
				.setResultTransformer(Transformers.aliasToBean(PusMenu.class)))
				.addScalar("state", Hibernate.SHORT)
				.addScalar("type", Hibernate.SHORT)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("name", Hibernate.STRING)
				.addScalar("menupid", Hibernate.INTEGER)
				.addScalar("description", Hibernate.STRING)
				.addScalar("pageurl", Hibernate.STRING)
				.addScalar("createtime", Hibernate.TIMESTAMP)
				;
		return query.list();
	}

	public List<PusMenu> findMenuByRoleId(Integer roleid) throws Exception {
		if (roleid == null) {
			return new ArrayList<PusMenu>();
		}
		// Finder finder = new Finder(
		// "SELECT r.* from pus_role r,pus_re_user_role  re where re.userid=:userid and re.roleid=r.id");
		Finder finder = new Finder(
				"SELECT pm.`id`, pm.`name`, pm.`menupid`, pm.`description`,pm.`pageurl`,pm.`type`,pm.`state`,pm.`createtime` FROM pus_menu pm INNER JOIN pus_re_role_menu prm WHERE pm.id=prm.menuid AND prm.roleid="
						+ roleid);

		// finder.setParam("roleid", roleid);
		Query query = ((SQLQuery) sessionFactory.getCurrentSession()
				.createSQLQuery(finder.getSql())
				.setResultTransformer(Transformers.aliasToBean(PusMenu.class)))
				.addScalar("state", Hibernate.SHORT)
				.addScalar("type", Hibernate.SHORT)
				.addScalar("id", Hibernate.INTEGER)
				.addScalar("name", Hibernate.STRING)
				.addScalar("menupid", Hibernate.INTEGER)
				.addScalar("description", Hibernate.STRING)
				.addScalar("pageurl", Hibernate.STRING)
				.addScalar("createtime", Hibernate.TIMESTAMP);
		// Query query = sessionFactory.getCurrentSession()
		// .createSQLQuery(finder.getSql());
		// query.setInteger("roleid",roleid);
		return query.list();
	}

	public int batchDelete(String strid) {

		String hqlDelete = "delete from   pus_menu   where id in(" + strid
				+ ")";
		int deletedEntities = sessionFactory.getCurrentSession()
				.createSQLQuery(hqlDelete).executeUpdate();
		return deletedEntities;
	}
	
	
	/**
	 * 是否有子菜单
	 * @param id
	 * @return
	 */
	public int pidcount(Integer id){
		String hql = "SELECT COUNT(*) FROM pus_menu WHERE menupid="+id;
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return ((BigInteger) query.uniqueResult()).intValue();
	}
	
}
