package cn.haut.elec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecDevPlan;

public interface IElecDevPlanDao extends ICommonDao<ElecDevPlan> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecDevPlanDaoImpl";

	void saves(ArrayList<ElecDevPlan> devPlanlist);

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);

	public List<Object[]> findElecDevPlanCount();
	

}