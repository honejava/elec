package cn.haut.elec.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecSystemDDLService;
import cn.haut.elec.service.IElecUserService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;
import cn.haut.elec.utils.JfreeChartUtils;
import cn.haut.elec.utils.StringToArrayUtil;
import cn.haut.elec.utils.ValueStackUtils;

@SuppressWarnings("serial")
@Controller("elecUserAction")
@Scope(value = "prototype")
public class ElecUserAction extends BaseAction<ElecUser> {

	private ElecUser elecUser = this.getModel();

	@Resource(name = IElecUserService.SERVICE_NAME)
	private IElecUserService elecUserService;

	@Resource(name = IElecSystemDDLService.SERVICE_NAME)
	private IElecSystemDDLService elecSystemDDLService;

	/**
	 * @Name: home
	 * @Description: 查询用户中有数据字典的内容 有【所属单位】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-12（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/system/userIndex.jsp
	 */
	/*
	 * public String home() { // 查询数据字典的所属单位的ElecSystemDDL对象 获取的是居住的list集合
	 * List<ElecSystemDDL> jctlist = elecSystemDDLService
	 * .findSystemDDLByKeywordlist("所属单位"); request.setAttribute("jctlist",
	 * jctlist); // 根据页面给出的选项查出所有的用户信息 List<ElecUser> userlist = elecUserService
	 * .findUserListByCondition(elecUser); request.setAttribute("userlist",
	 * userlist); // 转换数据字典的内容 this.convert(userlist); return "home"; }
	 */
	@AnnotationLimit(mid = "an", pid = "am")
	public String home() {
		// 查询数据字典的所属单位的ElecSystemDDL对象 获取的是居住的list集合
		List<ElecSystemDDL> jctlist = elecSystemDDLService
				.findSystemDDLByKeywordlist("所属单位");
		request.setAttribute("jctlist", jctlist);
		// 根据页面给出的选项查出所有的用户信息
		List<ElecUser> userlist = elecUserService
				.findUserListByCondition(elecUser);
		request.setAttribute("userlist", userlist);
		String initPage = request.getParameter("initPage");
		/**
		 * 如果initPage==null：跳转到userIndex.jsp,如果initPage==1：表示跳转到userList.jsp
		 */
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		/** 2014-2-27 添加分页 end */
		return "home";
	}

	/**
	 * 职位不能为空 如果为空 会出现错误 【】
	 * 
	 * @param userlist
	 */
	@SuppressWarnings("unused")
	private void convert(List<ElecUser> userlist) {
		for (ElecUser elecUser : userlist) {
			elecUser.setSexID(elecSystemDDLService
					.findSystemDDLByKeywordAndDDLCode("性别", elecUser.getSexID()));
			elecUser.setIsDuty(elecSystemDDLService
					.findSystemDDLByKeywordAndDDLCode("是否在职",
							elecUser.getIsDuty()));
			elecUser.setPostID(elecSystemDDLService
					.findSystemDDLByKeywordAndDDLCode("职位",
							elecUser.getPostID()));
			elecUser.setJctID(elecSystemDDLService
					.findSystemDDLByKeywordAndDDLCode("所属单位",
							elecUser.getJctID()));
		}
	}

	/**
	 * @Name: add
	 * @Description: 跳转到添加页面 查询添加页面的数据字典的内容
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-12（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/system/userAdd.jsp
	 */
	@AnnotationLimit(mid = "fb", pid = "fa")
	public String add() {
		this.initSystemDDL();
		return "add";
	}

	private void initSystemDDL() {
		// 获取性别 职位 所属单位 是否在职的列表
		List<ElecSystemDDL> sexlist = elecSystemDDLService
				.findSystemDDLByKeywordlist("性别");
		List<ElecSystemDDL> postlist = elecSystemDDLService
				.findSystemDDLByKeywordlist("职位");
		List<ElecSystemDDL> jctlist = elecSystemDDLService
				.findSystemDDLByKeywordlist("所属单位");
		List<ElecSystemDDL> isDutylist = elecSystemDDLService
				.findSystemDDLByKeywordlist("是否在职");
		request.setAttribute("sexlist", sexlist);
		request.setAttribute("postlist", postlist);
		request.setAttribute("jctlist", jctlist);
		request.setAttribute("isDutylist", isDutylist);
	}

