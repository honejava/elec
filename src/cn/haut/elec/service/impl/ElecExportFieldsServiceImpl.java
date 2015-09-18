package cn.haut.elec.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.service.IElecExportFieldsService;


@Service(IElecExportFieldsService.SERVICE_NAME)
@Transactional(readOnly=true)
public class ElecExportFieldsServiceImpl implements IElecExportFieldsService {

	@Resource(name=IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao iElecExportFieldsDao;

	public ElecExportFields findExportFieldsByID(String belongTo) {
		return iElecExportFieldsDao.findObjectByID(belongTo);
	}
    @Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
	public void saveSetExportExcel(ElecExportFields exportFields) {
    	iElecExportFieldsDao.update(exportFields);
	}
	


	
}
