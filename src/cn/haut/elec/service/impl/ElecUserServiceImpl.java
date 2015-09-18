package cn.haut.elec.service.impl;

import java.io.File;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
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

import cn.haut.elec.dao.IElecExportFieldsDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.dao.IElecUserDao;
import cn.haut.elec.domain.ElecExportFields;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecUserService;
import cn.haut.elec.utils.GenerateSqlFromExcel;
import cn.haut.elec.utils.MD5keyBean;
import cn.haut.elec.utils.PageInfo;
import cn.haut.elec.utils.StringToListUtils;

/**
 * 相当于spring容器中定义： <bean id="cn.haut.elec.service.impl.ElecTextServiceImpl"
 * class="cn.haut.elec.service.impl.ElecTextServiceImpl"/>
 */
@Service(IElecUserService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecUserServiceImpl implements IElecUserService {

	@Resource(name = IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;

	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	@Resource(name = IElecExportFieldsDao.SERVICE_NAME)
	private IElecExportFieldsDao elecExportFieldsDao;

	/**
	 * @Name: findUserListByCondition
	 * @Description: 查询符合条件的用户
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: ElecUser elecUser
	 * @Return: List<ElecUser>
	 */

	/*
	 * public List<ElecUser> findUserListByCondition(ElecUser elecUser) {
	 * List<ElecUser> userlist = null; if (elecUser != null) {// 带有条件的查询 String
	 * condition = ""; List<Object> paramlist = new ArrayList<Object>(); if
	 * (StringUtils.isNotEmpty(elecUser.getUserName())) { condition +=
	 * " and userName like ?"; paramlist.add("%" + elecUser.getUserName() +
	 * "%"); } if (StringUtils.isNotEmpty(elecUser.getJctID())) { condition +=
	 * " and jctId = ?"; paramlist.add(elecUser.getJctID()); } if
	 * (elecUser.getOnDutyDateBegin() != null) { condition +=
	 * " and onDutyDate > ?"; paramlist.add(elecUser.getOnDutyDateBegin()); } if
	 * (elecUser.getOnDutyDateEnd() != null) { condition +=
	 * " and onDutyDate < ?"; paramlist.add(elecUser.getOnDutyDateEnd()); }
	 * Object[] params = paramlist.toArray();
	 * 
	 * Map<String, String> orderby = null; // orderby.put("  o.userId", "asc");
	 * userlist = elecUserDao.findCollectionByConditionNoPage(condition, params,
	 * orderby); } else { userlist =
	 * elecUserDao.findCollectionByConditionNoPage(null, null, null); } return
	 * userlist; }
	 */
	// 带分页的用户查询
	public List<ElecUser> findUserListByCondition(ElecUser elecUser) {
		// 查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 用户姓名
		String userName = elecUser.getUserName();
		if (StringUtils.isNotBlank(userName)) {
			condition += " and o.userName like ?";
			paramsList.add("%" + userName + "%");
		}
		// 所属单位
		if (StringUtils.isNotBlank(elecUser.getJctID())) {
			condition += " and o.jctID = ?";
			paramsList.add(elecUser.getJctID());
		}
		// 入职开始时间
		if (elecUser.getOnDutyDateBegin() != null) {
			condition += " and o.onDutyDate >= ?";
			paramsList.add(elecUser.getOnDutyDateBegin());
		}
		// 入职结束时间
		if (elecUser.getOnDutyDateEnd() != null) {
			condition += " and o.onDutyDate <= ?";
			paramsList.add(elecUser.getOnDutyDateEnd());
		}
		Object[] params = paramsList.toArray();
		// 排序
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "asc");

		/** 2014-2-27 添加分页 begin */
		PageInfo pageInfo = new PageInfo(ServletActionContext.getRequest());
		List<ElecUser> list = elecUserDao.findCollectionByConditionWithPage(
				condition, params, orderby, pageInfo);
		ServletActionContext.getRequest().setAttribute("page",
				pageInfo.getPageBean());
		/** 2014-2-27 添加分页 end */
		// 3：将返回的结果List<ElecUser>，对出现数据字典的项，进行转换，使用数据类型和数据项的编号，获取数据项的值
		this.convertSystemDDL(list);
		return list;
	}

	/** 将返回的结果List<ElecUser>，对出现数据字典的项，进行转换，使用数据类型和数据项的编号，获取数据项的值 */
	private void convertSystemDDL(List<ElecUser> list) {
		if (list != null && list.size() > 0) {
			for (ElecUser elecUser : list) {
				// 性别
				elecUser.setSexID(StringUtils.isNotBlank(elecUser.getSexID()) ? elecSystemDDLDao
						.findSystemDDLByKeywordAndDDLCode("性别",
								elecUser.getSexID()) : "");
				// 所属单位
				elecUser.setJctID(StringUtils.isNotBlank(elecUser.getJctID()) ? elecSystemDDLDao
						.findSystemDDLByKeywordAndDDLCode("所属单位",
								elecUser.getJctID()) : "");
				// 职位
				elecUser.setPostID(StringUtils.isNotBlank(elecUser.getPostID()) ? elecSystemDDLDao
						.findSystemDDLByKeywordAndDDLCode("职位",
								elecUser.getPostID()) : "");
				// 是否在职
				elecUser.setIsDuty(StringUtils.isNotBlank(elecUser.getIsDuty()) ? elecSystemDDLDao
						.findSystemDDLByKeywordAndDDLCode("是否在职",
								elecUser.getIsDuty()) : "");
			}
		}
	}

	/**
	 * @Name: save
	 * @Description: 保存一条用户记录
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: ElecUser elecUser
	 * @Return: void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void save(ElecUser elecUser) {
		MD5keyBean m = new MD5keyBean();
		if (elecUser.getLogonPwd().isEmpty()) {
			elecUser.setLogonPwd(m.getkeyBeanofStr(ElecUser.INITPASSWORD));
		} else {
			elecUser.setLogonPwd(m.getkeyBeanofStr(elecUser.getLogonPwd()));
		}
		elecUserDao.save(elecUser);
	}

	/**
	 * @Name: checkUserByLogonName
	 * @Description: 查询数据库 检测登陆名称是否存在
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: String logonName
	 * @Return: String message
	 */
	public String checkUserByLogonName(String logonName) {
		String message = "";
		if (StringUtils.isNotEmpty(logonName)) {
			String condition = " and o.logonName=?";
			Object[] params = { logonName };

			List<ElecUser> list = elecUserDao.findCollectionByConditionNoPage(
					condition, params, null);
			if (list != null && list.size() > 0) {
				message = "2";
			} else {
				message = "3";
			}
		} else {
			message = "1";
		}
		return message;
	}

	/**
	 * @Name: findUserByUserId
	 * @Description: 根据主键来找到这个用户的个人信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: String userID
	 * @Return: ElecUser ElecUser
	 */
	public ElecUser findUserByUserId(String userID) {
		return elecUserDao.findObjectByID(userID);
	}

	/**
	 * @Name: update
	 * @Description: 更新用户资料 注意：这里的密码应该判断是否改变 如果发生了改变那么加密 如果没有改变 那么不要加密
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: ElecUser elecUser
	 * @Return: void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void update(ElecUser elecUser) {
		MD5keyBean m = new MD5keyBean();
		// 这里要判断 从页面获取到的密码 是否和数据库的密码 一样 【可以使用长度来判断
		// 因为数据库的密码使用加密了为32为，而用户输入的没有那么长】
		if (elecUser.getLogonPwd().length() != 32) {
			// 等于32说明密码用户没有重新设置密码 可以什么也不做
			elecUser.setLogonPwd(m.getkeyBeanofStr(elecUser.getLogonPwd()));
		}
		elecUserDao.update(elecUser);
	}

	/**
	 * @Name: delete
	 * @Description: 根据主键oid来删除一条记录
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: String userID
	 * @Return: void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void delete(String userID) {
		elecUserDao.deleteObjectByIDs(userID);
	}

	/**
	 * @Name: deleteAll
	 * @Description: 使用得逞封装的方法 来键一个主键的数组全部删除
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: String[] userIDarray
	 * @Return: void
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteAll(String[] userIDarray) {
		elecUserDao.deleteObjectByIDs((Serializable[]) userIDarray);
	}

	/**
	 * @Name: findUserCounts
	 * @Description: 找到用户的数量
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-28（创建日期）
	 * @Parameters:
	 * @Return: void
	 */
	public int findUserCounts() {
		return elecUserDao.count();
	}

	/**
	 * @Name: findExcelFiledName
	 * @Description: 组织excel的标题数据
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-27（创建日期）
	 * @Parameters: 无
	 * @Return: ArrayList<String> excel的标题
	 */
	public ArrayList<String> findExcelFiledName() {
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("5-1");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();// 获取到导出字段的名称
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		ArrayList<String> filedName = new ArrayList<String>(zList);
		return filedName;
	}

	/**
	 * @Name: findExcelFiledData
	 * @Description: 组织excel的结果数据
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-27（创建日期）
	 * @Parameters: ElecUser：VO对象
	 * @Return: ArrayList<ArrayList<String>>：excel的查询结果
	 */
	@SuppressWarnings("rawtypes")
	public ArrayList<ArrayList<String>> findExcelFiledData(ElecUser elecUser) {
		ArrayList<ArrayList<String>> fieldData = new ArrayList<ArrayList<String>>();
		// 查询导出设置表，获取导出设置中导出字段的中文名称
		ElecExportFields elecExportFields = elecExportFieldsDao
				.findObjectByID("5-1");
		// 获取导出设置的中导出的中文名称
		String zName = elecExportFields.getExpNameList();
		List<String> zList = StringToListUtils.stringToList(zName, "#");
		// 获取导出设置的中导出的英文名称
		String eName = elecExportFields.getExpFieldName();
		// 将导出的英文字段名称中的#号替换成逗号，即查询的条件
		String selectCondition = eName.replace("#", ",");

		// 查询条件
		String condition = "";
		List<Object> paramsList = new ArrayList<Object>();
		// 用户姓名
		String userName = elecUser.getUserName();
		try {
			userName = URLDecoder.decode(userName, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if (StringUtils.isNotBlank(userName)) {
			condition += " and o.userName like ?";
			paramsList.add("%" + userName + "%");
		}
		// 所属单位
		if (StringUtils.isNotBlank(elecUser.getJctID())) {
			condition += " and o.jctID = ?";
			paramsList.add(elecUser.getJctID());
		}
		// 入职开始时间
		if (elecUser.getOnDutyDateBegin() != null) {
			condition += " and o.onDutyDate >= ?";
			paramsList.add(elecUser.getOnDutyDateBegin());
		}
		// 入职结束时间
		if (elecUser.getOnDutyDateEnd() != null) {
			condition += " and o.onDutyDate <= ?";
			paramsList.add(elecUser.getOnDutyDateEnd());
		}
		Object[] params = paramsList.toArray();
		// 排序
		Map<String, String> orderby = new LinkedHashMap<String, String>();
		orderby.put("o.onDutyDate", "asc");
		// 获取结果，表示返回的所有结果
		List list = elecUserDao.findCollectionByConditionNoPageWithExcel(
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
						if (zList != null && zList.get(j).equals("性别")
								|| zList.get(j).equals("所属单位")
								|| zList.get(j).equals("是否在职")
								|| zList.get(j).equals("职位")) {
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

	/**
	 * @Name: importData
	 * @Description: 导入excel 并添加用户信息
	 * @Author: 刘洋（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-03-03（创建日期）
	 * @Parameters: ElecUser：VO对象
	 * @Return: ArrayList<ArrayList<String>>：excel的查询结果
	 */
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public List<String> importData(ElecUser elecUserForm,
			HttpServletRequest request) {
		File formFile = elecUserForm.getFile();
		// 把Excel文件内容转换为一个ArrayList
		ArrayList<String[]> arrayList = new ArrayList<String[]>();
		ArrayList<String> errorList = new ArrayList<String>();
		try {
			arrayList = GenerateSqlFromExcel.generateUserSql(formFile);
			// 检验 导入的excel是否符合添加用户的规则
			List<ElecUser> userlist = this.checkImportExcel(arrayList,
					errorList);
			if (errorList != null && errorList.size() > 0) {
				ServletActionContext.getRequest().setAttribute("userlist",
						userlist);
				return errorList;
			}
			// 保存
			elecUserDao.saves((ArrayList<ElecUser>) userlist);

		} catch (Exception e) {
			throw new RuntimeException("格式转换出现错误", e);

		}
		return null;

	}

	// 检测输入的数据是否合格 如果不符合 那么就不保存用户
	private List<ElecUser> checkImportExcel(ArrayList<String[]> arrayList,
			ArrayList<String> errorList) {
		List<ElecUser> userlist = new ArrayList<ElecUser>();
		if (arrayList != null && arrayList.size() > 0) {
			for (int i = 0; i < arrayList.size(); i++) {
				// 存放：登录名 密码 用户姓名 性别 所属单位 联系地址 是否在职 出生日期 职位
				String[] arrays = arrayList.get(i);// 获取每一行的数据
				ElecUser elecUser = new ElecUser();
				if (StringUtils.isNotBlank(arrays[0])) {
					String message = this.checkUserByLogonName(arrays[0]);
					// 数据库不存在记录，可以保存
					if (message != null && message.equals("3")) {
						elecUser.setLogonName(arrays[0]);
					} else {
						errorList.add("第" + (i + 2) + "行，第" + (0 + 1)
								+ "列，登录名出现重复");
					}
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (0 + 1) + "列，登录名为空");
				}
				// 密码
				if (StringUtils.isBlank(arrays[1])) {
					arrays[1] = "123";
				}
				if (StringUtils.isNotBlank(arrays[1])) {
					MD5keyBean md5keyBean = new MD5keyBean();
					elecUser.setLogonPwd(md5keyBean.getkeyBeanofStr(arrays[1]));
				}
				// 用户姓名
				if (StringUtils.isNotBlank(arrays[2])) {
					elecUser.setUserName(arrays[2]);
				}

				// 性别
				if (StringUtils.isNotBlank(arrays[3])) {
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("性别", arrays[3]);
					if (StringUtils.isNotBlank(ddlCode)) {
						elecUser.setSexID(ddlCode);
					} else {
						errorList.add("第" + (i + 2) + "行，第" + (3 + 1)
								+ "列，性别在数据字典转换中出现问题");
					}
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (3 + 1) + "列，性别为空");
				}

				// 所属单位
				if (StringUtils.isNotBlank(arrays[4])) {
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("所属单位", arrays[4]);
					if (StringUtils.isNotBlank(ddlCode)) {
						elecUser.setJctID(ddlCode);
					} else {
						errorList.add("第" + (i + 2) + "行，第" + (4 + 1)
								+ "列，所属单位在数据字典转换中出现问题");
					}
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (4 + 1) + "列，所属单位为空");
				}

				// 联系地址
				if (StringUtils.isNotBlank(arrays[5])) {
					elecUser.setAddress(arrays[5]);
				}

				// 是否在职
				if (StringUtils.isNotBlank(arrays[6])) {
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("是否在职", arrays[6]);
					if (StringUtils.isNotBlank(ddlCode)) {
						elecUser.setIsDuty(ddlCode);
					} else {
						errorList.add("第" + (i + 2) + "行，第" + (6 + 1)
								+ "列，是否在职在数据字典转换中出现问题");
					}
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (6 + 1) + "列，是否在职为空");
				}

				// 出生日期
				if (StringUtils.isNotBlank(arrays[7])) {
					SimpleDateFormat format = new SimpleDateFormat(
							"yyyy-MM-dd hh:mm:ss");
					try {
						elecUser.setBirthday(format.parse(arrays[7]));
					} catch (ParseException e) {
						throw new RuntimeException("日期格式不正确", e);
					}
				}

				// 职位
				if (StringUtils.isNotBlank(arrays[8])) {
					String ddlCode = elecSystemDDLDao
							.findSystemDDLByKeywordAndDDLName("职位", arrays[8]);
					if (StringUtils.isNotBlank(ddlCode)) {
						elecUser.setPostID(ddlCode);
					} else {
						errorList.add("第" + (i + 2) + "行，第" + (8 + 1)
								+ "列，职位在数据字典转换中出现问题");
					}
				} else {
					errorList.add("第" + (i + 2) + "行，第" + (8 + 1) + "列，职位为空");
				}
				userlist.add(elecUser);
			}
		}
		return userlist;
	}

	// 找到 每个部门的人员数量
	public List<ElecUser> findElecUserCount() {
		List<Object[]> list = elecUserDao.findElecUserCount();
		List<ElecUser> formList = this.userListPOToVo(list);
		return formList;
	}

	// 将object对象放入到elecUser对象中的jctName 和jctCount
	private List<ElecUser> userListPOToVo(List<Object[]> list) {
		List<ElecUser> formList = new ArrayList<ElecUser>();
		;
		for (int i = 0; list != null && i < list.size(); i++) {
			Object[] object = (Object[]) list.get(i);
			ElecUser elecUserForm = new ElecUser();
			elecUserForm.setJctName(object[0].toString());
			elecUserForm.setJctCount(object[1].toString());
			formList.add(elecUserForm);
		}
		return formList;
	}

}
