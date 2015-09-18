package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.domain.ElecExportFields;

@Repository(IElecExportFieldsDao.SERVICE_NAME)
public class ElecExportFieldsDaoImpl extends CommonDaoImpl<ElecExportFields>
		implements IElecExportFieldsDao {

}
