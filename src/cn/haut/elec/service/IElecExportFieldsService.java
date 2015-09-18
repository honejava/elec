package cn.haut.elec.service;

import cn.haut.elec.domain.ElecExportFields;


public interface IElecExportFieldsService {
	
	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecExportFieldsServiceImpl";

	public ElecExportFields findExportFieldsByID(String belongTo);

	public void saveSetExportExcel(ElecExportFields exportFields);
	
}
