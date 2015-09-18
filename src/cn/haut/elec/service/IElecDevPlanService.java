package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import cn.haut.elec.domain.ElecDevPlan;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecDevPlanService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecDevPlanServiceImpl";

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String string);

	public void save(ElecDevPlan model);

	public List<ElecDevPlan> findDevPlanListByCondition(ElecDevPlan model);

	public ElecDevPlan findDevPlanById(Integer devPlanID);

	public void update(ElecDevPlan model);

	public void deleteById(Integer devPlanID);

	public List<String> importData(ElecDevPlan model, HttpServletRequest request);

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecDevPlan model);

	public void devicePlanToDevice(ElecDevPlan model);

	public List<ElecDevPlan> findElecDevPlanCount();

}
