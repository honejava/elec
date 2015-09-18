package cn.haut.elec.service.impl;

import java.util.ArrayList;
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

import cn.haut.elec.dao.IElecBuildingDao;
import cn.haut.elec.dao.IElecEquapmentDao;
import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecEquapmentService;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToListUtils;

@Service(IElecEquapmentService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecEquapmentServiceImpl implements IElecEquapmentService {

	// 注入业务层的数据
	@Resource(name = IElecBuildingDao.SERVICE_NAME)
	private IElecBuildingDao buildingDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入设备dao
	@Resource(name = IElecEquapmentDao.SERVICE_NAME)
	private IElecEquapmentDao equapmentDao;

	@Override
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

	public List<ElecEquapment> findEquapmentListByCondition(
			ElecEquapment equapment) {

		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		// 所属单位
		if (StringUtils.isNotBlank(equapment.getJctID())
				&& !equapment.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(equapment.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(equapment.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(equapment.getDevName());
		}
		// 设备类型
		if (StringUtils.isNotBlank(equapment.getDevType())
				&& !equapment.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(equapment.getDevType());
		}
		// 使用日期
		if (equapment.getUseDatef() != null) {
			condition += " and o.useDate>=?";
			paramList.add(equapment.getUseDatef());
		}
		if (equapment.getUseDatet() != null) {
			condition += " and o.useDate<=?";
			paramList.add(equapment.getUseDatet());
		}
		// 设备状态
		if (StringUtils.isNotBlank(equapment.getDevState())
				&& !equapment.getDevState().equals("0")) {
			condition += " and o.devState=?";
			paramList.add(equapment.getDevState());
		}
		// 计划时间
		if (equapment.getPlanDatef() != null) {
			condition += " and o.planDate>=?";
			paramList.add(equapment.getPlanDatef());
		}
		if (equapment.getPlanDatef() != null) {
			condition += " and o.planDate<=?";
			paramList.add(equapment.getPlanDatet());
		}
		Object[] params = paramList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.equapmentID", "asc");

		/** 2014-2-27 添加分页 begin */
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());

		List<ElecEquapment> equapmentList = equapmentDao
				.findCollectionByConditionWithPage(condition, params, orderby,
						pageInfo);

		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		this.convertSystemDDL(equapmentList);
		return equapmentList;
	}

	private void convertSystemDDL(List<ElecEquapment> equapmentList) {
		for (ElecEquapment equapment : equapmentList) {
			// 所刷单位
			equapment
					.setJctID(StringUtils.isNotBlank(equapment.getJctID()) ? elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLCode("所属单位",
									equapment.getJctID()) : "");
			// 设备类型
			equapment
					.setDevType(StringUtils.isNotBlank(equapment.getDevType()) ? elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLCode("所属单位",
									equapment.getDevType()) : "");
			// 设备状态
			equapment.setDevState(StringUtils.isNotBlank(equapment
					.getDevState()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("设备状态",
							equapment.getDevState()) : "");
		}
	}

	public ElecEquapment findEquapmentById(Integer equapmentID) {
		return equapmentDao.findObjectByID(equapmentID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecEquapment model) {
		equapmentDao.update(model);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(ElecEquapment model) {
		equapmentDao.deleteObjectByIDs(model.getEquapmentID());
	}

	public ArrayList<String> findExcelFiledName() {
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-1");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList<String>> findExcelFiledData(
			ElecEquapment equapment) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-1");
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
		if (StringUtils.isNotBlank(equapment.getJctID())
				&& !equapment.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(equapment.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(equapment.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(equapment.getDevName());
		}
		// 设备类型
		if (StringUtils.isNotBlank(equapment.getDevType())
				&& !equapment.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(equapment.getDevType());
		}
		// 使用日期
		if (equapment.getUseDatef() != null) {
			condition += " and o.useDate>=?";
			paramList.add(equapment.getUseDatef());
		}
		if (equapment.getUseDatet() != null) {
			condition += " and o.useDate<=?";
			paramList.add(equapment.getUseDatet());
		}
		// 设备状态
		if (StringUtils.isNotBlank(equapment.getDevState())
				&& !equapment.getDevState().equals("0")) {
			condition += " and o.devState=?";
			paramList.add(equapment.getDevState());
		}
		// 计划时间
		if (equapment.getPlanDatef() != null) {
			condition += " and o.planDate>=?";
			paramList.add(equapment.getPlanDatef());
		}
		if (equapment.getPlanDatef() != null) {
			condition += " and o.planDate<=?";
			paramList.add(equapment.getPlanDatet());
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.equapmentID", "asc");
		Object[] params = paramList.toArray();
		// 获取结果，表示返回的所有结果
		List list = equapmentDao.findCollectionByConditionNoPageWithExcel(
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
								|| zList.get(j).equals("设备类型")) {
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
