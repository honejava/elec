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

import cn.haut.elec.domain.ElecBuilding;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecBuildingService;
import cn.haut.elec.service.IElecSystemDDLService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;
import cn.haut.elec.utils.JfreeChartUtils;

@SuppressWarnings("serial")
@Controller("elecBuildingAction")
@Scope(value = "prototype")
public class ElecBuildingAction extends BaseAction<ElecBuilding> {
	// 注入systemDDLservice
	@Resource(name = IElecSystemDDLService.SERVICE_NAME)
	private IElecSystemDDLService ddlService;

	// 注入elecbuildingService
	@Resource(name = IElecBuildingService.SERVICE_NAME)
	private IElecBuildingService buildingService;

	/**
	 * @Name: home
	 * @Description: 跳转到建筑台检测中心的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-24（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/buildingIndex.jsp
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String home() {
		this.initBuildingSystemDDL();
		// 进入到监测台建筑管理的 页面 查询所有的监测台建筑物
		List<ElecBuilding> buildList = buildingService
				.findBuildByCondition(this.getModel());
		request.setAttribute("buildList", buildList);
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
	 * @Description: 进入到新增建建筑台信息的页面
	 * @Author: 甘亮（作者）
	 * 
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-24（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/buildingAdd.jsp
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String add() {
		this.initBuildingSystemDDL();
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 添加建筑物检测信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-25（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/buildingIndex.jsp
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String save() {
		buildingService.save(this.getModel());
		return "close";
	}

	/**
	 * @Name: view
	 * @Description: 查看建筑的详细内容
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-25（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/buildingView.jsp
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String view() {
		// 根据传递过来的buildingID来获取building数据
		ElecBuilding build = buildingService.findBuildByID(this.getModel()
				.getBuildingID());
		request.setAttribute("build", build);
		this.initBuildingSystemDDL();
		return "view";
	}

	/**
	 * @Name: edit
	 * @Description: 编辑建筑物
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-25（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/buildingEdit.jsp
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String edit() {
		ElecBuilding build = buildingService.findBuildByID(this.getModel()
				.getBuildingID());
		request.setAttribute("build", build);
		this.initBuildingSystemDDL();
		return "edit";
	}

	/**
	 * @Name: update
	 * @Description: 更新一个建筑物信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-25（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/buildingIndex.jsp
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String update() {
		buildingService.update(this.getModel());
		return "close";
	}

	/**
	 * @Name: delete
	 * @Description: 删除一个建筑物信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-25（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/buildingIndex.jsp
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String delete() {
		buildingService.delete(this.getModel());
		return home();
	}

	/**
	 * @Name: exportExcel
	 * @Description: 导出监测台建筑物信息到excel中 使用struts2的开发【使用poi报表导出】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-28（创建日期）
	 * @Parameters: 无
	 * @Return: null
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String exportExcel() throws Exception {
		ElecBuilding building = this.getModel();
		// 表示excel的标题
		ArrayList<String> fieldName = buildingService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = buildingService
				.findExcelFiledData(building);
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "监测台建筑管理报表（"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "）";
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		request.setAttribute("filename", filename);

		excelFileGenerator.expordExcel(os);
		// struts将excel文件写到输入流中，将输入流放置到栈顶的inputStream的属性中
		byte[] buf = os.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		building.setInputStream(in);
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
	@AnnotationLimit(mid = "al", pid = "ak")
	public String importPage() {
		return "importPage";
	}

	/**
	 * @Name: importData
	 * @Description: 导入检测台建筑物【使用的jxl报表导入】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-26（创建日期）
	 * @Parameters: 无
	 * @Return:关闭当前页面 刷新父页面 close
	 */
	@AnnotationLimit(mid = "al", pid = "ak")
	public String importData() throws IOException {
		List<String> errorlist = buildingService.importData(this.getModel(),
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
	@AnnotationLimit(mid = "al", pid = "ak")
	public String report() {
		List<ElecBuilding> buildList = buildingService.findElecBuildingCount();
		request.setAttribute("buildList", buildList);

		int sumCount = 0;
		if (buildList != null && buildList.size() > 0) {
			for (int i = 0; i < buildList.size(); i++) {
				ElecBuilding building = (ElecBuilding) buildList.get(i);
				sumCount += Integer.parseInt(building.getJctCount());
			}
		}
		request.setAttribute("sumCount", sumCount);
		// 获取sevletContext对象
		ServletContext sc = ServletActionContext.getServletContext();
		// 生成图片
		try {
			JfreeChartUtils.generalBuildingBarJpeg(buildList, request, sc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "report";
	}

	/**
	 * action里面抽取出来的公用属性
	 */
	public void initBuildingSystemDDL() {
		// 获取到【所属单位】的数据字典的内容
		List<ElecSystemDDL> jctList = ddlService
				.findSystemDDLByKeywordlist("所属单位");
		request.setAttribute("jctList", jctList);
		// 获取到【建筑类型】的数据字典的内容
		List<ElecSystemDDL> typeList = ddlService
				.findSystemDDLByKeywordlist("建筑类型");
		request.setAttribute("typeList", typeList);
	}
}
