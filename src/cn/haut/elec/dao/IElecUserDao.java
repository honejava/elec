package cn.haut.elec.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.utils.PageInfo;

public interface IElecUserDao extends ICommonDao<ElecUser> {
	public static final String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecUserDaoImpl";

	public ElecUser findRolelistByID(String userID);

	public int count();

	public List<ElecUser> findCollectionByConditionWithPage(String condition,
			Object[] params, Map<String, String> orderby, PageInfo pageInfo);

	@SuppressWarnings("rawtypes")
	public List findCollectionByConditionNoPageWithExcel(String condition,
			Object[] params, Map<String, String> orderby, String selectCondition);

	public void saves(ArrayList<ElecUser> list);

	public String checkUserByLogonName(String string);

	public List<Object[]> findElecUserCount();

}
