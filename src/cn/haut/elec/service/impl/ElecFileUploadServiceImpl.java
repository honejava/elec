package cn.haut.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecAdjustDao;
import cn.haut.elec.dao.IElecBugDao;
import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecFileUploadDao;
import cn.haut.elec.dao.IElecPlanDao;
import cn.haut.elec.dao.IElecRepairDao;
import cn.haut.elec.dao.IElecStationDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecAdjust;
import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecFileUpload;
import cn.haut.elec.domain.ElecPlan;
import cn.haut.elec.domain.ElecRepair;
import cn.haut.elec.service.IElecFileUploadService;

@Service(IElecFileUploadService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecFileUploadServiceImpl implements IElecFileUploadService {

	// 注入业务层的数据
	@Resource(name = IElecFileUploadDao.SERVICE_NAME)
	private IElecFileUploadDao fileUploadDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入站点bug的dao
	@Resource(name = IElecBugDao.SERVICE_NAME)
	private IElecBugDao bugDao;

	// 注入站点plan的dao
	@Resource(name = IElecPlanDao.SERVICE_NAME)
	private IElecPlanDao planDao;

	// 注入设备校准的dao
	@Resource(name = IElecRepairDao.SERVICE_NAME)
	private IElecRepairDao repairDao;

	// 注入设备检修的dao
	@Resource(name = IElecAdjustDao.SERVICE_NAME)
	private IElecAdjustDao adjustDao;

	// 注入站点的dao
	@Resource(name = IElecStationDao.SERVICE_NAME)
	private IElecStationDao stationDao;

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecFileUpload upload) {
		fileUploadDao.save(upload);
	}

	public ElecBug findBugById(Integer bugID) {
		return bugDao.findObjectByID(bugID);
	}

	public List<ElecFileUpload> findFileUploadByCondition() {
		return fileUploadDao.findCollectionByConditionNoPage(
				" and o.elecPlan is null", null, null);
	}

	public ElecFileUpload findFileUploadById(Integer fileUploadID) {
		return fileUploadDao.findObjectByID(fileUploadID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteBugFileUploadById(Integer fileUploadID) {
		ElecFileUpload upload = fileUploadDao.findObjectByID(fileUploadID);
		upload.setElecBug(null);
		fileUploadDao.deleteObjectByIDs(fileUploadID);
		// 删除磁盘中的上传文件
		String path = ServletActionContext.getServletContext().getRealPath(
				"/upload/" + upload.getFileFileName());
		new File(path).delete();
	}

	public ElecPlan findPlanById(Integer planID) {
		return planDao.findObjectByID(planID);
	}

	public List<ElecFileUpload> findFileUploadListByCondition(
			ElecFileUpload fileUpload) {
		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		if (fileUpload.getElecPlan() != null
				&& fileUpload.getElecPlan().getPlanID() != null) {
			condition += " and o.elecPlan.planID=? ";
			paramList.add(fileUpload.getElecPlan().getPlanID());
		}
		condition += "and o.elecBug is null";
		Object[] params = paramList.toArray();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put(" o.fileUploadID  ", "asc");
		List<ElecFileUpload> list = fileUploadDao
				.findCollectionByConditionNoPage(condition, params, orderby);
		return list;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deletePlanFileUploadById(Integer fileUploadID) {
		ElecFileUpload upload = fileUploadDao.findObjectByID(fileUploadID);
		upload.setElecPlan(null);
		fileUploadDao.deleteObjectByIDs(fileUploadID);
		// 删除磁盘中的上传文件
		String path = ServletActionContext.getServletContext().getRealPath(
				"/planUpload/" + upload.getFileFileName());
		new File(path).delete();
	}

	public List<ElecFileUpload> findAdjustFileUploadByCondition(Integer adjustID) {
		String condition = " and o.adjustID is not null ";
		List<Object> paramList = new ArrayList<Object>();
		if (adjustID != null) {
			condition += " and o.adjustID=?";
			paramList.add(adjustID);
		}
		Object[] params = paramList.toArray();
		List<ElecFileUpload> list = fileUploadDao
				.findCollectionByConditionNoPage(condition, params, null);
		return list;
	}

	public ElecAdjust finAdjustById(Integer adjustID) {
		return adjustDao.findObjectByID(adjustID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAdjustFileUploadById(Integer fileUploadID) {
		ElecFileUpload upload = fileUploadDao.findObjectByID(fileUploadID);
		fileUploadDao.deleteObjectByIDs(fileUploadID);
		// 删除磁盘中的上传文件
		String path = ServletActionContext.getServletContext().getRealPath(
				"/adjustUpload/" + upload.getFileFileName());
		new File(path).delete();
	}

	public ElecRepair finRepairById(Integer repairID) {
		return repairDao.findObjectByID(repairID);
	}

	public List<ElecFileUpload> findRepairFileUploadByCondition(Integer repairID) {
		String condition = " and o.repairID is not null ";
		List<Object> paramList = new ArrayList<Object>();
		if (repairID != null) {
			condition += " and o.repairID=?";
			paramList.add(repairID);
		}
		Object[] params = paramList.toArray();
		List<ElecFileUpload> list = fileUploadDao
				.findCollectionByConditionNoPage(condition, params, null);
		return list;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteRepairFileUploadById(Integer fileUploadID) {
		ElecFileUpload upload = fileUploadDao.findObjectByID(fileUploadID);
		fileUploadDao.deleteObjectByIDs(fileUploadID);
		// 删除磁盘中的上传文件
		String path = ServletActionContext.getServletContext().getRealPath(
				"/repairUpload/" + upload.getFileFileName());
		new File(path).delete();
	}
}
