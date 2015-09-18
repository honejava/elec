package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecCommonMsgDao;
import cn.haut.elec.domain.ElecCommonMsg;

@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg>
		implements IElecCommonMsgDao {

}
