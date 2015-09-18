package cn.haut.elec.service;

import java.util.List;

import cn.haut.elec.domain.ElecPlan;
import cn.haut.elec.domain.ElecSystemDDL;

public interface IElecPlanService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecPlanServiceImpl";

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String string);

	public List<ElecPlan> findPlanListByCondition(ElecPlan model);

	public void save(ElecPlan model);

	public ElecPlan findPlanById(Integer planID);

	public void update(ElecPlan model);

	public void delete(Integer planID);

}
