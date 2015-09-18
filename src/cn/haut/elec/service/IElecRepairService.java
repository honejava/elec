package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecRepair;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecRepairService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecRepairServiceImpl";

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String string);

	public List<ElecEquapment> findRepairByCondition(ElecRepair model);

	public void moreUpdate(ElecRepair model);

	public List<ElecRepair> findRepairtHistory();

	public List<ElecRepair> findRepairListByCondition(ElecRepair model);

	public void save(ElecRepair model);

	public ElecRepair findRepairById(Integer repairID);

	public void update(ElecRepair model);

	public void delete(ElecRepair model);

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecRepair model);

}
