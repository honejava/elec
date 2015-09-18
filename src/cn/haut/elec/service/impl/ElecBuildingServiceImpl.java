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

import cn.haut.elec.dao.IElecBuildingDao;
import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecBuilding;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.service.IElecBuildingService;
import cn.haut.elec.utils.GenerateSqlFromExcel;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToListUtils;

@Service(IElecBuildingService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecBuildingServiceImpl implements IElecBuildingService {

	// 注入业务层的数据
	@Resource(name = IElecBuildingDao.SERVICE_NAME)
	private IElecBuildingDao buildingDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	@Override
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecBuilding elecbuilding) {
		buildingDao.save(elecbuilding);
	}

	public List<ElecBuilding> findBuildByCondition(ElecBuilding build) {
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 所属单位
		if (StringUtils.isNotBlank(build.getJctID())
				&& !build.getJctID().equals("0")) {
			condition += " and o.jctID = ?";
			paramsList.add(build.getJctID());
		}
		// 建筑名称
		if (StringUtils.isNotBlank(build.getBuildName())) {
			condition += " and o.buildName=?";
			paramsList.add(build.getBuildName());
		}
		// 建筑类型
		if (StringUtils.isNotBlank(build.getBuildType())
				&& !build.getBuildType().equals("0")) {
			condition += " and o.buildType = ?";
			paramsList.add(build.getBuildType());
		}
		// 建筑面积
		if (build.getBuildArea() != 0.0) {
			condition += " and o.buildArea = ?";
			paramsList.add(build.getBuildArea());
		}
		Object[] params = paramsList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.buildingID", "asc");

		/** 2014-2-27 添加分页 begin */
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecBuilding> buildList = buildingDao
				.findCollectionByConditionWithPage(condition, params, orderby,
						pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		/** 2014-2-27 添加分页 end */
		// 3：将返回的结果List<ElecUser>，对出现数据字典的项，进行转换，使用数据类型和数据项的编号，获取数据项的值
		this.convertSystemDDL(buildList);
		return buildList;
	}

	private void convertSystemDDL(List<ElecBuilding> buildList) {
		for (ElecBuilding building : buildList) {
			// 所属单位
			building.setJctID(StringUtils.isNotBlank(building.getJctID()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位",
							building.getJctID()) : "");
			// 建筑类型
			building.setBuildType(StringUtils.isNotBlank(building
					.getBuildType()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("建筑类型",
							building.getBuildType()) : "");
		}

	}

	public ElecBuilding findBuildByID(Integer buildingID) {
		ElecBuilding building = buildingDao.findObjectByID(buildingID);
		return building;
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecBuilding model) {
		buildingDao.update(model);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(ElecBuilding model) {
		buildingDao.deleteObjectByIDs(model.getBuildingID());
	}

	public ArrayList<String> findExcelFiledName() {
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("3-1");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	public ArrayList<ArrayList<String>> findExcelFiledData(ElecBuilding build) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("3-1");
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
		if (StringUtils.isNotBlank(build.getJctID())
				&& !build.getJctID().equals("0")) {
			condition += " and o.jctID = ?";
			paramsList.add(build.getJctID());
		}
		// 建筑名称
		if (StringUtils.isNotBlank(build.getBuildName())) {
			condition += " and o.buildName=?";
			paramsList.add(build.getBuildName());
		}
		// 建筑类型
		if (StringUtils.isNotBlank(build.getBuildType())
				&& !build.getBuildType().equals("0")) {
			condition += " and o.buildType = ?";
			paramsList.add(build.getBuildType());
		}
		// 建筑面积
		if (build.getBuildArea() != 0.0) {
			condition += " and o.buildArea = ?";
			paramsList.add(build.getBuildArea());
		}
		Object[] params = paramsList.toArray();
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.buildingID", "asc");
		// 获取结果，表示返回的所有结果
		@SuppressWarnings("rawtypes")
		List list = buildingDao.findCollectionByConditionNoPageWithExcel(
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
	public List<String> importData(ElecBuilding build,
			HttpServletRequest request) {
		File formFile = build.getFile();
		// 把Excel文件内容转换为一个ArrayList
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		ArrayList<String> errorList = new ArrayList<String>();
		try {
			arrayList = GenerateSqlFromExcel.generateUserSql(formFile);
			// 检验 导入的excel是否符合添加检测台的规则
			List<ElecBuilding> buildlist = this.checkImportExcel(arrayList,
					errorList);
			if (errorList != null && errorList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("buildlist",
						buildlist);
				return errorList;
			}
			// 保存
			buildingDao.saves((ArrayList<ElecBuilding>) buildlist);

		} catch (Exception e) {
			throw new RuntimeException("格式转换出现错误", e);

		}
		return null;

	}

	// 检测输入的数据是否合格 如果不符合 那么就不保存检测台
	private List<ElecBuilding> checkImportExcel(ArrayList<String[]> arrayList,
			ArrayList<String> errorList) throws Exception {
		List<ElecBuilding> buildlist = new ArrayList<ElecBuilding>();
		if (arrayList != null && arrayList.size() > 0) {
			for (int i = 0; i < arrayList.size(); i++) {
				// 存放：jctID buildName buildType buildLayer buildArea
				// buildStartDate extendBuildDate extendBuildArea dxDate
				// buildExpense useDate comment
				String[] arrays = arrayList.get(i);// 获取每一行的数据
				ElecBuilding building = new ElecBuilding();
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				// 检测工作单位
				if (StringUtils.isNotBlank(arrays[0])) {
					// 工作单位的数据字典的转换
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("所属单位", arrays[0]);
					building.setJctID(ddlCode != null ? ddlCode : "");
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (0 + 1) + "列，工作单位为空");
				}
				// 建筑物名称
				if (StringUtils.isNotBlank(arrays[1])) {
					building.setBuildName(arrays[1]);
				}
				// 建筑类型
				if (StringUtils.isNotBlank(arrays[2])) {
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("建筑类型", arrays[2]);
					building.setBuildType(ddlCode);
				}
				// 建筑层数
				if (StringUtils.isNotBlank(arrays[3])) {
					building.setBuildLayer(Integer.parseInt(arrays[3]));
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (3 + 1) + "列，建筑层数为空");
				}
				// 建筑面积
				if (StringUtils.isNotBlank(arrays[4])) {
					building.setBuildArea(Double.parseDouble(arrays[4]));
				}
				// 建筑时间
				if (StringUtils.isNotBlank(arrays[5])) {
					building.setBuildStartDate(format.parse(arrays[5]));
				}
				// 建筑扩建时间
				if (StringUtils.isNotBlank(arrays[6])) {
					building.setExtendBuildDate(format.parse(arrays[6]));
				}
				// 建筑扩建面积
				if (StringUtils.isNotBlank(arrays[7])) {
					building.setExtendBuildArea(Double.parseDouble(arrays[7]));
				}
				// 大修时间
				if (StringUtils.isNotBlank(arrays[8])) {
					building.setDxDate(format.parse(arrays[8]));
				}
				// zaojia
				if (StringUtils.isNotBlank(arrays[9])) {
					building.setBuildExpense(Double.parseDouble(arrays[9]));
				}
				// 使用时间
				if (StringUtils.isNotBlank(arrays[10])) {
					building.setUseDate(format.parse(arrays[10]));
				}
				// 评论
				if (StringUtils.isNotBlank(arrays[11])) {
					building.setComment(arrays[11]);
				}
				buildlist.add(building);
			}
		}
		return buildlist;
	}

	// 找到 每个部门的人员数量
	public List<ElecBuilding> findElecBuildingCount() {
		List<Object[]> list = buildingDao.findElecBuildingCount();
		List<ElecBuilding> formList = this.buildingListPOToVo(list);
		return formList;
	}

	// 将object对象放入到build对象中的jctName 和jctCount
	private List<ElecBuilding> buildingListPOToVo(List<Object[]> list) {
		List<ElecBuilding> formList = new ArrayList<ElecBuilding>();
		for (int i = 0; list != null && i < list.size(); i++) {
			Object[] object = (Object[]) list.get(i);
			ElecBuilding build = new ElecBuilding();
			build.setJctName(object[0].toString());
			build.setJctCount(object[1].toString());
			formList.add(build);
		}
		return formList;
	}
}
