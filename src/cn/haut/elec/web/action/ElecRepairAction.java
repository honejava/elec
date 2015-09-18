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
import cn.haut.elec.domain.ElecRepair;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecRepairService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;

@Controller("elecRepairAction")
@Scope(value = "prototype")
public class ElecRepairAction extends BaseAction<ElecRepair> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecRepairService.SERVICE_NAME)
	private IElecRepairService repairService;

	/**
	 * @Name: home
	 * @Description: 跳转到检修管理
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairIndex.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String home() {
		this.initSystemDDL();
		List<ElecRepair> repairList = repairService
				.findRepairListByCondition(this.getModel());
		request.setAttribute("repairList", repairList);
		String initPage = request.getParameter("initPage");
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		return "home";
	}

	/**
	 * @Name: repairMoreAdd
	 * @Description: 进入到批量添加检修的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairMoreAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String repairMoreAdd() {
		this.initSystemDDL();
		return "repairMoreAdd";
	}

	/**
	 * @Name: repairMoreAddList
	 * @Description: 进入到批量添加检修的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairMoreAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String repairMoreAddList() {
		// 查询到所有的设备
		List<ElecEquapment> equapmentList = repairService
				.findRepairByCondition(this.getModel());
		request.setAttribute("equapmentList", equapmentList);
		return "repairMoreAddList";
	}

	/**
	 * @Name: moreUpdate
	 * @Description: 批量添加检修
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairIndex.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String moreUpdate() {
		repairService.moreUpdate(this.getModel());
		return "close";
	}

	/**
	 * @Name: repairQuery
	 * @Description: 检修查询
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairQuery.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String repairQuery() {
		// 查询所有的已经校准了设备
		List<ElecRepair> repairList = repairService.findRepairtHistory();
		request.setAttribute("repairList", repairList);
		return "repairQuery";
	}

	/**
	 * @Name: add
	 * @Description: 进入到添加检修页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String add() {
		this.initSystemDDL();
		ElecRepair repair = repairService.findRepairById(this.getModel()
				.getRepairID());
		request.setAttribute("repair", repair);
		return "add";
	}

	/**
	 * @Name: add
	 * @Description: 添加设备检修
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String save() {
		repairService.save(this.getModel());
		return "close";
	}

	/**
	 * @Name: edit
	 * @Description: 进入到编辑检修页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String edit() {
		this.initSystemDDL();
		ElecRepair repair = repairService.findRepairById(this.getModel()
				.getRepairID());
		request.setAttribute("repair", repair);
		return "edit";
	}

	/**
	 * @Name: delete
	 * @Description: 删除检修记录
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String delete() {
		repairService.delete(this.getModel());
		return home();
	}

	/**
	 * @Name: update
	 * @Description: 更新设备检修
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairIndex.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String update() {
		repairService.update(this.getModel());
		return "close";
	}

	/**
	 * @Name: view
	 * @Description: 查看设备检修页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/repairView.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String view() {
		this.initSystemDDL();
		ElecRepair repair = repairService.findRepairById(this.getModel()
				.getRepairID());
		request.setAttribute("repair", repair);
		return "view";
	}

	/**
	 * @Name: exportExcel
	 * @Description: 查看设备检修页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String exportExcel() throws Exception {
		// 表示excel的标题
		ArrayList<String> fieldName = repairService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = repairService
				.findExcelFiledData(this.getModel());
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "仪器设备检修报表（"
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
		List<ElecSystemDDL> jctList = repairService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		// 检修状态
		List<ElecSystemDDL> isHavingList = repairService
				.findSystemDDLListByKeyword("校准状态");
		request.setAttribute("isHavingList", isHavingList);
		// 设备类型
		List<ElecSystemDDL> devTypeList = repairService
				.findSystemDDLListByKeyword("设备类型");
		request.setAttribute("devTypeList", devTypeList);
	}

}
