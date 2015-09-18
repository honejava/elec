package cn.haut.elec.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecStation;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecStationService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;
import cn.haut.elec.utils.JfreeChartUtils;

@Controller("elecStationAction")
@Scope(value = "prototype")
public class ElecStationAction extends BaseAction<ElecStation> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入service
	@Resource(name = IElecStationService.SERVICE_NAME)
	private IElecStationService stationService;

	/**
	 * @Name: home
	 * @Description: 跳转到站点基本信息的管理页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteInfoIndex.jsp
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String home() {
		this.initStationSystemDDL();
		// 查询所有的站点信息
		List<ElecStation> stationList = stationService
				.findStationByCondition(this.getModel());
		request.setAttribute("stationList", stationList);
		// 获取到站点名称的集合
		List<String> stationNameList = new ArrayList<String>();
		for (ElecStation station : stationList) {
			stationNameList.add(station.getStationName());
		}
		request.setAttribute("stationNameList", stationNameList);
		// 添加分页
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
	 * @Name: add
	 * @Description: 进入到添加站点信息的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteInfoAdd.jsp
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String add() {
		this.initStationSystemDDL();
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 保存站点信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteInfoIndex.jsp
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String save() {
		stationService.save(this.getModel());
		return "close";
	}

	/**
	 * @Name: view
	 * @Description: 查看站点信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteInfoView.jsp
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String view() {
		this.initStationSystemDDL();
		ElecStation station = stationService.findStationByID(this.getModel()
				.getStationID());
		request.setAttribute("station", station);
		return "view";
	}

	/**
	 * @Name: edit
	 * @Description: 进入到编辑站点信息的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteInfoEdit.jsp
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String edit() {
		this.initStationSystemDDL();
		ElecStation station = stationService.findStationByID(this.getModel()
				.getStationID());
		request.setAttribute("station", station);
		return "edit";
	}

	/**
	 * @Name: update
	 * @Description: 根据编辑后的站点信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteInfoIndex.jsp
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String update() {
		stationService.update(this.getModel());
		return "close";
	}

	/**
	 * @Name: delete
	 * @Description: 删除一个站点
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteInfoIndex.jsp
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String delete() {
		stationService.delete(this.getModel());
		return home();
	}

	/**
	 * @Name: exportExcel
	 * @Description: 导出站点信息到excel中 使用struts2的开发【使用poi报表导出】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-28（创建日期）
	 * @Parameters: 无
	 * @Return: null
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String exportExcel() throws Exception {
		ElecStation station = this.getModel();
		// 表示excel的标题
		ArrayList<String> fieldName = stationService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = stationService
				.findExcelFiledData(station);
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "站点基本信息报表（"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "）";
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		request.setAttribute("filename", filename);

		excelFileGenerator.expordExcel(os);
		// struts将excel文件写到输入流中，将输入流放置到栈顶的inputStream的属性中
		byte[] buf = os.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		station.setInputStream(in);
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
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String importPage() {
		return "importPage";
	}

	/**
	 * @Name: importData
	 * @Description: 导入站点基本信息【使用的jxl报表导入】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return:关闭当前页面 刷新父页面 close
	 */
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String importData() throws IOException {
		List<String> errorlist = stationService.importData(this.getModel(),
				request);
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
	@AnnotationLimit(mid = "ah", pid = "ag")
	public String report() {
		List<ElecStation> stationList = stationService.findElecStationCount();
		request.setAttribute("stationList", stationList);

		int sumCount = 0;
		if (stationList != null && stationList.size() > 0) {
			for (int i = 0; i < stationList.size(); i++) {
				ElecStation station = (ElecStation) stationList.get(i);
				sumCount += Integer.parseInt(station.getJctCount());
			}
		}
		request.setAttribute("sumCount", sumCount);
		// 获取sevletContext对象
		ServletContext sc = ServletActionContext.getServletContext();
		// 生成图片
		try {
			JfreeChartUtils.generalStationBarJpeg(stationList, request, sc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "report";
	}

	// 初始化 数据字典的 【所属单位】 【站点类别】
	public void initStationSystemDDL() {
		// 获取到所属单位的集合
		List<ElecSystemDDL> jctList = stationService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		// 获取到站点类别的集合
		List<ElecSystemDDL> stationTypeList = stationService
				.findSystemDDLListByKeyword("站点类别");
		request.setAttribute("stationTypeList", stationTypeList);
	}
}
