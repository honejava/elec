package cn.haut.elec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecBug;

public interface IElecBugDao extends ICommonDao<ElecBug> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecBugDaoImpl";

	public List<String> findDistinctStationNameList();

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);

	public void saves(ArrayList<ElecBug> buglist);

	public void deleteBug(ElecBug bug);
	
	

}
