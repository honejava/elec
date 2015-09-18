package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecApplicationFlowDao;
import cn.haut.elec.domain.ElecApplication;

@Repository(IElecApplicationFlowDao.SERVICE_NAME)
public class ElecApplicationFlowDaoImpl extends CommonDaoImpl<ElecApplication>
		implements IElecApplicationFlowDao {

}
