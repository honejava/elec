package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecEquapmentService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecEquapmentServiceImpl";

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String string);

	public List<ElecEquapment> findEquapmentListByCondition(ElecEquapment model);

	public ElecEquapment findEquapmentById(Integer equapmentID);

	public void update(ElecEquapment model);

	public void delete(ElecEquapment model);

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecEquapment model);
	
	
	
	
	

}
