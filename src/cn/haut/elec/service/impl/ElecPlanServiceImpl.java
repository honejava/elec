package cn.haut.elec.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecFileUploadDao;
import cn.haut.elec.dao.IElecPlanDao;
import cn.haut.elec.dao.IElecStationDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecFileUpload;
import cn.haut.elec.domain.ElecPlan;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecPlanService;
import cn.haut.elec.utils.PageInfo;

@Service(IElecPlanService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecPlanServiceImpl implements IElecPlanService {

	// 注入业务层的数据
	@Resource(name = IElecPlanDao.SERVICE_NAME)
	private IElecPlanDao planDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入站点的dao
	@Resource(name = IElecStationDao.SERVICE_NAME)
	private IElecStationDao stationDao;

	// 注入文件上传的的dao
	@Resource(name = IElecFileUploadDao.SERVICE_NAME)
	private IElecFileUploadDao fileUploadDao;

	public List<ElecSystemDDL> findSystemDDLListByKeyword(String keyword) {
		if (keyword.isEmpty()) {
			return elecSystemDDLDao.findCollectionByConditionNoPageWithCach(
					null, null, null);
		}
		String condition = " and o.keyword=?";
		Object[] params = { keyword };

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.ddlCode", "asc");

		return elecSystemDDLDao.findCollectionByConditionNoPageWithCach(
				condition, params, orderby);
	}

	public List<ElecPlan> findPlanListByCondition(ElecPlan plan) {

		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtils.isNotBlank(plan.getJctID())
				&& !plan.getJctID().equals("0")) {
			condition += " and o.jctID=? ";
			paramList.add(plan.getJctID());
		}
		if (plan.getStartdate() != null) {
			condition += " and o.occurDate>? ";
			paramList.add(plan.getStartdate());
		}
		if (plan.getEnddate() != null) {
			condition += " and o.occurDate<? ";
			paramList.add(plan.getEnddate());
		}
		Object[] params = paramList.toArray();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put(" o.planID ", "asc");
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecPlan> list = planDao.findCollectionByConditionWithPage(
				condition, params, orderby, pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		this.convertSystemDDL(list);
		return list;
	}

	private void convertSystemDDL(List<ElecPlan> list) {
		for (ElecPlan elecPlan : list) {
			// 所属单位
			elecPlan.setJctID(elecPlan.getJctID() != null ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位",
							elecPlan.getJctID()) : "");
		}
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecPlan plan) {
		planDao.save(plan);
	}

	public ElecPlan findPlanById(Integer planID) {
		return planDao.findObjectByID(planID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecPlan model) {
		planDao.update(model);
	}

	@Transactional(readOnly = false)
	public void delete(Integer planID) {
		String condition = " and o.elecPlan.planID=?";
		List<Object> paramList = new ArrayList<Object>();
		paramList.add(planID);
		Object[] params = paramList.toArray();
		List<ElecFileUpload> list = fileUploadDao
				.findCollectionByConditionNoPage(condition, params, null);
		if (list != null && list.size() > 0) {
			for (ElecFileUpload elecFileUpload : list) {
				elecFileUpload.setElecPlan(null);
				fileUploadDao.deleteObjectByIDs(elecFileUpload
						.getFileUploadID());
			}
		}
		ElecPlan plan = planDao.findObjectByID(planID);
		planDao.deletePlan(plan);
	}
}
