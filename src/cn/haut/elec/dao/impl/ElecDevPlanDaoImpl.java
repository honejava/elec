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

import cn.haut.elec.dao.IElecDevPlanDao;
import cn.haut.elec.domain.ElecDevPlan;

@Repository(IElecDevPlanDao.SERVICE_NAME)
public class ElecDevPlanDaoImpl extends CommonDaoImpl<ElecDevPlan> implements
		IElecDevPlanDao {

	public void saves(ArrayList<ElecDevPlan> devPlanlist) {
		this.getHibernateTemplate().saveOrUpdateAll(devPlanlist);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public List<Object[]> findElecDevPlanCount() {
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate()
				.execute(new HibernateCallback() {
					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String sql = "SELECT b.ddlName as ddlname,COUNT(*) as count FROM elec_devplan a "
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
