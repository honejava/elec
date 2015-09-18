package cn.haut.elec.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecStation;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecBugService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;

@Controller("elecBugAction")
@Scope(value = "prototype")
public class ElecBugAction extends BaseAction<ElecBug> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecBugService.SERVICE_NAME)
	private IElecBugService bugService;

	/**
	 * @Name: home
	 * @Description: 跳转到运行情况
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunIndex.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String home() {
		this.initSystemDDL();
		// 获取到站点的名称【不重复的】 List<String> stationNameList =

		List<String> stationList = bugService.findDistinctStationNameList();
		request.setAttribute("stationList", stationList);

		// 查询所有的站点运行情况
		List<ElecBug> bugList = bugService.findBugByCondition(this.getModel());
		request.setAttribute("bugList", bugList);
		String initPage = request.getParameter("initPage");
		/**
		 * 如果initPage==null：跳转到userIndex.jsp,如果initPage==1：表示跳转到userList.jsp
		 */
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		return "home";
	}

	/**
	 * @Name: edit
	 * @Description: 编辑站点bug
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunIndex.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String edit() {
		this.initSystemDDL();
		// 获取到站点的名称【不重复的】
		List<ElecStation> stationList = bugService.findAllStation();
		request.setAttribute("stationList", stationList);
		// 根据bugID来找到这个bug的全部信息
		ElecBug bug = bugService.findBugById(this.getModel().getBugID());
		request.setAttribute("bug", bug);
		return "edit";
	}

	/**
	 * @Name: update
	 * @Description: 更新站点bug
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunIndex.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String update() {
		bugService.update(this.getModel());
		return "close";
	}

	/**
	 * @Name: showRunTime
	 * @Description: 显示站点一共消耗了多少时间
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-28（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteRunTime.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String showRunTime() {
		String sumHour = bugService.findSumBugHour();
		request.setAttribute("sumHour", sumHour);
		return "showRunTime";
	}

	/**
	 * @Name: exportExcel
	 * @Description: 导出站点bug信息到excel中 使用struts2的开发【使用poi报表导出】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-28（创建日期）
	 * @Parameters: 无
	 * @Return: null
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String exportExcel() throws Exception {
		ElecBug bug = this.getModel();
		// 表示excel的标题
		ArrayList<String> fieldName = bugService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = bugService
				.findExcelFiledData(bug);
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "站点设备运行情况报表（"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "）";
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		request.setAttribute("filename", filename);

		excelFileGenerator.expordExcel(os);
		// struts将excel文件写到输入流中，将输入流放置到栈顶的inputStream的属性中
		byte[] buf = os.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		bug.setInputStream(in);
		return "success";
	}

	/**
	 * @Name: importPage
	 * @Description: 进入到导入页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: null
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String importPage() {
		return "importPage";
	}

	/**
	 * @Name: importData
	 * @Description: 导入检测台建筑物【使用的jxl报表导入】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-28（创建日期）
	 * @Parameters: 无
	 * @Return:关闭当前页面 刷新父页面 close
	 */

	@AnnotationLimit(mid = "ai", pid = "ag")
	public String importData() throws IOException {
		List<String> errorlist = bugService
				.importData(this.getModel(), request);
		if (errorlist != null && errorlist.size() > 0) {
			request.setAttribute("errorList", errorlist);
			return "importPage";
		}
		return "close";
	}

	/**
	 * @Name: report
	 * @Description: jfreeChart统计建筑物数量
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return:/WEB-INF/page/system/buildingReport.jsp
	 */

	// 初始化数据字典的内容
	public void initSystemDDL() {
		// 所属单位
		List<ElecSystemDDL> jctList = bugService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		// 故障类型
		List<ElecSystemDDL> bugTypeList = bugService
				.findSystemDDLListByKeyword("故障类型");
		request.setAttribute("bugTypeList", bugTypeList);
		// 站点类别
		List<ElecSystemDDL> stationTypeList = bugService
				.findSystemDDLListByKeyword("站点类别");
		request.setAttribute("stationTypeList", stationTypeList);
	}
}
