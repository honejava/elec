package cn.haut.elec.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecEquapmentDao;
import cn.haut.elec.domain.ElecEquapment;

@Repository(IElecEquapmentDao.SERVICE_NAME)
public class ElecEquapmentDaoImpl extends CommonDaoImpl<ElecEquapment>
		implements IElecEquapmentDao {

	public void saves(List<ElecEquapment> equapmentList) {
		this.getHibernateTemplate().saveOrUpdateAll(equapmentList);
	}

}
