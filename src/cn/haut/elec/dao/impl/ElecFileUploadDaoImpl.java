package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecFileUploadDao;
import cn.haut.elec.domain.ElecFileUpload;

@Repository(IElecFileUploadDao.SERVICE_NAME)
public class ElecFileUploadDaoImpl extends CommonDaoImpl<ElecFileUpload>
		implements IElecFileUploadDao {

}