	/**
	 * @Name: findJctUnit
	 * @Description: 使用ajax的二级联动来实现查询
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-12（创建日期）
	 * @Parameters: 无
	 * @Return: String：返回到原来的页面
	 */
	@AnnotationLimit(mid = "fl", pid = "fa")
	public String findJctUnit() {
		String ddlName = elecUser.getJctID();
		List<ElecSystemDDL> jctunitlist = elecSystemDDLService
				.findSystemDDLByKeywordlist(ddlName);
		ValueStackUtils.setValueStatck(jctunitlist);
		return "findJctUnit";
	}

	/**
	 * @Name: save
	 * @Description: 保存添加的用户信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-12（创建日期）
	 * @since 根据从页面获取到的参数来判断是否是新增加的还是更新的
	 * @since 对密码进行MD5加密处理 如果为空 那么设置一个默认值 为【123】
	 * @Parameters: 无
	 * @Return: String：close.jsp
	 */
	@AnnotationLimit(mid = "fc", pid = "fa")
	public String save() {
		String userID = elecUser.getUserID();
		if (userID == null) {
			elecUserService.save(elecUser);
		} else {
			elecUserService.update(elecUser);
		}
		return "close";
	}

	/**
	 * @Name: checkUser
	 * @Description: 检测登陆用户的信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-12（创建日期）
	 * @Parameters: 无
	 * @Return: String：返回到当前jsp页面
	 */
	@AnnotationLimit(mid = "ff", pid = "fa")
	public String checkUser() {
		String logonName = elecUser.getLogonName();
		String message = elecUserService.checkUserByLogonName(logonName);
		elecUser.setMessage(message);
		return "checkUser";
	}

	/**
	 * @Name: edit
	 * @Description: 检测登陆用户的信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-12（创建日期）
	 * @Parameters: 无
	 * @Return: String：/WEB-INF/pages/system/userEdit.jsp
	 */
	@AnnotationLimit(mid = "fd", pid = "fa")
	public String edit() {
		this.initSystemDDL();
		// 获取到编辑的那个客户的全部的信息 然后根据userId来查询到这个用户逇信息 并保存到request中
		String userID = elecUser.getUserID();
		String viewflag = elecUser.getViewflag();
		elecUser = elecUserService.findUserByUserId(userID);
		elecUser.setViewflag(viewflag);
		ValueStackUtils.setValueStatck(elecUser);
		// 获取到页面的所属单位 来获取到当前所属单位的单位名称
		String jctID = elecUser.getJctID();
		String ddlName = elecSystemDDLService.findSystemDDLByKeywordAndDDLCode(
				"所属单位", jctID);
		List<ElecSystemDDL> jctUnitlist = elecSystemDDLService
				.findSystemDDLByKeywordlist(ddlName);
		request.setAttribute("jctUnitlist", jctUnitlist);
		return "edit";
	}

	/**
	 * @Name: delete
	 * @Description: 批量删除 [这里的userID获取到的是一个字符串 包含了 所有选中的userID]
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-13（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到/WEB-INF/pages/system/userIndex.jsp
	 */
	@AnnotationLimit(mid = "fe", pid = "fa")
	public String delete() {
		String userID = elecUser.getUserID();
		String[] userIDarray = StringToArrayUtil.StringToArray(userID, ", ");
		// 在这里便利删除userIDarray里面所有的用户信息
		/** 第一种方式是删除每个值 */
		for (int i = 0; i < userIDarray.length; i++) {
			elecUserService.delete(userIDarray[i]);
		}
		/** 第2种方式是一次性全部删除每个值 */
		// elecUserService.deleteAll(userIDarray);
		return "delete";
	}

