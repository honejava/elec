package cn.haut.elec.service;

import java.util.List;

import cn.haut.elec.domain.ElecDataChart;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecDataChartService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecDataChartServiceImpl";

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String string);

	public void save(ElecDataChart model);

	public List<ElecDataChart> findDataChartByCondition(ElecDataChart model);

	public ElecDataChart findDataChartById(Integer dataChartID);

	public void delete(ElecDataChart model);

	public List<ElecDataChart> findDataChartList(ElecDataChart model);
	
	
	

}
