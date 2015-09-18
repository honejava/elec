package cn.haut.elec.dao;

import cn.haut.elec.domain.ElecPopedom;

public interface IElecPopedomDao extends ICommonDao<ElecPopedom> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecPopedomDaoImpl";

	public ElecPopedom finPopedomByMid(String mid);
}
