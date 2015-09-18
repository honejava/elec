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

import cn.haut.elec.dao.IElecAdjustDao;
import cn.haut.elec.dao.IElecEquapmentDao;
import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecFileUploadDao;
import cn.haut.elec.dao.IElecRepairDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.domain.ElecFileUpload;
import cn.haut.elec.domain.ElecRepair;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecRepairService;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToArrayUtil;
import cn.haut.elec.utils.StringToListUtils;

@Service(IElecRepairService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecRepairServiceImpl implements IElecRepairService {

	// 注入业务层的数据
	@Resource(name = IElecAdjustDao.SERVICE_NAME)
	private IElecAdjustDao adjustDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入设备的dao
	@Resource(name = IElecEquapmentDao.SERVICE_NAME)
	private IElecEquapmentDao equapmentDao;

	// 注入设备检修的dao
	@Resource(name = IElecRepairDao.SERVICE_NAME)
	private IElecRepairDao repairDao;

	// 注入文件上传的dao
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

	public List<ElecEquapment> findRepairByCondition(ElecRepair repair) {
		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtils.isNotBlank(repair.getDevType())
				&& !repair.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(repair.getDevType());
		}
		Object[] params = paramList.toArray();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put(" o.equapmentID", "asc");
		List<ElecEquapment> list = equapmentDao
				.findCollectionByConditionNoPage(condition, params, orderby);
		// 数据字典 的 所属单位转换
		for (ElecEquapment elecEquapment : list) {
			elecEquapment.setJctID(StringUtils.isNotBlank(elecEquapment
					.getJctID()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位",
							elecEquapment.getJctID()) : "");
		}
		return list;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void moreUpdate(ElecRepair elecRepair) {
		String[] ids = StringToArrayUtil.StringToArray(elecRepair.getJctID(),
				",");
		List<ElecRepair> repairList = new ArrayList<ElecRepair>();
		for (int i = 0; i < ids.length; i++) {
			// 获取设备信息
			ElecEquapment equapment = equapmentDao.findObjectByID(Integer
					.parseInt(ids[i]));
			// 添加校准
			ElecRepair repair = new ElecRepair();

			repair.setEquapmentID(equapment.getEquapmentID());
			repair.setJctID(equapment.getJctID());
			repair.setDevName(equapment.getDevName());

			repair.setOverhaulPeriod(equapment.getOverhaulPeriod());
			repair.setOpunit(equapment.getOpunit());

			repair.setUseDate(equapment.getUseDate());
			repair.setDevType(equapment.getDevType());

			repair.setStartDate(elecRepair.getStartDate());
			repair.setIsHaving(ElecRepair.REPAIR_ISHAVING);

			repair.setComment(elecRepair.getComment());
			repair.setRecord(elecRepair.getRecord());

			repairList.add(repair);
		}
		repairDao.saves(repairList);

	}

	public List<ElecRepair> findRepairtHistory() {
		String condition = " and o.isHaving=?";
		Object[] params = { "2" };
		List<ElecRepair> list = repairDao.findCollectionByConditionNoPage(
				condition, params, null);
		this.convertSystemDDL(list);
		return list;
	}

	private void convertSystemDDL(List<ElecRepair> repairList) {
		for (ElecRepair repair : repairList) {
			// 所刷单位
			repair.setJctID(StringUtils.isNotBlank(repair.getJctID()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位", repair.getJctID())
					: "");
			// 检修状态
			repair.setIsHaving(StringUtils.isNotBlank(repair.getIsHaving()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("检修状态",
							repair.getIsHaving()) : "");
			// 设备类型
			repair.setDevType(StringUtils.isNotBlank(repair.getDevType()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("设备类型",
							repair.getDevType()) : "");
		}
	}

	public List<ElecRepair> findRepairListByCondition(ElecRepair repair) {
		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		// 所属单位
		if (StringUtils.isNotBlank(repair.getJctID())
				&& !repair.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(repair.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(repair.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(repair.getDevName());
		}
		// 检修状态
		if (StringUtils.isNotBlank(repair.getIsHaving())
				&& !repair.getIsHaving().equals("0")) {
			condition += " and o.isHaving=?";
			paramList.add(repair.getIsHaving());
		}
		// 检修日期
		if (repair.getStartDatef() != null) {
			condition += " and o.startDate>=?";
			paramList.add(repair.getStartDatef());
		}
		if (repair.getStartDatet() != null) {
			condition += " and o.startDate<=?";
			paramList.add(repair.getStartDatet());
		}
		// 设备类型
		if (StringUtils.isNotBlank(repair.getDevType())
				&& !repair.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(repair.getDevType());
		}
		Object[] params = paramList.toArray();
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put(" o.repairID", "asc");
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecRepair> list = repairDao.findCollectionByConditionWithPage(
				condition, params, orderby, pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		this.convertSystemDDL(list);
		return list;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecRepair model) {
		repairDao.save(model);
	}

	public ElecRepair findRepairById(Integer repairID) {
		return repairDao.findObjectByID(repairID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecRepair elecRepair) {
		ElecRepair repair = repairDao.findObjectByID(elecRepair.getRepairID());
		repair.setStartDate(elecRepair.getStartDate());
		repair.setComment(elecRepair.getComment());
		repair.setRecord(elecRepair.getRecord());
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(ElecRepair repair) {
		// 级联删除 上传文件
		String condition = " and o.repairID is not null ";
		List<Object> paramList = new ArrayList<Object>();
		if (repair.getRepairID() != null) {
			condition += " and o.repairID=?";
			paramList.add(repair.getRepairID());
		}
		Object[] params = paramList.toArray();
		List<ElecFileUpload> list = fileUploadDao
				.findCollectionByConditionNoPage(condition, params, null);
		if (list != null && list.size() > 0) {
			// 删除 这个 校准小 上传的文件
			fileUploadDao.deleteObjectByCollection(list);
		}
		repairDao.deleteObjectByIDs(repair.getRepairID());
	}

	public ArrayList<String> findExcelFiledName() {
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-2-2");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList<String>> findExcelFiledData(ElecRepair repair) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-2-2");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		// 获取导出设置的中导出的英文名称
		String eName = elecExportFields.getExpFieldName();
		// 将导出的英文字段名称中的#号替换成逗号，即查询的条件
		String selectCondition = eName.replace("#", ",");
		// 查询条件
		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		// 所属单位
		if (StringUtils.isNotBlank(repair.getJctID())
				&& !repair.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(repair.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(repair.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(repair.getDevName());
		}
		// 检修状态
		if (StringUtils.isNotBlank(repair.getIsHaving())
				&& !repair.getIsHaving().equals("0")) {
			condition += " and o.isHaving=?";
			paramList.add(repair.getIsHaving());
		}
		// 检修日期
		if (repair.getStartDatef() != null) {
			condition += " and o.startDate>=?";
			paramList.add(repair.getStartDatef());
		}
		if (repair.getStartDatet() != null) {
			condition += " and o.startDate<=?";
			paramList.add(repair.getStartDatet());
		}
		// 设备类型
		if (StringUtils.isNotBlank(repair.getDevType())
				&& !repair.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(repair.getDevType());
		}

		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.repairID", "asc");
		Object[] params = paramList.toArray();
		// 获取结果，表示返回的所有结果
		List list = repairDao.findCollectionByConditionNoPageWithExcel(
				condition, params, orderby, selectCondition);
		// 遍历list
		if (list != null && list.size() > 0) {
			for (int i = 0; i < list.size(); i++) {
				// 存放每一条的数据
				Object[] arrays = null;
				// list返回Object[]对象
				if (selectCondition.contains(",")) {
					arrays = (Object[]) list.get(i);// 获取的是每1条的数据
				}
				// list返回Object对象
				else {
					arrays = new Object[1];
					arrays[0] = list.get(i);// 获取的是每1条的数据
				}
				// 真实存放的每1条的数据
				ArrayList<String> data = new ArrayList<String>();
				if (arrays != null && arrays.length > 0) {
					for (int j = 0; j < arrays.length; j++) {
						Object o = arrays[j];
						// 如果是数据字典的字段，需要数据字典的转换
						if (zList != null && zList.get(j).equals("所属单位")
								|| zList.get(j).equals("设备类型")
								|| zList.get(j).equals("检修状态")) {
							data.add(o != null ? elecSystemDDLDao
									.findSystemDDLByKeywordAndDDLCode(
											zList.get(j), o.toString()) : "");
						} else {
							data.add(o != null ? o.toString() : "");
						}

					}
				}
				fieldData.add(data);
			}
		}
		return fieldData;

	}

}
