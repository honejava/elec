package cn.haut.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.haut.elec.dao.IElecPlanDao;
import cn.haut.elec.domain.ElecPlan;

@Repository(IElecPlanDao.SERVICE_NAME)
public class ElecPlanDaoImpl extends CommonDaoImpl<ElecPlan> implements
		IElecPlanDao {

	@Override
	public void deletePlan(ElecPlan plan) {
		this.getHibernateTemplate().delete(plan);

	}

}
