package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecRoleDao;
import cn.haut.elec.domain.ElecRole;

@Repository(IElecRoleDao.SERVICE_NAME)
public class ElecRoleDaoImpl extends CommonDaoImpl<ElecRole> implements
		IElecRoleDao {

}
