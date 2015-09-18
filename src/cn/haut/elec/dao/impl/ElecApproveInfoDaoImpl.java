package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecApproveInfoDao;
import cn.haut.elec.domain.ElecApproveInfo;

@Repository(IElecApproveInfoDao.SERVICE_NAME)
public class ElecApproveInfoDaoImpl extends CommonDaoImpl<ElecApproveInfo>
		implements IElecApproveInfoDao {

}
