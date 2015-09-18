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
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.domain.ElecAdjust;
import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.domain.ElecFileUpload;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecAdjustService;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToArrayUtil;
import cn.haut.elec.utils.StringToListUtils;

@Service(IElecAdjustService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecAdjustServiceImpl implements IElecAdjustService {

	// 注入业务层的数据
	@Resource(name = IElecAdjustDao.SERVICE_NAME)
	private IElecAdjustDao adjustDao;
	// 注入数据字典的数据
	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	// 注入导出elecExportFieldsDao
	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	// 注入站点的dao
	@Resource(name = IElecEquapmentDao.SERVICE_NAME)
	private IElecEquapmentDao equapmentDao;

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

	@Override
	public List<ElecAdjust> findAdjustByCondition(ElecAdjust adjust) {
		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		// 所属单位
		if (StringUtils.isNotBlank(adjust.getJctID())
				&& !adjust.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(adjust.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(adjust.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(adjust.getDevName());
		}
		// 校准状态
		if (StringUtils.isNotBlank(adjust.getIsHaving())
				&& !adjust.getIsHaving().equals("0")) {
			condition += " and o.isHaving=?";
			paramList.add(adjust.getIsHaving());
		}
		// 设备类型
		if (StringUtils.isNotBlank(adjust.getDevType())
				&& !adjust.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(adjust.getDevType());
		}
		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put(" o.adjustID", "asc");
		Object[] params = paramList.toArray();

		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecAdjust> adjustList = adjustDao
				.findCollectionByConditionWithPage(condition, params, orderby,
						pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		this.convertSystemDDL(adjustList);

		return adjustList;
	}

	private void convertSystemDDL(List<ElecAdjust> adjustList) {
		for (ElecAdjust adjust : adjustList) {
			// 所刷单位
			adjust.setJctID(StringUtils.isNotBlank(adjust.getJctID()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("所属单位", adjust.getJctID())
					: "");
			// 校准状态
			adjust.setIsHaving(StringUtils.isNotBlank(adjust.getIsHaving()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("校准状态",
							adjust.getIsHaving()) : "");
			// 设备类型
			adjust.setDevType(StringUtils.isNotBlank(adjust.getDevType()) ? elecSystemDDLDao
					.findSystemDDLByKeywordAndDDLCode("设备类型",
							adjust.getDevType()) : "");
		}
	}

	@Override
	public List<ElecEquapment> findEquapmentByCondition(ElecAdjust adjust) {
		String condition = "";
		List<Object> paramList = new ArrayList<Object>();
		if (StringUtils.isNotBlank(adjust.getDevType())
				&& !adjust.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(adjust.getDevType());
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
	public void moreUpdate(ElecAdjust elecAdjust) {
		String[] ids = StringToArrayUtil.StringToArray(elecAdjust.getJctID(),
				",");
		List<ElecAdjust> adjustList = new ArrayList<ElecAdjust>();
		for (int i = 0; i < ids.length; i++) {
			// 获取设备信息
			ElecEquapment equapment = equapmentDao.findObjectByID(Integer
					.parseInt(ids[i]));
			// 添加校准
			ElecAdjust adjust = new ElecAdjust();

			adjust.setEquapmentID(equapment.getEquapmentID());
			adjust.setJctID(equapment.getJctID());
			adjust.setDevName(equapment.getDevName());

			adjust.setAdjustPeriod(equapment.getAdjustPeriod());
			adjust.setApunit(equapment.getApunit());

			adjust.setUseDate(equapment.getUseDate());
			adjust.setDevType(equapment.getDevType());

			adjust.setStartDate(elecAdjust.getStartDate());
			adjust.setIsHaving(ElecAdjust.ADJUST_ISHAVING);

			adjust.setComment(elecAdjust.getComment());
			adjust.setRecord(elecAdjust.getRecord());

			adjustList.add(adjust);
		}
		adjustDao.saves(adjustList);
	}

	public List<ElecAdjust> findAdjustHistory() {
		String condition = " and o.isHaving=?";
		Object[] params = { "2" };
		List<ElecAdjust> list = adjustDao.findCollectionByConditionNoPage(
				condition, params, null);
		this.convertSystemDDL(list);
		return list;
	}

	public ElecAdjust findAdjustById(Integer adjustID) {
		return adjustDao.findObjectByID(adjustID);
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(ElecAdjust adjust) {
		// 级联删除 上传文件
		String condition = " and o.adjustID is not null ";
		List<Object> paramList = new ArrayList<Object>();
		if (adjust.getAdjustID() != null) {
			condition += " and o.adjustID=?";
			paramList.add(adjust.getAdjustID());
		}
		Object[] params = paramList.toArray();
		List<ElecFileUpload> list = fileUploadDao
				.findCollectionByConditionNoPage(condition, params, null);
		if (list != null && list.size() > 0) {
			// 删除 这个 校准小 上传的文件
			fileUploadDao.deleteObjectByCollection(list);
		}
		adjustDao.deleteObjectByIDs(adjust.getAdjustID());
	}

	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(Integer adjustID, String comment, String record) {
		ElecAdjust adjust = adjustDao.findObjectByID(adjustID);
		adjust.setComment(comment);
		adjust.setRecord(record);
	}

	public ArrayList<String> findExcelFiledName() {
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-2");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList<String>> findExcelFiledData(ElecAdjust adjust) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("1-2");
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
		if (StringUtils.isNotBlank(adjust.getJctID())
				&& !adjust.getJctID().equals("0")) {
			condition += " and o.jctID=?";
			paramList.add(adjust.getJctID());
		}
		// 设备名称
		if (StringUtils.isNotBlank(adjust.getDevName())) {
			condition += " and o.devName=?";
			paramList.add(adjust.getDevName());
		}
		// 校准状态
		if (StringUtils.isNotBlank(adjust.getIsHaving())
				&& !adjust.getIsHaving().equals("0")) {
			condition += " and o.isHaving=?";
			paramList.add(adjust.getIsHaving());
		}
		// 设备类型
		if (StringUtils.isNotBlank(adjust.getDevType())
				&& !adjust.getDevType().equals("0")) {
			condition += " and o.devType=?";
			paramList.add(adjust.getDevType());
		}
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.adjustID", "asc");
		Object[] params = paramList.toArray();
		// 获取结果，表示返回的所有结果
		List list = adjustDao.findCollectionByConditionNoPageWithExcel(
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
								|| zList.get(j).equals("校准状态")) {
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
	public void save(ElecAdjust adjust) {
		// 添加校准
		ElecEquapment equapment = equapmentDao.findObjectByID(adjust
				.getEquapmentID());
		adjust.setEquapmentID(equapment.getEquapmentID());
		adjust.setJctID(equapment.getJctID());
		adjust.setDevName(equapment.getDevName());

		adjust.setAdjustPeriod(equapment.getAdjustPeriod());
		adjust.setApunit(equapment.getApunit());

		adjust.setUseDate(equapment.getUseDate());
		adjust.setDevType(equapment.getDevType());

		adjust.setIsHaving(ElecAdjust.ADJUST_ISHAVING);
		adjustDao.save(adjust);
	}
}
