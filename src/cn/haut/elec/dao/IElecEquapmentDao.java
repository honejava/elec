package cn.haut.elec.dao;

import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecEquapment;

public interface IElecEquapmentDao extends ICommonDao<ElecEquapment> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecEquapmentDaoImpl";

	public void saves(List<ElecEquapment> equapmentList);

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);

}
