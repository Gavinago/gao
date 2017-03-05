package com.wssys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.ProjectionList;
import org.hibernate.criterion.Projections;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;

@Repository
@Transactional
public class BaseDao {
	
	
	
	/**
	 * 分页查询方法
	 * 
	 * @param cri
	 *            ：查询条件对象
	 * @param page
	 *            ：分页对象
	 * @return page:分页对象
	 */
	protected Page getQueryPage(Criteria cri, Page page) {

		int count = ((Integer) cri.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
		cri.setProjection(null);
		page.setSumcount(count);// 总记录数
		int pagenum = count / page.getPagesize();
		if (count % page.getPagesize() != 0)
			pagenum += 1;
		page.setPagecount(pagenum);// 总页数
		int startNo = (page.getCurpage() - 1) * page.getPagesize();
//		int endNo = startNo + page.getPagesize();
		int endNo = page.getPagesize();	//查几条

		cri.setFirstResult(startNo);
		cri.setMaxResults(endNo);

		page.setResult(cri.list());
		return page;

	}
	
	/**
	 * 分页查询方法
	 * 
	 * @param cri
	 *            ：查询条件对象
	 * @param page
	 *            ：分页对象
	 * @return page:分页对象
	 */
	protected Page getQueryPage(Criteria cri, Page page,ProjectionList proList) {

		int count = ((Integer) cri.setProjection(Projections.rowCount())
				.uniqueResult()).intValue();
		cri.setProjection(proList);
		page.setSumcount(count);// 总记录数
		int pagenum = count / page.getPagesize();
		if (count % page.getPagesize() != 0)
			pagenum += 1;
		page.setPagecount(pagenum);// 总页数
		int startNo = (page.getCurpage() - 1) * page.getPagesize();
//		int endNo = startNo + page.getPagesize();
		int endNo = page.getPagesize();	//查几条

		cri.setFirstResult(startNo);
		cri.setMaxResults(endNo);

		page.setResult(cri.list());
		return page;

	}
	
	/**
	 * 分页查询方法
	 * 
	 * @param cri
	 *            ：查询条件对象
	 * @param page
	 *            ：分页对象
	 * @return page:分页对象
	 */
	protected Page getQueryPage(Query q, Page page) {

		int count =  q.list().size();
		page.setSumcount(count);// 总记录数
		int pagenum = count / page.getPagesize();
		if (count % page.getPagesize() != 0)
			pagenum += 1;
		page.setPagecount(pagenum);// 总页数
		int startNo = (page.getCurpage() - 1) * page.getPagesize();
//		int endNo = startNo + page.getPagesize();
		int endNo = page.getPagesize();	//查几条

		q.setFirstResult(startNo);
		q.setMaxResults(endNo);

		page.setResult(q.list());
		return page;

	}
	
	
//	/**
//	 * 分页查询方法
//	 * 
//	 * @param cri
//	 *            ：查询条件对象
//	 * @param page
//	 *            ：分页对象
//	 * @return page:分页对象
//	 */
//	protected Page getQueryPage(List list, Page page) {
//		
//		int count =  list.size();
//		page.setSumcount(count);// 总记录数
//		int pagenum = count / page.getPagesize();
//		if (count % page.getPagesize() != 0)
//			pagenum += 1;
//		page.setPagecount(pagenum);// 总页数
//		int startNo = (page.getCurpage() - 1) * page.getPagesize();

//		int endNo = page.getPagesize();	//查几条
//
////		q.setFirstResult(startNo);
////		q.setMaxResults(endNo);
//		List sub = list.subList(startNo, endNo);
//		
//		
//		page.setResult(sub);
//		return page;
//
//	}
	

}
