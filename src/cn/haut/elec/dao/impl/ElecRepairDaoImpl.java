package cn.haut.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecRepairDao;
import cn.haut.elec.domain.ElecRepair;

@Repository(IElecRepairDao.SERVICE_NAME)
public class ElecRepairDaoImpl extends CommonDaoImpl<ElecRepair> implements
		IElecRepairDao {

	public void saves(List<ElecRepair> repairList) {
		this.getHibernateTemplate().saveOrUpdateAll(repairList);
	}

}
