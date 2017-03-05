package com.wssys.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
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
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.bean.PusFrontUserBean;
import com.wssys.bean.PusSysUserBean;
import com.wssys.entity.Mailclient;
import com.wssys.entity.PusMenu;
import com.wssys.entity.PusMenuSingle;
import com.wssys.entity.PusRole;
import com.wssys.entity.PusRole_WithoutChild;
import com.wssys.entity.PusSysUser;
import com.wssys.utils.Finder;
import com.wssys.utils.StringUtil;

@Repository
@Transactional
public class PusSysUserDao extends BaseDao {
	private Logger logger = LoggerFactory.getLogger(PusSysUserDao.class);

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(PusSysUser psu) {
		return (Integer) sessionFactory.getCurrentSession().save(psu);

	}

	// 查询是否有重复的account
	public int checkAccount(String account) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				PusSysUser.class);
		criteria.add(Restrictions.eq("account", account));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	public PusSysUser getById(int id) {
		return (PusSysUser) sessionFactory.getCurrentSession().get(
				PusSysUser.class, id);
	}

	public void update(PusSysUser pus) {
		sessionFactory.getCurrentSession().merge(pus);
	}

	public Page queryUserPages(Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				PusSysUser.class);
		return getQueryPage(cri, page);

	}

	@RequiresAuthentication
	@RequiresPermissions("/viewAllUserJson.do")
	public Page queryUserPagesByAccount(String account, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				PusSysUser.class);
		if (!account.equals("")) {
			cri.add(Restrictions.eq("account", account));
		}
	
		cri.add(Restrictions.eq("state", 1));
		return getQueryPage(cri, page);

	}
	
	public List<PusSysUserBean> findPusSysUserBeanListBean(String account, Page page){

		int startNo = (page.getCurpage() - 1) * page.getPagesize();
		int endNo = page.getPagesize(); // 查几条
		

		
		StringBuilder sbstr = new StringBuilder(
				"SELECT u.*,r.`name` rolename FROM pus_sys_user u LEFT JOIN pus_re_user_role ur " +
				"ON u.`id`=ur.`userid` LEFT JOIN pus_role r ON r.`id`=ur.`roleid`" +
				" where 1=1 ");
		if (!StringUtil.stringIsNull(account).equals("")) {
			sbstr.append(" AND u.account='" + account + "'");
		}
		
		sbstr.append(" order by u.createtime desc");
		sbstr.append(" limit " + startNo + "," + endNo);

		System.out.println(sbstr.toString());
		
		List<PusSysUserBean> list = jdbcTemplate.query(sbstr.toString(),
				new BeanPropertyRowMapper<PusSysUserBean>(PusSysUserBean.class));
		return list;
	}

	public PusSysUser findByaccount(String configkey) {
		String hql = "from PusSysUser as i where i.account=?";
		Query query = sessionFactory.getCurrentSession().createQuery(hql);
		query.setString(0, configkey);
		ArrayList list = (ArrayList) query.list(); // 返回的是一个集合
		return (PusSysUser) list.get(0);
	}

	public List<PusRole> findRoleByUserId(Integer userid) throws Exception {
		if (userid == null) {
			return null;
		}
		Finder finder = new Finder(
				"SELECT r.* from pus_role r,pus_re_user_role  re where re.userid=:userid and re.roleid=r.id");
		//finder.setParam("userid", userid);
		Query query = ((SQLQuery) sessionFactory.getCurrentSession()
				.createSQLQuery(finder.getSql())
				.setResultTransformer(Transformers.aliasToBean(PusRole.class)));
		//.addScalar("userid", Hibernate.INTEGER)
		query.setInteger("userid", userid);
		return query.list();
	}

	public Set<String> getRolesAsString(Integer userId) throws Exception {
		List<PusRole> list = findRoleByUserId(userId);
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (PusRole r : list) {
//			set.add(r.getCode());
			set.add(r.getName());
		}
		return set;
	}

	public List<PusMenuSingle> findMenuByRoleId(Integer roleid) throws Exception {
		if (roleid == null) {
			return null;
		}
		Finder finder = new Finder(
				"SELECT m.* FROM pus_menu m,pus_re_role_menu  re WHERE re.roleId=:roleid AND re.menuid=m.id  ");
		//finder.setParam("roleid", roleid);
		Query query = ((SQLQuery) sessionFactory.getCurrentSession()
				.createSQLQuery(finder.getSql())
				.setResultTransformer(Transformers.aliasToBean(PusMenuSingle.class))).addScalar("state", Hibernate.SHORT)
				.addScalar("type", Hibernate.SHORT).addScalar("id", Hibernate.INTEGER)
				.addScalar("name", Hibernate.STRING)
				.addScalar("menupid", Hibernate.INTEGER)
				.addScalar("description", Hibernate.STRING)
				.addScalar("pageurl", Hibernate.STRING)
				.addScalar("createtime", Hibernate.TIMESTAMP);
		query.setParameter("roleid", roleid);
		return query.list();
	}

	private List<PusMenuSingle> findAllMenuByUserId(Integer userId) throws Exception {
		if (userId == null) {
			return null;
		}

		List<PusRole> roles = findRoleByUserId(userId);
		if (CollectionUtils.isEmpty(roles)) {
			return null;
		}
		List<PusMenuSingle> list = new ArrayList<PusMenuSingle>();
		for (PusRole role : roles) {
			List<PusMenuSingle> menus = findMenuByRoleId(role.getId());
			if (CollectionUtils.isEmpty(menus)) {
				continue;
			}
			list.addAll(menus);
		}

		return list;

	}

	public Set<String> getPermissionsAsString(Integer userId) throws Exception {
		List<PusMenuSingle> setMenu = findAllMenuByUserId(userId);
		if (CollectionUtils.isEmpty(setMenu)) {
			return null;
		}
		Set<String> set = new HashSet<String>();
		for (PusMenuSingle m : setMenu) {
			if (StringUtils.isBlank(m.getPageurl())) {
				continue;
			}
			set.add(m.getPageurl());
		}
		return set;
	}
	
	
	public int batchDelete(String strid) {

		String hqlDelete = "delete from   pus_sys_user  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	
	public int batchresetPwd(String strid,String newpwd) {

		String hqlDelete = "update  pus_sys_user set pwd='"+newpwd+"'  where id in("+strid+")";
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return updateEntities;
	}
	
	public int batchresetPwd(Integer id,String newpwd) {

		String hqlDelete = "update  pus_sys_user set pwd='"+newpwd+"'  where id="+id;
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return updateEntities;
	}
	
	/**
	 * 设置接受手机短信通知用户
	 * @param strid
	 * @return
	 */
	public int batchSetAcceptUser(String strid,Integer isAcceptedSms) {

		String hqlDelete = "update  pus_sys_user set isAcceptedSms="+isAcceptedSms+"  where id in("+strid+")";
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return updateEntities;
	}
	
	
	/**
	 * 到款通知
	 * @param strid
	 * @param isAcceptedSms
	 * @return
	 */
	public int batchSetAcceptDkUser(String strid,Integer isAcceptedDk) {

		String hqlDelete = "update  pus_sys_user set isAcceptedDk="+isAcceptedDk+"  where id in("+strid+")";
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return updateEntities;
	}
	
	/**
	 * 出库通知
	 * @param strid
	 * @param isAcceptedSms
	 * @return
	 */
	public int batchSetAcceptCkUser(String strid,Integer isAcceptedCk) {

		String hqlDelete = "update  pus_sys_user set isAcceptedCk="+isAcceptedCk+"  where id in("+strid+")";
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return updateEntities;
	}
}
