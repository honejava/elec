package cn.haut.elec.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.hibernate.Hibernate;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecUserDao;
import cn.haut.elec.domain.ElecRole;
import cn.haut.elec.domain.ElecUser;

@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements
		IElecUserDao {

	// 根据 用户的id来找到当前用户角色的结合
	public ElecUser findRolelistByID(String userID) {
		ElecUser user = this.getHibernateTemplate().get(ElecUser.class, userID);
		this.getHibernateTemplate().initialize(user);
		Set<ElecRole> rolrset = user.getElecRoles();
		for (ElecRole elecRole : rolrset) {
			this.getHibernateTemplate().initialize(elecRole);
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	// 计算用户的数量
	public int count() {
		List<ElecUser> list = this.getHibernateTemplate().find("from ElecUser");
		return list != null && list.size() > 0 ? list.size() : 0;
	}

	// 批量保存用户
	public void saves(ArrayList<ElecUser> list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	public String checkUserByLogonName(String logonName) {

		return (String) this
				.getHibernateTemplate()
				.find("select o.logonName from ElecUser o  where o.logonName=?",
						logonName).get(0);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public List<Object[]> findElecUserCount() {
		List<Object[]> list = (List<Object[]>) this.getHibernateTemplate()
				.execute(new HibernateCallback() {

					@SuppressWarnings("deprecation")
					public Object doInHibernate(Session session)
							throws HibernateException, SQLException {
						String sql = "SELECT b.DdlName as ddlname,COUNT(*) as count FROM elec_user a "
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
