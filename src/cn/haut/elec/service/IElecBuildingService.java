package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.haut.elec.domain.ElecBuilding;

public interface IElecBuildingService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecBuildingServiceImpl";

	public void save(ElecBuilding model);

	public List<ElecBuilding> findBuildByCondition(ElecBuilding model);

	public ElecBuilding findBuildByID(Integer buildingID);

	public void update(ElecBuilding model);

	public void delete(ElecBuilding model);

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecBuilding building);

	public List<String> importData(ElecBuilding model,
			HttpServletRequest request);

	public List<ElecBuilding> findElecBuildingCount();

}
