package cn.haut.elec.web.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecEquapmentService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;

@Controller("elecEquapmentAction")
@Scope(value = "prototype")
public class ElecEquapmentAction extends BaseAction<ElecEquapment> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecEquapmentService.SERVICE_NAME)
	private IElecEquapmentService equapmentService;

	/**
	 * @Name: home
	 * @Description: 跳转到运行情况
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/equapmentIndex.jsp
	 */
	@AnnotationLimit(mid = "ab", pid = "aa")
	public String home() {
		this.initSystemDDL();
		List<ElecEquapment> equapmentList = equapmentService
				.findEquapmentListByCondition(this.getModel());
		request.setAttribute("equapmentList", equapmentList);

		String initPage = request.getParameter("initPage");
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		return "home";
	}

	/**
	 * @Name: view
	 * @Description: 查看设备仪器管理
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/equapmentView.jsp
	 */
	@AnnotationLimit(mid = "ab", pid = "aa")
	public String view() {
		this.initSystemDDL();
		ElecEquapment equapment = equapmentService.findEquapmentById(this
				.getModel().getEquapmentID());
		request.setAttribute("equapment", equapment);
		return "view";
	}

	/**
	 * @Name: edit
	 * @Description: 编辑设备仪器管理
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/equapmentView.jsp
	 */
	@AnnotationLimit(mid = "ab", pid = "aa")
	public String edit() {
		this.initSystemDDL();
		ElecEquapment equapment = equapmentService.findEquapmentById(this
				.getModel().getEquapmentID());
		request.setAttribute("equapment", equapment);
		return "edit";
	}

	/**
	 * @Name: update
	 * @Description: 更新设备仪器管理
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/equapmentIndex.jsp
	 */
	@AnnotationLimit(mid = "ab", pid = "aa")
	public String update() {
		equapmentService.update(this.getModel());
		return "close";
	}

	/**
	 * @Name: delete
	 * @Description: 删除设备仪器管理
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/equapmentIndex.jsp
	 */
	@AnnotationLimit(mid = "ab", pid = "aa")
	public String delete() {
		equapmentService.delete(this.getModel());
		return "close";
	}

	/**
	 * @Name: exportExcel
	 * @Description:仪器设备导出
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/equapmentIndex.jsp
	 */
	@AnnotationLimit(mid = "ab", pid = "aa")
	public String exportExcel() throws Exception {
		// 表示excel的标题
		ArrayList<String> fieldName = equapmentService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = equapmentService
				.findExcelFiledData(this.getModel());
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "仪器设备报表（"
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

	private void initSystemDDL() {
		// 所属单位
		List<ElecSystemDDL> jctList = equapmentService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		// 设备类型
		List<ElecSystemDDL> devTypeList = equapmentService
				.findSystemDDLListByKeyword("设备类型");
		request.setAttribute("devTypeList", devTypeList);
		// 设备状态
		List<ElecSystemDDL> devStateList = equapmentService
				.findSystemDDLListByKeyword("设备状态");
		request.setAttribute("devStateList", devStateList);
	}

}
