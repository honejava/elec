package cn.haut.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecDataChart;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecDataChartService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.utils.ValueStackUtils;

@Controller("elecDataChartAction")
@Scope(value = "prototype")
public class ElecDataChartAction extends BaseAction<ElecDataChart> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecDataChartService.SERVICE_NAME)
	private IElecDataChartService dataChartService;

	/**
	 * @Name: home
	 * @Description: 跳转技术资料图纸的管理页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-02（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/dataChart/dataChartIndex.jsp
	 */
	@AnnotationLimit(mid = "af", pid = "ae")
	public String home() {
		this.initSystemDDL();
		List<ElecDataChart> dataChartList = dataChartService
				.findDataChartByCondition(this.getModel());
		request.setAttribute("dataChartList", dataChartList);
		String initPage = request.getParameter("initPage");
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		return "home";
	}

	/** 点击查询按钮，使用lucene进行检索 */
	@AnnotationLimit(mid = "af", pid = "ae")
	public String luceneHome() {
		if (this.getModel().getJctID().equals("0")
				&& StringUtils.isBlank(this.getModel().getQueryString())) {
			return home();
		}
		this.initSystemDDL();
		List<ElecDataChart> dataChartList = dataChartService
				.findDataChartList(this.getModel());
		request.setAttribute("dataChartList", dataChartList);
		return "home";
	}

	/**
	 * @Name: add
	 * @Description: 保存添加的资料图纸
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-02（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/dataChart/dataChartIndex.jsp
	 */
	@AnnotationLimit(mid = "af", pid = "ae")
	public String add() {
		this.initSystemDDL();
		List<ElecDataChart> dataChartList = dataChartService
				.findDataChartByCondition(this.getModel());
		request.setAttribute("dataChartList", dataChartList);
		return "add";
	}

	/** 使用ajax技术，以所属单位和图纸类别进行查询，获取对应的集合列表 */
	@AnnotationLimit(mid = "af", pid = "ae")
	public String addList() {
		List<ElecDataChart> dataChartList = dataChartService
				.findDataChartByCondition(this.getModel());
		request.setAttribute("dataChartList", dataChartList);
		return "addList";
	}

	/**
	 * @Name: save
	 * @Description: 进入到添加资料图纸的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-02（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/dataChart/dataChartAdd.jsp
	 */
	@AnnotationLimit(mid = "af", pid = "ae")
	public String save() {
		dataChartService.save(this.getModel());
		// 查询所有的图书资料
		return add();
	}

	/**
	 * @Name: download
	 * @Description: 下载 查看 图书资料
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-02（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/dataChart/dataChartAdd.jsp
	 */
	@AnnotationLimit(mid = "af", pid = "ae")
	public String download() {
		// 根据图书资料的id来查询的图书资料
		ElecDataChart chart = dataChartService.findDataChartById(this
				.getModel().getDataChartID());
		chart.setContentType(request.getServletContext().getMimeType(
				chart.getDataChartName()));
		chart.setTargetName(request.getServletContext().getResourceAsStream(
				"/dataChart/" + chart.getDataChartName()));
		chart.setFilename(chart.getDataChartName());

		ValueStackUtils.setValueStatck(chart);
		return "download";
	}

	/**
	 * @Name: delete
	 * @Description: 删除一个图书资料
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-02（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/dataChart/dataChartIndex.jsp
	 */
	@AnnotationLimit(mid = "af", pid = "ae")
	public String delete() {
		dataChartService.delete(this.getModel());
		// 查询所有的图书资料
		return home();
	}

	// 初始化数据字典
	public void initSystemDDL() {
		// 所属单位
		List<ElecSystemDDL> jctList = dataChartService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
		// 图纸类别
		List<ElecSystemDDL> belongToList = dataChartService
				.findSystemDDLListByKeyword("图纸类别");
		request.setAttribute("belongToList", belongToList);
	}
}
