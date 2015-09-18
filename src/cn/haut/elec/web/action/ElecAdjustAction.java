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

import cn.haut.elec.domain.ElecAdjust;
import cn.haut.elec.domain.ElecEquapment;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecAdjustService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ExcelFileGenerator;

@Controller("elecAdjustAction")
@Scope(value = "prototype")
public class ElecAdjustAction extends BaseAction<ElecAdjust> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecAdjustService.SERVICE_NAME)
	private IElecAdjustService adjustService;

	/**
	 * @Name: home
	 * @Description: 跳转到运行情况
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustIndex.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String home() {
		this.initSystemDDL();
		// 查询到所有的设备
		List<ElecAdjust> adjustList = adjustService.findAdjustByCondition(this
				.getModel());
		request.setAttribute("adjustList", adjustList);
		String initPage = request.getParameter("initPage");
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		return "home";
	}

	/**
	 * @Name: adjustMoreAdd
	 * @Description: 跳转到批量添加的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustMoreAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String adjustMoreAdd() {
		this.initSystemDDL();
		// 查询到所有的设备
		return "adjustMoreAdd";
	}

	/**
	 * @Name: adjustMoreAddList
	 * @Description: 跳转到批量添加的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustMoreAddList.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String adjustMoreAddList() {
		this.initSystemDDL();
		// 查询到所有的设备
		List<ElecEquapment> equapmentList = adjustService
				.findEquapmentByCondition(this.getModel());
		request.setAttribute("equapmentList", equapmentList);
		return "adjustMoreAddList";
	}

	/**
	 * @Name: moreUpdate
	 * @Description: 添加校准管理
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustIndex.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String moreUpdate() {
		adjustService.moreUpdate(this.getModel());
		return "close";
	}

	/**
	 * @Name: adjustQuery
	 * @Description: 进入到查看校准记录的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustQuery.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String adjustQuery() {
		// 查询所有的已经校准了设备
		List<ElecAdjust> adjustList = adjustService.findAdjustHistory();
		request.setAttribute("adjustList", adjustList);
		return "adjustQuery";
	}

	/**
	 * @Name: edit
	 * @Description: 编辑校准信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustEdit.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String edit() {
		this.initSystemDDL();
		ElecAdjust adjust = adjustService.findAdjustById(this.getModel()
				.getAdjustID());
		request.setAttribute("adjust", adjust);
		return "edit";
	}

	/**
	 * @Name: update
	 * @Description: 编辑校准信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustEdit.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String update() {
		adjustService.update(this.getModel().getAdjustID(), this.getModel()
				.getComment(), this.getModel().getRecord());
		return "close";
	}

	/**
	 * @Name: view
	 * @Description: 查看校准信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustEdit.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String view() {
		this.initSystemDDL();
		ElecAdjust adjust = adjustService.findAdjustById(this.getModel()
				.getAdjustID());
		request.setAttribute("adjust", adjust);
		return "view";
	}

	/**
	 * @Name: add
	 * @Description: 进入到添加校准记录的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-04（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String add() {
		this.initSystemDDL();
		ElecAdjust adjust = adjustService.findAdjustById(this.getModel()
				.getAdjustID());
		request.setAttribute("adjust", adjust);
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 保存校准记录
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustAdd.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String save() {
		adjustService.save(this.getModel());
		return "close";
	}

	/**
	 * @Name: delete
	 * @Description: 删除校准信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/equapment/adjustEdit.jsp
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String delete() {
		adjustService.delete(this.getModel());
		return home();
	}

	/**
	 * @Name: exportExcel
	 * @Description: poi报表的导出
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-05（创建日期）
	 * @Parameters: 无
	 * @Return: String：
	 */
	@AnnotationLimit(mid = "ac", pid = "aa")
	public String exportExcel() throws Exception {
		// 表示excel的标题
		ArrayList<String> fieldName = adjustService.findExcelFiledName();
		// 表示excel的数据内容
		ArrayList<ArrayList<String>> fieldData = adjustService
				.findExcelFiledData(this.getModel());
		ExcelFileGenerator excelFileGenerator = new ExcelFileGenerator(
				fieldName, fieldData);
		// 重置输出流，该代码可以加，如果不加，要保证resposne缓冲区中没有其他的数据，但是建议加上
		response.reset();
		ByteArrayOutputStream os = new ByteArrayOutputStream();

		// 文件下载（添加的配置）
		String filename = "仪器设备校准报表（"
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
		List<ElecSystemDDL> jctList = adjustService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		// 校准状态
		List<ElecSystemDDL> isHavingList = adjustService
				.findSystemDDLListByKeyword("校准状态");
		request.setAttribute("isHavingList", isHavingList);
		// 设备类型
		List<ElecSystemDDL> devTypeList = adjustService
				.findSystemDDLListByKeyword("设备类型");
		request.setAttribute("devTypeList", devTypeList);
	}
}
