package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.haut.elec.domain.ElecStation;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecStationService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecStationServiceImpl";

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String keyword);

	public void save(ElecStation model);

	public List<ElecStation> findStationByCondition(ElecStation model);

	public ElecStation findStationByID(Integer stationID);

	public void update(ElecStation model);

	public void delete(ElecStation model);

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecStation station);

	public List<String> importData(ElecStation model, HttpServletRequest request);

	public List<ElecStation> findElecStationCount();

}