	/**
	 * @Name: logon
	 * @Description: 登陆系统管理
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-15（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到/WEB-INF/pages/system/userIndex.jsp
	 */
	public String logon() {
		return "logon";
	}

	/**
	 * @Name: chartUser
	 * @Description: 统计本店里系统存在的用户数量
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到/WEB-INF/pages/system/userCount.jsp
	 */
	@AnnotationLimit(mid = "fj", pid = "fa")
	public String chartUser() {
		int count = elecUserService.findUserCounts();
		request.setAttribute("userCount", count);
		return "chartUser";
	}

	/**
	 * @Name: exportExcel
	 * @Description: 导出用户到excel中 不使用struts2的开发
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-28（创建日期）
	 * @Parameters: 无
	 * @Return: null
	 */
	@AnnotationLimit(mid = "fg", pid = "fa")
	public String exportExcel2() throws Exception {
		// 表示excel的标题
		ArrayList<String> fieldName = elecUserService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = elecUserService
				.findExcelFiledData(elecUser);
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		OutputStream os = response.getOutputStream();

		// 文件下载（添加的配置）
		response.setContentType("application/vnd.ms-excel");
		String filename = "用户报表（"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "）";
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		response.setHeader("Content-disposition", "attachment;filename="
				+ filename + ".xls");
		response.setBufferSize(1024);

		excelFileGenerator.expordExcel(os);
		return null;
	}

	/**
	 * @Name: exportExcel
	 * @Description: 导出用户到excel中 使用struts2的开发
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-28（创建日期）
	 * @Parameters: 无
	 * @Return: null
	 */
	@AnnotationLimit(mid = "fg", pid = "fa")
	public String exportExcel() throws Exception {
		// 表示excel的标题
		ArrayList<String> fieldName = elecUserService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = elecUserService
				.findExcelFiledData(elecUser);
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "用户报表（"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "）";
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		request.setAttribute("filename", filename);

		excelFileGenerator.expordExcel(os);
		// struts将excel文件写到输入流中，将输入流放置到栈顶的inputStream的属性中
		byte[] buf = os.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		elecUser.setInputStream(in);
		return "success";
	}

	/**
	 * @Name: importPage
	 * @Description: 进入到 导入用户的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-03-03（创建日期）
	 * @Parameters: 无
	 * @Return: /WEB-INF/pages/system/userImport.jsp
	 */
	@AnnotationLimit(mid = "fh", pid = "fa")
	public String importPage() {

		return "importPage";
	}

	/**
	 * @Name: importData
	 * @Description: 导入用户
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-03-03（创建日期）
	 * @Parameters: 无
	 * @Return:关闭当前页面 刷新父页面 close
	 */
	@AnnotationLimit(mid = "fi", pid = "fa")
	public String importData() throws IOException {
		List<String> errorlist = elecUserService.importData(elecUser, request);
		if (errorlist != null && errorlist.size() > 0) {
			request.setAttribute("errorList", errorlist);
			return "importPage";
		}
		return "close";
	}

	/**
	 * @Name: report
	 * @Description: jfreeChart统计人员数量
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-03-03（创建日期）
	 * @Parameters: 无
	 * @Return:/WEB-INF/page/system/userReport.jsp
	 */
	@AnnotationLimit(mid = "fk", pid = "fa")
	public String report() {
		List<ElecUser> userList = elecUserService.findElecUserCount();
		request.setAttribute("userList", userList);

		int sumCount = 0;
		if (userList != null && userList.size() > 0) {
			for (int i = 0; i < userList.size(); i++) {
				ElecUser ElecUserForm = (ElecUser) userList.get(i);
				sumCount += Integer.parseInt(ElecUserForm.getJctCount());
			}
		}
		request.setAttribute("sumCount", sumCount);
		// 获取sevletContext对象
		ServletContext sc = ServletActionContext.getServletContext();
		// 生成图片
		try {
			JfreeChartUtils.generalBarJpeg(userList, request, sc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "report";
	}

}
