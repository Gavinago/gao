package com.wssys.dao;

import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Disjunction;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.wssys.bean.Page;
import com.wssys.bean.PusFrontUserBean;
import com.wssys.bean.StudentBean;
import com.wssys.entity.Studentinfos;
import com.wssys.utils.StringUtil;

@Repository
@Transactional
public class StudentinfosDao extends BaseDao {
	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Page queryStudentinfosPagesByname(String name, Integer age,
			Integer sex, String origin, String nation, String syfl, Page page) {

		Criteria cri = sessionFactory.getCurrentSession().createCriteria(
				Studentinfos.class);
		if (!name.equals("")) {
			cri.add(Restrictions.eq("name", name));
		}
		if (null != age) {
			cri.add(Restrictions.eq("age", age));
		}
		if (null != sex) {
			cri.add(Restrictions.eq("sex", sex));
		}
		if (!origin.equals("")) {
			cri.add(Restrictions.like("origin", origin, MatchMode.ANYWHERE));
		}
		if (!nation.equals("")) {
			if(nation.equals("0")){
				cri.add(Restrictions.eq("nation", "汉族"));
			}else if(nation.equals("1")){
				cri.add(Restrictions.ne("nation", "汉族"));
			}
			
		}
		if (!syfl.equals("")) {
			if (syfl.equals("1")) { // 本区学生信息查询(同安区）
				cri.add(Restrictions.like("origin", "同安区", MatchMode.ANYWHERE));
			} else if (syfl.equals("2")) { // 本市内非本区学生信息查询
											// 厦门市思明区、厦门市湖里区、厦门市集美区、厦门市海沧区、厦门市翔安区
				Disjunction dis = Restrictions.disjunction();
				dis.add(Restrictions.like("origin", "厦门市思明区",
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("origin", "厦门市湖里区",
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("origin", "厦门市集美区",
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("origin", "厦门市海沧区",
						MatchMode.ANYWHERE));
				dis.add(Restrictions.like("origin", "厦门市翔安区",
						MatchMode.ANYWHERE));
				cri.add(dis);
			} else if (syfl.equals("3")) { // 省内非本市学生信息查询 福建省除厦门市外其他市区
			// cri.add(Restrictions.like("origin", "福建省",
			// MatchMode.START)).add(arg0);
			// cri.add(Restrictions.le(propertyName, value)("origin", "同安区",
			// MatchMode.START));
				// origin like '福建省%' and
				cri.add(Restrictions
						.sqlRestriction("(origin like '福建省%' and origin not like '%厦门市%' )"));
				// cri.add(
				// Restrictions.disjunction()
				// .add((Restrictions.sqlRestriction("(origin like '福建省%' and origin not like '%厦门市%' )"))
				// )
				//
				// );
			} else if (syfl.equals("4")) { // 省外学生信息查询
				cri.add(Restrictions.sqlRestriction("origin not like '%福建省%' "));
			}

		}
		cri.addOrder(Order.desc("createdate"));
		return getQueryPage(cri, page);
	}

	public int save(Studentinfos pojo) {
		return (Integer) sessionFactory.getCurrentSession().save(pojo);
	}

	public int batchDelete(String strid) {
		String hqlDelete = "delete from  studentinfos  where id in(" + strid
				+ ")";
		int deletedEntities = sessionFactory.getCurrentSession()
				.createSQLQuery(hqlDelete).executeUpdate();
		return deletedEntities;
	}

	public List<StudentBean> findStudentBeanListBean(String syfl) {

		String params = "";
		if (!syfl.equals("")) {
			if (syfl.equals("1")) { // 本区学生信息查询(同安区）
				params = " and origin like '%同安区%'";
			} else if (syfl.equals("2")) { // 本市内非本区学生信息查询
											// 厦门市思明区、厦门市湖里区、厦门市集美区、厦门市海沧区、厦门市翔安区
				params = " and (origin like '%厦门市思明区%'  or origin like '%厦门市湖里区%'"
						+ " or origin like '%厦门市集美区%' or origin like '%厦门市海沧区%' or origin like '%厦门市翔安区%')";
			} else if (syfl.equals("3")) { // 省内非本市学生信息查询 福建省除厦门市外其他市区
				params = " and (origin like '福建省%' and origin not like '%厦门市%' )";
			} else if (syfl.equals("4")) { // 省外学生信息查询
				params = " and origin not like '%福建省%' ";
			}else if (syfl.equals("0")) { // 省外学生信息查询
				params = " ";
			}
		}
		StringBuilder sbstr = new StringBuilder(
				"SELECT age,sex,COUNT(*) sums  FROM studentinfos where 1=1 " + params
						+ " GROUP BY age,sex WITH ROLLUP;");

		List<StudentBean> list = jdbcTemplate.query(sbstr.toString(),
				new BeanPropertyRowMapper<StudentBean>(StudentBean.class));
		return list;
	}
}
