package cn.haut.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecSystemDDL;

@Repository(IElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL>
		implements IElecSystemDDLDao {

	@SuppressWarnings("unchecked")
	public List<ElecSystemDDL> findDistinctKeyWordList() {
		String hql = "select distinct keyword from ElecSystemDDL ";
		List<Object> list = this.getHibernateTemplate().find(hql);
		List<ElecSystemDDL> systemlist = new ArrayList<ElecSystemDDL>();
		for (int i = 0; i < list.size(); i++) {
			ElecSystemDDL e = new ElecSystemDDL();
			e.setKeyword(list.get(i).toString());
			systemlist.add(e);
		}
		return systemlist;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findSystemDDLByKeywordAndDDLCode(final String keyword,
			final String ddlCode) {
		final String hql = "select o.ddlName from ElecSystemDDL o where o.keyword=? and o.ddlCode=?";
		List<Object> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {

					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter(0, keyword);
						query.setParameter(1, Integer.parseInt(ddlCode));
						query.setCacheable(true);
						return query.list();
					}
				});

		return (String) (list != null && list.size() > 0 ? list.get(0) : null);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String findSystemDDLByKeywordAndDDLName(final String keyword,
			final String ddlName) {
		final String hql = "select o.ddlCode from ElecSystemDDL o where o.keyword=? and o.ddlName=?";
		List<Object> list = this.getHibernateTemplate().execute(
				new HibernateCallback() {
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						Query query = session.createQuery(hql);
						query.setParameter(0, keyword);
						query.setParameter(1, ddlName);
						return query.list();
					}
				});

		return (list != null && list.size() > 0 ? list.get(0).toString() : null);
	}

}
