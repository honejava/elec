package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.haut.elec.domain.ElecUser;


public interface IElecUserService {
	
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecUserServiceImpl";

	public List<ElecUser> findUserListByCondition(ElecUser elecUser);

	public void save(ElecUser elecUser);

	public String checkUserByLogonName(String logonName);

	public ElecUser findUserByUserId(String userID);

	public void update(ElecUser elecUser);

	public void delete(String userID);

	public void deleteAll(String[] userIDarray);

	public int findUserCounts();

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecUser elecUser);

	public List<String> importData(ElecUser elecUser, HttpServletRequest request);

	public List<ElecUser> findElecUserCount();
	
	
	
	



}
