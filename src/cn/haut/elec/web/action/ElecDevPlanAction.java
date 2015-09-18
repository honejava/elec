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

import cn.haut.elec.domain.ElecDevPlan;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecDevPlanService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;
import cn.haut.elec.utils.JfreeChartUtils;

@Controller("elecDevPlanAction")
@Scope(value = "prototype")
public class ElecDevPlanAction extends BaseAction<ElecDevPlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecDevPlanService.SERVICE_NAME)
	private IElecDevPlanService devPlanService;

	/**
	 * @Name: home
	 * @Description: 跳转到运行情况
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-03（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String home() {
		this.initSystemDDL();
		// 查询到带有条件的设备维修计划
		List<ElecDevPlan> devPlanList = devPlanService
				.findDevPlanListByCondition(this.getModel());
		request.setAttribute("devPlanList", devPlanList);

		String initPage = request.getParameter("initPage");
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		return "home";
	}

	/**
	 * @Name: add
	 * @Description:跳转到添加设备购置计划页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-03（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planAdd.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String add() {
		this.initSystemDDL();
		return "add";
	}

	/**
	 * @Name: save
	 * @Description:保存设备购置计划
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-03（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String save() {
		devPlanService.save(this.getModel());
		return "close";
	}

	/**
	 * @Name: view
	 * @Description:浏览设备购置计划
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-03（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planView.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String view() {
		this.initSystemDDL();
		// 根据devPlanID来获取到devPlan
		ElecDevPlan devPlan = devPlanService.findDevPlanById(this.getModel()
				.getDevPlanID());
		request.setAttribute("devPlan", devPlan);
		return "view";
	}

	/**
	 * @Name: edit
	 * @Description:编辑设备购置计划
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-03（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planEdit.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String edit() {
		this.initSystemDDL();
		// 根据devPlanID来获取到devPlan
		ElecDevPlan devPlan = devPlanService.findDevPlanById(this.getModel()
				.getDevPlanID());
		request.setAttribute("devPlan", devPlan);
		return "edit";
	}

	/**
	 * @Name: update
	 * @Description:更新设备购置计划
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String update() {
		devPlanService.update(this.getModel());
		return "close";
	}

	/**
	 * @Name: delete
	 * @Description:删除设备购置计划
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String delete() {
		devPlanService.deleteById(this.getModel().getDevPlanID());
		return home();
	}

	/**
	 * @Name: exportExcel
	 * @Description:设备购置计划报表导出
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String exportExcel() throws Exception {
		// 表示excel的标题
		ArrayList<String> fieldName = devPlanService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = devPlanService
				.findExcelFiledData(this.getModel());
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "购置计划报表（"
				+ new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())
				+ "）";
		filename = new String(filename.getBytes("gbk"), "iso-8859-1");
		request.setAttribute("filename", filename);

		excelFileGenerator.expordExcel(os);
		// struts将excel文件写到输入流中，将输入流放置到栈顶的inputStream的属性中
		byte[] buf = os.toByteArray();
		ByteArrayInputStream in = new ByteArrayInputStream(buf);
		this.getModel().setInputStream(in);
		return "success";
	}

	/**
	 * @Name: importPage
	 * @Description:进入到导入设备购置计划的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planImport.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String importPage() {
		return "importPage";
	}

	/**
	 * @Name: importPage
	 * @Description:进入到导入设备购置计划的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planImport.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String importData() throws IOException {
		List<String> errorlist = devPlanService.importData(this.getModel(),
				request);
		if (errorlist != null && errorlist.size() > 0) {
			request.setAttribute("errorList", errorlist);
			return "importPage";
		}
		return "close";
	}

	/**
	 * @Name: devicePlanToDevice
	 * @Description:将购置计划实现 变成自己的设备
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String devicePlanToDevice() {
		devPlanService.devicePlanToDevice(this.getModel());
		return home();
	}

	/**
	 * @Name: devicePlanNextDate
	 * @Description:购置计划顺延
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String devicePlanNextDate() {
		// devPlanService.devicePlanNextDate(this.getModel());
		return home();
	}

	/**
	 * @Name: report
	 * @Description:购置计划顺延
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/planIndex.jsp
	 */
	@AnnotationLimit(mid = "ad", pid = "aa")
	public String report() {
		List<ElecDevPlan> devPlanList = devPlanService.findElecDevPlanCount();
		request.setAttribute("devPlanList", devPlanList);

		int sumCount = 0;
		if (devPlanList != null && devPlanList.size() > 0) {
			for (int i = 0; i < devPlanList.size(); i++) {
				ElecDevPlan plan = (ElecDevPlan) devPlanList.get(i);
				sumCount += Integer.parseInt(plan.getJctCount());
			}
		}
		request.setAttribute("sumCount", sumCount);
		// 获取sevletContext对象
		ServletContext sc = ServletActionContext.getServletContext();
		// 生成图片
		try {
			JfreeChartUtils.generalDevPlanBarJpeg(devPlanList, request, sc);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "report";
	}

	private void initSystemDDL() {
		// 所属单位
		List<ElecSystemDDL> jctList = devPlanService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		// 设备类型
		List<ElecSystemDDL> devTypeList = devPlanService
				.findSystemDDLListByKeyword("设备类型");
		request.setAttribute("devTypeList", devTypeList);
	}

}
