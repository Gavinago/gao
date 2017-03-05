package com.wssys.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import com.wssys.entity.Seqs;

@Repository
@Transactional
public class SeqsDao {

	@Autowired
	private SessionFactory sessionFactory;

	// public Seqs getById(int id) {
	// return (Seqs) sessionFactory.getCurrentSession().get(Seqs.class, id);
	//
	// }

	public Seqs getSeqs() {
		Query query = sessionFactory.getCurrentSession().createQuery(
				"from Seqs");
		List<Seqs> list = query.list();
		if (list.size() > 0) {
			return list.get(0);
		} else {
			return new Seqs();
		}
	}
	
	
	public int updatGoodsSeq(String GoodsSeq) {

		String sql = "UPDATE seqs SET goodsnum='"+GoodsSeq+"'";
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.executeUpdate();
		return updateEntities;
	}
	
	public int updatCknumSeq(String cknumSeq) {

		String sql = "UPDATE seqs SET cknum='"+cknumSeq+"'";
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.executeUpdate();
		return updateEntities;
	}
	
	public int updatCgnumSeq(String cgnumSeq) {

		String sql = "UPDATE seqs SET cgnum='"+cgnumSeq+"'";
		int updateEntities = sessionFactory.getCurrentSession().createSQLQuery(sql)
				.executeUpdate();
		return updateEntities;
	}
}
