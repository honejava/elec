package cn.haut.elec.service.impl;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecBuildingDao;
import cn.haut.elec.dao.IElecDevPlanDao;
import cn.haut.elec.dao.IElecEquapmentDao;
import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecDevPlan;
import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecDevPlanService;
import cn.haut.elec.utils.GenerateSqlFromExcel;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToArrayUtil;
import cn.haut.elec.utils.StringToListUtils;

@Service(IElecDevPlanService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecDevPlanServiceImpl implements IElecDevPlanService {

	// 注入业务层的数据
	@Resource(name = IElecBuildingDao.SERVICE_NAME)
	private IElecBuildingDao buildingDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入设备购置计划dao
	@Resource(name = IElecDevPlanDao.SERVICE_NAME)
	private IElecDevPlanDao devPlanDao;

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

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecDevPlan model) {
		devPlanDao.save(model);
	}

	public List<ElecDevPlan> findDevPlanListByCondition(ElecDevPlan devplan) {
		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		// 所属单位
		if (StringUtils.isNotBlank(devplan.getJctID())
				&& !devplan.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(devplan.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(devplan.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(devplan.getDevName());
		}
		// 计划时间
		if (devplan.getPlanDatef() != null) {
			condition += " and o.planDate>=?";
			paramList.add(devplan.getPlanDatef());
		}
		if (devplan.getPlanDatet() != null) {
			condition += " and o.planDate<=?";
			paramList.add(devplan.getPlanDatet());
		}
		// 设备类型
		if (StringUtils.isNotBlank(devplan.getDevType())
				&& !devplan.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(devplan.getDevType());
		}
		Object[] params = paramList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.devPlanID", "asc");

		/** 2014-2-27 添加分页 begin */
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());

		List<ElecDevPlan> devPlanList = devPlanDao
				.findCollectionByConditionWithPage(condition, params, orderby,
						pageInfo);

		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		this.convertSystemDDL(devPlanList);
		return devPlanList;
	}

	private void convertSystemDDL(List<ElecDevPlan> devPlanList) {
		for (ElecDevPlan elecDevPlan : devPlanList) {
			// 所刷单位
			elecDevPlan
					.setJctID(StringUtils.isNotBlank(elecDevPlan.getJctID()) ? elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLCode("所属单位",
									elecDevPlan.getJctID()) : "");
			// 设备类型
			elecDevPlan.setDevType(StringUtils.isNotBlank(elecDevPlan
					.getDevType()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位",
							elecDevPlan.getDevType()) : "");
		}
	}

	public ElecDevPlan findDevPlanById(Integer devPlanID) {
		return devPlanDao.findObjectByID(devPlanID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecDevPlan model) {
		devPlanDao.update(model);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteById(Integer devPlanID) {
		devPlanDao.deleteObjectByIDs(devPlanID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public List<String> importData(ElecDevPlan devPlan,
			HttpServletRequest request) {
		File formFile = devPlan.getFiles();
		// 把Excel文件内容转换为一个ArrayList
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		ArrayList<String> errorList = new ArrayList<String>();
		try {
			arrayList = GenerateSqlFromExcel.generateUserSql(formFile);
			// 检验 导入的excel是否符合添加用户的规则
			List<ElecDevPlan> devPlanlist = this.checkImportExcel(arrayList,
					errorList);
			if (errorList != null && errorList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("devPlanlist",
						devPlanlist);
				return errorList;
			}
			// 保存
			devPlanDao.saves((ArrayList<ElecDevPlan>) devPlanlist);

		} catch (Exception e) {
			throw new RuntimeException("格式转换出现错误", e);

		}
		return null;

	}

	// 检测输入的数据是否合格 如果不符合 那么就不保存用户
	private List<ElecDevPlan> checkImportExcel(ArrayList<String[]> arrayList,
			ArrayList<String> errorList) {
		List<ElecDevPlan> devPlanlist = new ArrayList<ElecDevPlan>();
		if (arrayList != null && arrayList.size() > 0) {
			for (int i = 0; i < arrayList.size(); i++) {
				// 存放：登录名 密码 用户姓名 性别 所属单位 联系地址 是否在职 出生日期 职位
				String[] arrays = arrayList.get(i);// 获取每一行的数据
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				ElecDevPlan devPlan = new ElecDevPlan();
				// 1 设备类型 devType
				if (StringUtils.isNotBlank(arrays[0])) {
					// 数据字典转换
					devPlan.setDevType(elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("设备类型", arrays[0]));
				}
				// 2 设备名称 devName
				if (StringUtils.isNotBlank(arrays[1])) {
					devPlan.setDevName(arrays[1]);
				}
				// 3 trademark
				if (StringUtils.isNotBlank(arrays[2])) {
					devPlan.setTrademark(arrays[2]);
				}
				// 4 specType
				if (StringUtils.isNotBlank(arrays[3])) {
					devPlan.setSpecType(arrays[3]);
				}
				// 5 produceHome
				if (StringUtils.isNotBlank(arrays[4])) {
					devPlan.setProduceHome(arrays[4]);
				}
				// 6 quality
				if (StringUtils.isNotBlank(arrays[5])) {
					devPlan.setQuality(Integer.parseInt(arrays[5]));
				}
				// 7 qunit
				if (StringUtils.isNotBlank(arrays[6])) {
					devPlan.setQunit(arrays[6]);
				}
				// 8 useness
				if (StringUtils.isNotBlank(arrays[7])) {
					devPlan.setUseness(arrays[7]);
				}
				// 9 produceArea//
				if (StringUtils.isNotBlank(arrays[8])) {
					devPlan.setProduceArea(arrays[8]);
				}
				// 10 devExpense
				if (StringUtils.isNotBlank(arrays[9])) {
					devPlan.setDevExpense(Double.parseDouble(arrays[9]));
				}
				// 11 useUnit
				if (StringUtils.isNotBlank(arrays[10])) {
					devPlan.setUseUnit(arrays[10]);
				}

				// 12 jctID
				if (StringUtils.isNotBlank(arrays[11])) {
					// 数据字典转换
					devPlan.setJctID(elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("所属单位",
									arrays[11]));
				}
				// 13 planDate
				if (StringUtils.isNotBlank(arrays[12])) {
					try {
						devPlan.setPlanDate(format.parse(arrays[12]));
					} catch (ParseException e) {
						throw new RuntimeException("日期格式转换出现错误", e);
					}
				}

				// 14 adjustPeriod
				if (StringUtils.isNotBlank(arrays[13])) {
					devPlan.setAdjustPeriod(arrays[13]);
				}
				// 15 apunit
				if (StringUtils.isNotBlank(arrays[14])) {
					devPlan.setApunit(arrays[14]);
				}
				// 16 overhaulPeriod
				if (StringUtils.isNotBlank(arrays[15])) {
					devPlan.setOverhaulPeriod(arrays[15]);
				}
				// 17 opunit
				if (StringUtils.isNotBlank(arrays[16])) {
					devPlan.setOpunit(arrays[16]);
				}
				// 18 configure
				if (StringUtils.isNotBlank(arrays[17])) {
					devPlan.setConfigure(arrays[17]);
				}
				devPlanlist.add(devPlan);
			}
		}
		return devPlanlist;
	}

	@Override
	public ArrayList<String> findExcelFiledName() {
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-3");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	@SuppressWarnings("rawtypes")
	@Override
	public ArrayList<ArrayList<String>> findExcelFiledData(ElecDevPlan devplan) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-3");
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
		if (StringUtils.isNotBlank(devplan.getJctID())
				&& !devplan.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(devplan.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(devplan.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(devplan.getDevName());
		}
		// 计划时间
		if (devplan.getPlanDatef() != null) {
			condition += " and o.planDate>=?";
			paramList.add(devplan.getPlanDatef());
		}
		if (devplan.getPlanDatet() != null) {
			condition += " and o.planDate<=?";
			paramList.add(devplan.getPlanDatet());
		}
		// 设备类型
		if (StringUtils.isNotBlank(devplan.getDevType())
				&& !devplan.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(devplan.getDevType());
		}
		Object[] params = paramList.toArray();
		// 排序
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.devPlanID", "asc");
		// 获取结果，表示返回的所有结果
		List list = devPlanDao.findCollectionByConditionNoPageWithExcel(
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

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void devicePlanToDevice(ElecDevPlan devPlan) {
		// 首先获取到购置的计划
		String plantodev = devPlan.getPlantodev();
		String[] ids = StringToArrayUtil.StringToArray(plantodev, ",");
		List<ElecEquapment> equapmentList = new ArrayList<ElecEquapment>();
		List<ElecDevPlan> devPlantList = new ArrayList<ElecDevPlan>();
		for (int i = 0; i < ids.length; i++) {
			ElecDevPlan plan = devPlanDao.findObjectByID(Integer
					.parseInt(ids[i]));
			devPlantList.add(plan);
			// 添加新的设备
			ElecEquapment equapment = new ElecEquapment();
			equapment.setDevName(plan.getDevName());
			equapment.setJctID(plan.getJctID());

			equapment.setDevType(plan.getDevType());
			equapment.setQuality(plan.getQuality());
			equapment.setQunit(plan.getQunit());

			equapment.setDevExpense(plan.getDevExpense());
			equapment.setUseUnit(plan.getUseUnit());

			equapment.setConfigure(plan.getConfigure());
			equapment.setSpecType(plan.getSpecType());
			equapment.setTrademark(plan.getTrademark());

			equapment.setDevState(ElecEquapment.DEV_STATE_NORMAL);
			equapment.setProduceArea(plan.getProduceArea());
			equapment.setProduceHome(plan.getProduceHome());

			equapment.setUseness(plan.getUseness());

			equapment.setOverhaulPeriod(plan.getOverhaulPeriod());
			equapment.setOpunit(plan.getOpunit());

			equapment.setUseDate(new Date());
			equapment.setPlanDate(plan.getPlanDate());

			equapment.setAdjustPeriod(plan.getAdjustPeriod());
			equapment.setApunit(plan.getApunit());

			equapmentList.add(equapment);
		}
		// 保存设备
		equapmentDao.saves(equapmentList);
		// 删除 已经购置的设备计划
		devPlanDao.deleteObjectByCollection(devPlantList);
	}

	public List<ElecDevPlan> findElecDevPlanCount() {
		List<Object[]> list = devPlanDao.findElecDevPlanCount();
		List<ElecDevPlan> formList = this.devPlanListPOToVo(list);
		return formList;
	}

	// 将object对象放入到devPlan对象中的jctName 和jctCount
	private List<ElecDevPlan> devPlanListPOToVo(List<Object[]> list) {
		List<ElecDevPlan> formList = new ArrayList<ElecDevPlan>();
		for (int i = 0; list != null && i < list.size(); i++) {
			Object[] object = (Object[]) list.get(i);
			ElecDevPlan plan = new ElecDevPlan();
			plan.setJctName(object[0].toString());
			plan.setJctCount(object[1].toString());
			formList.add(plan);
		}
		return formList;
	}

}
