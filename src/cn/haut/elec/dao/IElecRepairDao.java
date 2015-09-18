package cn.haut.elec.dao;

import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecRepair;

public interface IElecRepairDao extends ICommonDao<ElecRepair> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecRepairDaoImpl";

	public void saves(List<ElecRepair> repairList);

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);

}
