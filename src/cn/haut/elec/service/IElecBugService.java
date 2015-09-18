package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecStation;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecBugService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecBugServiceImpl";

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String string);

	public List<ElecBug> findBugByCondition(ElecBug elecBug);

	public List<ElecStation> findAllStation();

	public ElecBug findBugById(Integer bugID);

	public void update(ElecBug model);

	public List<String> findDistinctStationNameList();

	public String findSumBugHour();

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecBug bug);

	public List<String> importData(ElecBug model, HttpServletRequest request);

	public boolean save(ElecBug model);

}
