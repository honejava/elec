package cn.haut.elec.dao;

import java.util.List;

import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecSystemDDLDao extends ICommonDao<ElecSystemDDL>{
	public static final String SERVICE_NAME = "cn.itcast.elec.dao.impl.ElecSystemDDLDaoImpl";

	public List<ElecSystemDDL> findDistinctKeyWordList();

	
	public String findSystemDDLByKeywordAndDDLCode(String keyword,String ddlCode);


	public String findSystemDDLByKeywordAndDDLName(String keyword, String ddlName);
	


	

}
