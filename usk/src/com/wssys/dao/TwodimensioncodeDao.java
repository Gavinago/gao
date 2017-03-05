package com.wssys.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.entity.Twodimensioncode;

@Repository
@Transactional
public class TwodimensioncodeDao {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Autowired
	private SessionFactory sessionFactory;
	
	public Twodimensioncode getById(int id) {
		return (Twodimensioncode) sessionFactory.getCurrentSession().get(Twodimensioncode.class, id);
	}
	
	public int save(Twodimensioncode o) {
		return (Integer) sessionFactory.getCurrentSession().save(o);
	}
	
	public int zfoldTwodimensioncode(Integer goodsid) {
		String hqlDelete = "UPDATE twodimensioncode SET state=0 WHERE goodsid='"+goodsid+"'";
		int okEntities = sessionFactory.getCurrentSession().createSQLQuery(hqlDelete)
				.executeUpdate();
		return okEntities;
	}
	
	/**
	 * 更新二维码货物关系
	 * @param cdid
	 * @param goodsid
	 * @return
	 */
	public int updateGoodsByTwodimensioncodeID(Integer twoDimensionCodeid, Integer goodsid) {

		String hqlDelete = "UPDATE goods SET twoDimensionCodeid="+twoDimensionCodeid+" WHERE id="+goodsid;
		int updateEntities = sessionFactory.getCurrentSession()
				.createSQLQuery(hqlDelete).executeUpdate();
		return updateEntities;
	}
}
