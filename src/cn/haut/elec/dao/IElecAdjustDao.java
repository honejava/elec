package cn.haut.elec.dao;

import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecAdjust;

public interface IElecAdjustDao extends ICommonDao<ElecAdjust> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecAdjustDaoImpl";

	public void saves(List<ElecAdjust> adjustList);

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);
	

}
