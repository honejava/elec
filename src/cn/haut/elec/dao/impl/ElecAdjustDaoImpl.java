package cn.haut.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecAdjustDao;
import cn.haut.elec.domain.ElecAdjust;

@Repository(IElecAdjustDao.SERVICE_NAME)
public class ElecAdjustDaoImpl extends CommonDaoImpl<ElecAdjust> implements
		IElecAdjustDao {

	public void saves(List<ElecAdjust> adjustList) {
		this.getHibernateTemplate().saveOrUpdateAll(adjustList);
	}

}
