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
import cn.haut.elec.dao.IElecFileUploadDao;
import cn.haut.elec.dao.IElecStationDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.domain.ElecFileUpload;
import cn.haut.elec.domain.ElecStation;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecStationService;
import cn.haut.elec.utils.GenerateSqlFromExcel;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToListUtils;

@Service(IElecStationService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecStationServiceImpl implements IElecStationService {

	// 注入业务层的数据
	@Resource(name = IElecStationDao.SERVICE_NAME)
	private IElecStationDao stationDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入站点信息 bug
	@Resource(name = IElecBugDao.SERVICE_NAME)
	private IElecBugDao bugDao;

	// 注入上传文件的dao
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

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecStation elecStation) {
		stationDao.save(elecStation);
	}

	public List<ElecStation> findStationByCondition(ElecStation station) {
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 所属单位
		if (StringUtils.isNotBlank(station.getJctID())
				&& !station.getJctID().equals("0")) {
			condition += " and o.jctID = ?";
			paramsList.add(station.getJctID());
		}
		// 站点名称
		if (StringUtils.isNotBlank(station.getStationName())
				&& !station.getStationName().equals("0")) {
			condition += " and o.stationName=? ";
			paramsList.add(station.getStationName());
		}
		// 站点代号
		if (StringUtils.isNotBlank(station.getStationCode())) {
			condition += " and o.stationCode=? ";
			paramsList.add(station.getStationCode());
		}
		// 站点类别
		if (StringUtils.isNotBlank(station.getStationName())
				&& (!station.getStationType().equals("0"))) {
			condition += " and o.stationType=? ";
			paramsList.add(station.getStationType());
		}
		// 通讯方式
		if (StringUtils.isNotBlank(station.getContactType())) {
			condition += " and o.contactType=? ";
			paramsList.add(station.getContactType());
		}
		// 归属地
		if (StringUtils.isNotBlank(station.getAttributionGround())) {
			condition += " and o.attributionGround=? ";
			paramsList.add(station.getAttributionGround());
		}

		Object[] params = paramsList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.stationID", "asc");

		/** 2014-2-27 添加分页 begin */
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecStation> stationList = stationDao
				.findCollectionByConditionWithPage(condition, params, orderby,
						pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		/** 2014-2-27 添加分页 end */
		// 3：将返回的结果List<ElecUser>，对出现数据字典的项，进行转换，使用数据类型和数据项的编号，获取数据项的值
		this.convertSystemDDL(stationList);
		return stationList;
	}

	private void convertSystemDDL(List<ElecStation> stationList) {
		for (ElecStation station : stationList) {
			// 所属单位
			station.setJctID(StringUtils.isNotBlank(station.getJctID()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位",
							station.getJctID()) : "");
			// 站点类别
			station.setStationType(StringUtils.isNotBlank(station
					.getStationType()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("站点类别",
							station.getStationType()) : "");
		}

	}

	public ElecStation findStationByID(Integer stationID) {
		return stationDao.findObjectByID(stationID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecStation model) {
		stationDao.update(model);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(ElecStation model) {
		// 这里要是删除的话 必须要 级联删除 站点信息 bug 和 bug关联的 上传文件
		// 根据stationID来获取到站点信息的集合【bug】
		String condition = " and o.elecStation.stationID=?";
		Object[] params = { model.getStationID() };
		List<ElecBug> list = bugDao.findCollectionByConditionNoPage(condition,
				params, null);
		if (list != null && list.size() > 0) {
			// 先级连删除这个bug
			for (ElecBug elecBug : list) {
				this.deleteBug(elecBug.getBugID());
			}
		}
		stationDao.deleteObjectByIDs(model.getStationID());
	}

	private void deleteBug(Integer bugID) {
		String condition = " and o.elecBug.bugID=?";
		Object[] params = { bugID };
		// 在根据bugID来查到上传文件的集合
		List<ElecFileUpload> list = fileUploadDao
				.findCollectionByConditionNoPage(condition, params, null);
		if (list != null && list.size() > 0) {
			for (ElecFileUpload fileUpload : list) {
				fileUpload.setElecBug(null);

				fileUploadDao.deleteObjectByIDs(fileUpload.getFileUploadID());
			}
		}
		// 删除这个bug
		ElecBug bug = bugDao.findObjectByID(bugID);
		bug.setElecStation(null);
		bugDao.deleteBug(bug);
	}

	public ArrayList<String> findExcelFiledName() {
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("4-1");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecStation station) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("4-1");
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
		if (StringUtils.isNotBlank(station.getJctID())
				&& !station.getJctID().equals("0")) {
			condition += " and o.jctID = ?";
			paramsList.add(station.getJctID());
		}
		// 站点名称
		if (StringUtils.isNotBlank(station.getStationName())
				&& !station.getStationName().equals("0")) {
			condition += " and o.stationName=? ";
			paramsList.add(station.getStationName());
		}
		// 站点代号
		if (StringUtils.isNotBlank(station.getStationCode())) {
			condition += " and o.stationCode=? ";
			paramsList.add(station.getStationCode());
		}
		// 站点类别
		if (StringUtils.isNotBlank(station.getStationName())
				&& (!station.getStationType().equals("0"))) {
			condition += " and o.stationType=? ";
			paramsList.add(station.getStationType());
		}
		// 通讯方式
		if (StringUtils.isNotBlank(station.getContactType())) {
			condition += " and o.contactType=? ";
			paramsList.add(station.getContactType());
		}
		// 归属地
		if (StringUtils.isNotBlank(station.getAttributionGround())) {
			condition += " and o.attributionGround=? ";
			paramsList.add(station.getAttributionGround());
		}

		Object[] params = paramsList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.stationID", "asc");
		// 获取结果，表示返回的所有结果
		@SuppressWarnings("rawtypes")
		List list = stationDao.findCollectionByConditionNoPageWithExcel(
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
								|| zList.get(j).equals("建筑类型")) {
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
	public List<String> importData(ElecStation station,
			HttpServletRequest request) {
		File formFile = station.getFile();
		// 把Excel文件内容转换为一个ArrayList
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		ArrayList<String> errorList = new ArrayList<String>();
		try {
			arrayList = GenerateSqlFromExcel.generateUserSql(formFile);
			// 检验 导入的excel是否符合添加检测台的规则
			List<ElecStation> stationlist = this.checkImportExcel(arrayList,
					errorList);
			if (errorList != null && errorList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("buildlist",
						stationlist);
				return errorList;
			}
			// 保存
			stationDao.saves((ArrayList<ElecStation>) stationlist);

		} catch (Exception e) {
			throw new RuntimeException("格式转换出现错误", e);

		}
		return null;

	}

	// 检测输入的数据是否合格 如果不符合 那么就不保存检测台
	private List<ElecStation> checkImportExcel(ArrayList<String[]> arrayList,
			ArrayList<String> errorList) throws Exception {
		List<ElecStation> stationlist = new ArrayList<ElecStation>();
		if (arrayList != null && arrayList.size() > 0) {
			for (int i = 0; i < arrayList.size(); i++) {

				String[] arrays = arrayList.get(i);// 获取每一行的数据
				ElecStation station = new ElecStation();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				// 检测工作单位
				if (StringUtils.isNotBlank(arrays[0])) {
					// 工作单位的数据字典的转换
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("所属单位", arrays[0]);
					station.setJctID(ddlCode != null ? ddlCode : "");
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (0 + 1) + "列，工作单位为空");
				}
				// 站点名称
				if (StringUtils.isNotBlank(arrays[1])) {
					station.setStationName(arrays[1]);
				}
				// 站点代号
				if (StringUtils.isNotBlank(arrays[2])) {
					station.setStationCode(arrays[2]);
				}
				// 站点开始时间
				if (StringUtils.isNotBlank(arrays[3])) {
					station.setUseStartDate(format.parse(arrays[3]));
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (3 + 1) + "列，建筑层数为空");
				}
				// 站点地点
				if (StringUtils.isNotBlank(arrays[4])) {
					station.setJcfrequency(arrays[4]);
				}
				// 站点厂家
				if (StringUtils.isNotBlank(arrays[5])) {
					station.setProduceHome(arrays[5]);
				}
				// 联系方式
				if (StringUtils.isNotBlank(arrays[6])) {
					station.setContactType(arrays[6]);
				}
				// 站点类型
				if (StringUtils.isNotBlank(arrays[7])) {
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("站点类别", arrays[7]);
					station.setStationType(ddlCode);
				}
				// 归属地
				if (StringUtils.isNotBlank(arrays[8])) {
					station.setAttributionGround(arrays[8]);
				}
				// 评论
				if (StringUtils.isNotBlank(arrays[9])) {
					station.setComment(arrays[11]);
				}
				stationlist.add(station);
			}
		}
		return stationlist;
	}

	// 找到 每个部门的人员数量
	public List<ElecStation> findElecStationCount() {
		List<Object[]> list = stationDao.findElecStationCount();
		List<ElecStation> formList = this.stationListPOToVo(list);
		return formList;
	}

	// 将object对象放入到build对象中的jctName 和jctCount
	private List<ElecStation> stationListPOToVo(List<Object[]> list) {
		List<ElecStation> formList = new ArrayList<ElecStation>();
		for (int i = 0; list != null && i < list.size(); i++) {
			Object[] object = (Object[]) list.get(i);
			ElecStation station = new ElecStation();
			station.setJctName(object[0].toString());
			station.setJctCount(object[1].toString());
			formList.add(station);
		}
		return formList;
	}

}
