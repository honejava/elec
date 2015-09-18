package cn.haut.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecBugDao;
import cn.haut.elec.domain.ElecBug;

@Repository(IElecBugDao.SERVICE_NAME)
public class ElecBugDaoImpl extends CommonDaoImpl<ElecBug> implements
		IElecBugDao {

	@Override
	public List<String> findDistinctStationNameList() {
		List<String> execute = this.getHibernateTemplate().execute(
				new HibernateCallback<List<String>>() {

					@SuppressWarnings("unchecked")
					@Override
					public List<String> doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session
								.createQuery("select distinct(s.stationName) from ElecStation s");
						return query.list();
					}
				});
		return execute;
	}

	@Override
	public void saves(ArrayList<ElecBug> buglist) {
		this.getHibernateTemplate().saveOrUpdateAll(buglist);
	}

	public void deleteBug(ElecBug bug) {
		this.getHibernateTemplate().delete(bug);
	}

}
