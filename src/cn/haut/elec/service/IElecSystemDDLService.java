package cn.haut.elec.service;

import java.util.List;

import cn.haut.elec.domain.ElecSystemDDL;


public interface IElecSystemDDLService {
	
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecSystemDDLServiceImpl";

	public List<ElecSystemDDL> findDistinctKeyWordList();

	public List<ElecSystemDDL> findSystemDDLByKeywordlist(String keyword);

	public void saveSystemDDL(ElecSystemDDL elecSystemDDL);

	public String findSystemDDLByKeywordAndDDLCode(String keyword, String sexID);

	
}
