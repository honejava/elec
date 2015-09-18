package cn.haut.elec.service;

import java.util.ArrayList;
import java.util.List;

import cn.haut.elec.domain.ElecAdjust;
import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecAdjustService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecAdjustServiceImpl";

	public List<ElecAdjust> findAdjustByCondition(ElecAdjust adjust);

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String string);

	public List<ElecEquapment> findEquapmentByCondition(ElecAdjust adjust);

	public void moreUpdate(ElecAdjust model);

	public List<ElecAdjust> findAdjustHistory();

	public ElecAdjust findAdjustById(Integer adjustID);

	public void delete(ElecAdjust model);

	public void update(Integer integer, String comment, String record);

	public ArrayList<String> findExcelFiledName();

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecAdjust model);

	public void save(ElecAdjust model);
	
	

}
