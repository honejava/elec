package cn.haut.elec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecBuilding;
import cn.haut.elec.utils.PageInfo;

public interface IElecBuildingDao extends ICommonDao<ElecBuilding> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecBuildingDaoImpl";

	public List<ElecBuilding> findCollectionByConditionWithPage(String condition,
			Object[] params, Map<String, String> orderby, PageInfo pageInfo);

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);

	public void saves(ArrayList<ElecBuilding> buildlist);

	public List<Object[]> findElecBuildingCount();
	
	

}
