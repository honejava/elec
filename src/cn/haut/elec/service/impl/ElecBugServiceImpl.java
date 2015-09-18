package cn.haut.elec.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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

import cn.haut.elec.dao.IElecBugDao;
import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecStationDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.domain.ElecStation;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecBugService;
import cn.haut.elec.utils.GenerateSqlFromExcel;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToListUtils;

@Service(IElecBugService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecBugServiceImpl implements IElecBugService {

	// 注入业务层的数据
	@Resource(name = IElecBugDao.SERVICE_NAME)
	private IElecBugDao bugDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入站点的dao
	@Resource(name = IElecStationDao.SERVICE_NAME)
	private IElecStationDao stationDao;

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

	public List<ElecBug> findBugByCondition(ElecBug bug) {
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 所属单位
		if (bug.getElecStation() != null
				&& StringUtils.isNotBlank(bug.getElecStation().getJctID())
				&& !bug.getElecStation().getJctID().equals("0")) {
			condition += " and o.elecStation.jctID=? ";
			paramsList.add(bug.getElecStation().getJctID());
		}
		// 站点名称
		if (bug.getElecStation() != null
				&& bug.getElecStation().getStationName() != null
				&& !bug.getElecStation().getStationName().equals("0")) {
			condition += " and o.elecStation.stationName=?";
			paramsList.add(bug.getElecStation().getStationName());
		}
		// 查询时间
		if (StringUtils.isNotBlank(bug.getSbYearFrom())
				&& !bug.getSbYearFrom().equals("0")) {
			condition += " and o.sbYear>=?";
			paramsList.add(bug.getSbYearFrom());
		}
		if (StringUtils.isNotBlank(bug.getSbYearTo())
				&& !bug.getSbYearTo().equals("0")) {
			condition += " and o.sbYear<=?";
			paramsList.add(bug.getSbYearTo());
		}
		if (StringUtils.isNotBlank(bug.getSbMonthFrom())
				&& !bug.getSbMonthFrom().equals("0")) {
			condition += " and o.sbMonth>=?";
			paramsList.add(bug.getSbMonthFrom());
		}
		if (StringUtils.isNotBlank(bug.getSbMonthTo())
				&& !bug.getSbMonthTo().equals("0")) {
			condition += " and o.sbMonth<=?";
			paramsList.add(bug.getSbMonthTo());
		}
		// 故障类型
		if (StringUtils.isNotBlank(bug.getBugType())
				&& !bug.getBugType().equals("0")) {
			condition += " and o.bugType=?";
			paramsList.add(bug.getBugType());
		}
		// 故障时间
		if (bug.getBugTimeFrom() != null && !bug.getSbMonthFrom().equals("0")) {
			condition += " and o.occurDate>=?";
			paramsList.add(bug.getBugTimeFrom());
		}
		if (bug.getBugTimeTo() != null && !bug.getBugTimeTo().equals("0")) {
			condition += " and o.resolveDate<=?";
			paramsList.add(bug.getBugTimeTo());
		}
		// 站点代号
		if (bug.getElecStation() != null
				&& StringUtils
						.isNotBlank(bug.getElecStation().getStationCode())) {
			condition += " and o.elecStation.stationCode=?";
			paramsList.add(bug.getElecStation().getStationCode());
		}
		// 站点类型
		if (bug.getElecStation() != null
				&& StringUtils
						.isNotBlank(bug.getElecStation().getStationType())
				&& !bug.getElecStation().getStationType().equals("0")) {
			condition += " and o.elecStation.stationType=?";
			paramsList.add(bug.getElecStation().getStationType());
		}
		// 故障描述
		if (StringUtils.isNotBlank(bug.getBugDescribe())) {
			condition += " and o.bugDescribe=?";
			paramsList.add(bug.getBugDescribe());
		}
		Object[] params = paramsList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.bugID", "asc");
		/** 2014-2-27 添加分页 begin */
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecBug> bugList = bugDao.findCollectionByConditionWithPage(
				condition, params, orderby, pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		/** 2014-2-27 添加分页 end */
		// 这里的bugList含有 未初始化的数据 但是进过转化后 就变成已经初始化的数据了 不用现实初始化数据了

		for (ElecBug elecBug : bugList) {
			this.convertSystemDDL(elecBug);
		}
		// this.convertSystemDDL(bugList);
		this.setBugTime(bugList);
		return bugList;
	}

	public List<ElecStation> findAllStation() {
		return stationDao.findCollectionByConditionNoPage(null, null, null);
	}

	public ElecBug findBugById(Integer bugID) {
		ElecBug bug = bugDao.findObjectByID(bugID);
		// 初始化 elecStation
		this.convertSystemDDL(bug);
		return bug;
	}

	// 转换数据字典的内容
	@SuppressWarnings("unused")
	private void convertSystemDDL(List<ElecBug> bugList) {
		// 所属单位
		// TODO 这里 为什么相同的stationID会出现错误呢？？？？？？？？？？？
		for (ElecBug elecBug : bugList) {
			String jctID = elecBug.getElecStation().getJctID();
			elecBug.getElecStation()
					.setJctID(
							StringUtils.isNotBlank(elecBug.getElecStation()
									.getJctID()) ? elecSystemDDLDao
									.findSystemDDLByKeywordAndDDLCode("所属单位",
											elecBug.getElecStation().getJctID())
									: "");
			// 故障类型
			elecBug.setBugType(StringUtils.isNotBlank(elecBug.getBugType()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("故障类型",
							elecBug.getBugType()) : "");
			// 站点类别
			elecBug.getElecStation().setStationType(
					StringUtils.isNotBlank(elecBug.getElecStation()
							.getStationType()) ? elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLCode("站点类别", elecBug
									.getElecStation().getStationType()) : "");
		}

	}

	public void convertSystemDDL(ElecBug elecBug) {
		// 故障类型
		elecBug.setBugType(StringUtils.isNotBlank(elecBug.getBugType()) ? elecSystemDDLDao
				.findSystemDDLByKeywordAndDDLCode("故障类型", elecBug.getBugType())
				: "");
		if (elecBug.getElecStation() != null) {
			// 站点类别
			try {
				Integer.parseInt(elecBug.getElecStation().getStationType());
				elecBug.getElecStation().setStationType(
						StringUtils.isNotBlank(elecBug.getElecStation()
								.getStationType()) ? elecSystemDDLDao
								.findSystemDDLByKeywordAndDDLCode("站点类别",
										elecBug.getElecStation()
												.getStationType()) : "");
			} catch (Exception e) {
			}
			// 所属单位
			try {
				String jctID = elecBug.getElecStation().getJctID();
				Integer.parseInt(jctID);
				elecBug.getElecStation().setJctID(
						StringUtils.isNotBlank(elecBug.getElecStation()
								.getJctID()) ? elecSystemDDLDao
								.findSystemDDLByKeywordAndDDLCode("所属单位",
										elecBug.getElecStation().getJctID())
								: "");
			} catch (Exception e) {
			}
		}

	}

	@SuppressWarnings("deprecation")
	private void setBugTime(List<ElecBug> bugList) {
		for (ElecBug bug : bugList) {
			int om = bug.getOccurDate().getMonth() + 1;
			int od = bug.getOccurDate().getDate();

			int rm = bug.getResolveDate().getMonth() + 1;
			int rd = bug.getResolveDate().getDate();

			double hour = (rm - om) * 30 * 24 + (rd - od) * 24;
			bug.setHour(hour);
		}

	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecBug model) {
		// 获取到级联的station数据
		ElecStation station = stationDao.findObjectByID(model.getElecStation()
				.getStationID());
		model.setElecStation(station);

		bugDao.update(model);
	}

	public List<String> findDistinctStationNameList() {
		return bugDao.findDistinctStationNameList();
	}

	public String findSumBugHour() {
		List<ElecBug> list = bugDao.findCollectionByConditionNoPage(null, null,
				null);
		return this.getSumBugHour(list);
	}

	@SuppressWarnings("deprecation")
	private String getSumBugHour(List<ElecBug> list) {
		double sumHour = 0;
		for (ElecBug bug : list) {
			int om = bug.getOccurDate().getMonth() + 1;
			int od = bug.getOccurDate().getDate();

			int rm = bug.getResolveDate().getMonth() + 1;
			int rd = bug.getResolveDate().getDate();

			sumHour += (rm - om) * 30 * 24 + (rd - od) * 24;
		}
		return String.valueOf(sumHour);
	}

	public ArrayList<String> findExcelFiledName() {
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("4-2");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecBug bug) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("4-2");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		// 获取导出设置的中导出的英文名称
		String eName = elecExportFields.getExpFieldName();
		// 将导出的英文字段名称中的#号替换成逗号，即查询的条件
		String selectCondition = eName.replace("#", ",");

		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 所属单位
		if (bug.getElecStation() != null
				&& StringUtils.isNotBlank(bug.getElecStation().getJctID())
				&& !bug.getElecStation().getJctID().equals("0")) {
			condition += " and o.elecStation.jctID=? ";
			paramsList.add(bug.getElecStation().getJctID());
		}
		// 站点名称
		if (bug.getElecStation() != null
				&& bug.getElecStation().getStationName() != null
				&& !bug.getElecStation().getStationName().equals("0")) {
			condition += " and o.elecStation.stationName=?";
			paramsList.add(bug.getElecStation().getStationName());
		}
		// 查询时间
		if (StringUtils.isNotBlank(bug.getSbMonthFrom())
				&& !bug.getSbMonthFrom().equals("0")) {
			condition += " and o.sbMonthFrom>?";
			paramsList.add(bug.getSbMonthFrom());
		}
		if (StringUtils.isNotBlank(bug.getSbMonthTo())
				&& !bug.getSbMonthTo().equals("0")) {
			condition += " and o.sbMonthTo<?";
			paramsList.add(bug.getSbMonthTo());
		}
		if (StringUtils.isNotBlank(bug.getSbYearFrom())
				&& !bug.getSbYearFrom().equals("0")) {
			condition += " and o.sbYearFrom>?";
			paramsList.add(bug.getSbMonthFrom());
		}
		if (StringUtils.isNotBlank(bug.getSbYearTo())
				&& !bug.getSbMonthTo().equals("0")) {
			condition += " and o.sbTearTo<?";
			paramsList.add(bug.getSbYearTo());
		}
		// 故障类型
		if (StringUtils.isNotBlank(bug.getBugType())
				&& !bug.getBugType().equals("0")) {
			condition += " and o.bugType=?";
			paramsList.add(bug.getBugType());
		}
		// 故障时间
		if (bug.getBugTimeFrom() != null && !bug.getSbMonthFrom().equals("0")) {
			condition += " and o.bugTimeFrom>?";
			paramsList.add(bug.getBugTimeFrom());
		}
		if (bug.getBugTimeFrom() != null && !bug.getBugTimeTo().equals("0")) {
			condition += " and o.bugTimeTo<?";
			paramsList.add(bug.getBugTimeTo());
		}
		// 站点代号
		if (bug.getElecStation() != null
				&& StringUtils
						.isNotBlank(bug.getElecStation().getStationCode())) {
			condition += " and o.elecStation.stationCode=?";
			paramsList.add(bug.getElecStation().getStationCode());
		}
		// 站点类型
		if (bug.getElecStation() != null
				&& StringUtils
						.isNotBlank(bug.getElecStation().getStationType())
				&& !bug.getElecStation().getStationType().equals("0")) {
			condition += " and o.elecStation.stationType=?";
			paramsList.add(bug.getElecStation().getStationType());
		}
		Object[] params = paramsList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.bugID", "asc");
		// 获取结果，表示返回的所有结果
		@SuppressWarnings("rawtypes")
		List list = bugDao.findCollectionByConditionNoPageWithExcel(condition,
				params, orderby, selectCondition);
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
								|| zList.get(j).equals("故障类型")) {
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
	public List<String> importData(ElecBug bug, HttpServletRequest request) {
		File formFile = bug.getFile();
		// 把Excel文件内容转换为一个ArrayList
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		ArrayList<String> errorList = new ArrayList<String>();
		try {
			arrayList = GenerateSqlFromExcel.generateUserSql(formFile);
			// 检验 导入的excel是否符合添加检测台的规则
			List<ElecBug> buglist = this.checkImportExcel(arrayList, errorList);
			if (errorList != null && errorList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("buglist",
						buglist);
				return errorList;
			}
			// 保存
			bugDao.saves((ArrayList<ElecBug>) buglist);

		} catch (Exception e) {
			throw new RuntimeException("格式转换出现错误", e);

		}
		return null;

	}

	// 检测输入的数据是否合格 如果不符合 那么就不保存检测台
	private List<ElecBug> checkImportExcel(ArrayList<String[]> arrayList,
			ArrayList<String> errorList) throws Exception {
		List<ElecBug> buglist = new ArrayList<ElecBug>();
		if (arrayList != null && arrayList.size() > 0) {
			for (int i = 0; i < arrayList.size(); i++) {
				String[] arrays = arrayList.get(i);// 获取每一行的数据
				ElecBug bug = new ElecBug();
				ElecStation station = new ElecStation();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				// 站点名称
				if (StringUtils.isNotBlank(arrays[0])) {
					station.setStationName(arrays[0]);
				}
				// 站点代号
				if (StringUtils.isNotBlank(arrays[1])) {
					station.setStationCode(arrays[1]);
				}
				// 工作单位
				if (StringUtils.isNotBlank(arrays[2])) {
					// 工作单位的数据字典的转换
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("所属单位", arrays[2]);
					station.setJctID(ddlCode != null ? ddlCode : "");
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (0 + 1) + "列，工作单位为空");
				}
				// 故障类型
				if (StringUtils.isNotBlank(arrays[3])) {
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("故障类型", arrays[3]);
					bug.setBugType(ddlCode);
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (3 + 1) + "列，建筑层数为空");
				}
				// 故障时间
				if (StringUtils.isNotBlank(arrays[4])) {
					bug.setOccurDate(format.parse(arrays[4]));
				}
				// 处理时间
				if (StringUtils.isNotBlank(arrays[5])) {
					bug.setResolveDate(format.parse(arrays[5]));
				}
				// 故障原因
				if (StringUtils.isNotBlank(arrays[6])) {
					bug.setBugReason(arrays[6]);
				}
				// 处理方法
				if (StringUtils.isNotBlank(arrays[7])) {
					bug.setResolveMethod(arrays[7]);
				}
				// 备注
				if (StringUtils.isNotBlank(arrays[8])) {
					bug.setComment(arrays[8]);
				}
				// 故障描述
				if (StringUtils.isNotBlank(arrays[9])) {
					bug.setBugDescribe(arrays[9]);
				}
				// 找到关联的那个stationID

				String condition = " and o.stationName=? and o.stationCode=? ";
				Object[] params = { station.getStationName(),
						station.getStationCode() };
				List<ElecStation> list = stationDao
						.findCollectionByConditionNoPage(condition, params,
								null);
				bug.setElecStation(list != null && list.size() > 0 ? list
						.get(0) : null);
				buglist.add(bug);
			}
		}
		return buglist;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public boolean save(ElecBug bug) {
		// 找到关联的那个stationID
		String condition = "and o.jctID=? and o.stationName=? and o.stationCode=? ";
		Object[] params = { bug.getElecStation().getJctID(),
				bug.getElecStation().getStationName(),
				bug.getElecStation().getStationCode() };
		List<ElecStation> list = stationDao.findCollectionByConditionNoPage(
				condition, params, null);
		if (list != null && list.size() > 0) {
			bug.setElecStation(list.get(0));
			bugDao.save(bug);
			return true;
		}
		return false;
	}
}
