package cn.haut.elec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecStation;
import cn.haut.elec.utils.PageInfo;

public interface IElecStationDao extends ICommonDao<ElecStation> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecStationDaoImpl";

	public List<ElecStation> findCollectionByConditionWithPage(
			String condition, Object[] params, Map<String, String> orderby,
			PageInfo pageInfo);

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);

	public void saves(ArrayList<ElecStation> stationlist);

	public List<Object[]> findElecStationCount();
	
	

}
