package cn.haut.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecBuildingDao;
import cn.haut.elec.domain.ElecBuilding;

@Repository(IElecBuildingDao.SERVICE_NAME)
public class ElecBuildingDaoImpl extends CommonDaoImpl<ElecBuilding> implements
		IElecBuildingDao {

	@Override
	public void saves(ArrayList<ElecBuilding> buildlist) {
		this.getHibernateTemplate().saveOrUpdateAll(buildlist);

	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object[]> findElecBuildingCount() {
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String sql = "SELECT b.ddlName as ddlname,COUNT(*) as count FROM elec_building a "
								+ "LEFT JOIN elec_systemddl b ON a.JctID=b.DdlCode "
								+ "AND b.Keyword='所属单位' GROUP BY a.JctID";
						Query query = session.createSQLQuery(sql)
								.addScalar("ddlname", Hibernate.STRING)
								.addScalar("count", Hibernate.STRING);
						return query.list();
					}

				});
		return list;
	}

}
