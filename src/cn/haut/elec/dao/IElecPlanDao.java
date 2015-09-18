package cn.haut.elec.dao;

import cn.haut.elec.domain.ElecPlan;

public interface IElecPlanDao extends ICommonDao<ElecPlan> {
	public static final String SERVICE_NAME = "cn.haut.elec.dao.impl.ElecPlanDaoImpl";

	public void deletePlan(ElecPlan plan);

}
