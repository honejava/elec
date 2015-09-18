package cn.haut.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecRolePopedomDao;
import cn.haut.elec.domain.ElecRolePopedom;

@Repository(IElecRolePopedomDao.SERVICE_NAME)
public class ElecRolePopedomDaoImpl extends CommonDaoImpl<ElecRolePopedom>
		implements IElecRolePopedomDao {

	public Boolean isExist(ElecRolePopedom rolePopedom) {
		String condition = "and o.roleID=? and o.mid=? and o.pid=?";
		Object[] params = { rolePopedom.getRoleID(), rolePopedom.getMid(),
				rolePopedom.getPid() };
		List<ElecRolePopedom> list = this.findCollectionByConditionNoPage(
				condition, params, null);
		if (list != null && list.size() > 0) {
			return true;
		}
		return false;
	}

	public void deletePopedomByRoleId(final String roleID) {
		this.getHibernateTemplate().execute(new HibernateCallback<String>() {
			@Override
			public String doInHibernate(Session session)
					throws HibernateException, SQLException {
				Query query = session
						.createQuery("delete from ElecRolePopedom where roleID=?");
				query.setString(0, roleID);
				query.executeUpdate();
				return null;
			}
		});
	}

}
