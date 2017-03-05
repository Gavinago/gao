package com.wssys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.entity.Sendmail;

//*标签学习
//@Repository 注解便属于最先引入的一批，它用于将数据访问层 (DAO 层 ) 的类标识为 Spring Bean。
//具体只需将该注解标注在 DAO 类上即可。同时，为了让 Spring 能够扫描类路径中的类并识别出 @Repository 注解，
//需要在 XML 配置文件中启用 Bean 的自动扫描功能，这可以通过 <context:component-scan/>
//如此，我们就不再需要在 XML 中显式使用 <bean/> 进行 Bean 的配置。
//Spring 在容器初始化时将自动扫描 base-package 指定的包及其子包下的所有 class 文件，
//所有标注了 @Repository 的类都将被注册为 Spring Bean。
//为什么 @Repository 只能标注在 DAO 类上呢？这是因为该注解的作用不只是将类识别为 Bean，
//同时它还能将所标注的类中抛出的数据访问异常封装为 Spring 的数据访问异常类型。
//Spring 本身提供了一个丰富的并且是与具体的数据访问技术无关的数据访问异常结构，
//用于封装不同的持久层框架抛出的异常，使得异常独立于底层的框架。

//在service类前加上@Transactional，声明这个service所有方法需要事务管理。每一个业务方法开始时都会打开一个事务。
//如何改变默认规则： 
//
//1 让checked例外也回滚：在整个方法前加上 @Transactional(rollbackFor=Exception.class) 
//
//2 让unchecked例外不回滚： @Transactional(notRollbackFor=RunTimeException.class) 
//
//3 不需要事务管理的(只查询的)方法：@Transactional(propagation=Propagation.NOT_SUPPORTED) 
//在整个方法运行前就不会开启事务 
//
//还可以加上：@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)，这样就做成一个只读事务，可以提高效率。

//@Autowired  Spring 2.5 引入   它可以对类成员变量、方法及构造函数进行标注，完成自动装配的工作  
//这个注解就是spring可以自动帮你把bean里面引用的对象的setter/getter方法省略，它会自动帮你set/get。

@Repository
@Transactional
public class SMailDAO extends BaseDao{
	private Logger logger = LoggerFactory.getLogger(SMailDAO.class);
	@Autowired
	private SessionFactory sessionFactory;

	public int save(Sendmail sm) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Sendmail.class);

		// criteria.add(Restrictions.eq("mailAddress",sm.getMailAddress()));
		// //查询是否有重复的email地址
		// if(criteria.list().size()>0){
		// return 0;
		// }else{
		return (Integer) sessionFactory.getCurrentSession().save(sm);

	}

	// 查询是否有重复的email地址
	public int checkMailAddress(String mailAddress) {
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Sendmail.class);
		criteria.add(Restrictions.eq("mailAddress", mailAddress));
		if (criteria.list().size() > 0) {
			return 1;
		} else {
			return 0;
		}
	}

	

	public Page queryUserPages(Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Sendmail.class);
		return getQueryPage(cri, page);

	}

	public Page queryUserPagesBymailAddress(String mailAddress, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Sendmail.class);
		if (!mailAddress.equals("")) {
			cri.add(Restrictions.eq("mailAddress", mailAddress));
		}
		return getQueryPage(cri, page);

	}

	public Sendmail getById(int id) {
		return (Sendmail) sessionFactory.getCurrentSession().get(
				Sendmail.class, id);
	}

	public void update(Sendmail smail) {
		sessionFactory.getCurrentSession().merge(smail);
	}

	public int batchDelete(String strid) {

		String hqlDelete = "delete from sendmail  where id in("+strid+")";
		int deletedEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return deletedEntities;
	}
	
	public List<Sendmail> getSendmailList(){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Sendmail.class);
		List<Sendmail> list=criteria.list();
		return list;
	}
	
	public List<Sendmail> getSendmailListByTakId(Integer taskid){
		Criteria criteria = sessionFactory.getCurrentSession().createCriteria(
				Sendmail.class);
		if (null!=taskid) {
			criteria.add(Restrictions.eq("taskid", taskid));
		}
		List<Sendmail> list=criteria.list();
		return list;
	}
	
	public List queryListNoDomain() {

		String hql = "SELECT * FROM sendmail WHERE id NOT IN (SELECT sid FROM maildomains)";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return query.list();
	}
	
	public List queryListHaveDomain() {

		String hql = "SELECT * FROM sendmail WHERE id  IN (SELECT sid FROM maildomains)";
		Query query =sessionFactory.getCurrentSession().createSQLQuery(hql);
		return query.list();
	}
}
