package cn.haut.elec.dao;

import cn.haut.elec.domain.ElecRolePopedom;

public interface IElecRolePopedomDao extends ICommonDao<ElecRolePopedom> {
	public static final String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecRolePopedomDaoImpl";

	public Boolean isExist(ElecRolePopedom rolePopedom);

	public void deletePopedomByRoleId(String roleID);
}

