package com.wssys.dao;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.DwhBean;
import com.wssys.entity.DeptReDwh;

@Repository
@Transactional
public class DeptReDwhDao {
	@Autowired
	private SessionFactory sessionFactory;
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public int save(DeptReDwh o) {
		return (Integer) sessionFactory.getCurrentSession().save(o);
	}

	public int batchDelete(Integer deptid) {

		String hqlDelete = "delete from dept_re_dwh  where deptid=" + deptid;
		int deletedEntities = sessionFactory.getCurrentSession()
				.createSQLQuery(hqlDelete).executeUpdate();
		return deletedEntities;
	}

	public List<DwhBean> findDwhNameBean(Integer deptid) {

	
		StringBuilder sbstr = new StringBuilder(
				"SELECT a.* FROM ( SELECT d.*,drd.deptid FROM dwh d LEFT JOIN " +
				"dept_re_dwh drd ON(d.id=drd.dwhid) ) a LEFT JOIN pus_dept pd " +
				"ON(pd.id=a.deptid) WHERE pd.id="+deptid);

		List<DwhBean> list = jdbcTemplate.query(sbstr.toString(),
				new BeanPropertyRowMapper<DwhBean>(DwhBean.class));
		return list;
	}

}
