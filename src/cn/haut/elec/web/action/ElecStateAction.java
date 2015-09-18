package cn.haut.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecBugService;
import cn.haut.elec.utils.AnnotationLimit;

@Controller("elecStateAction")
@Scope(value = "prototype")
public class ElecStateAction extends BaseAction<ElecBug> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecBugService.SERVICE_NAME)
	private IElecBugService bugService;

	/**
	 * @Name: home
	 * @Description: 跳转维护情况主页
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-27（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteStateIndex.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String home() {
		this.initSystemDDL();
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
	 * @Name: add
	 * @Description: 进入到添加维修状态的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-01（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteStateAdd.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String add() {
		this.initSystemDDL();
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 保存站点维护情况
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-05-01（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/building/siteStateAdd.jsp
	 */
	@AnnotationLimit(mid = "ai", pid = "ag")
	public String save() {
		boolean save = bugService.save(this.getModel());
		if (save) {
			return "close";
		}
		// 如果添加失败 那么返回到添加页面 并显示错误信息
		this.addFieldError("", "站点维护信息添加失败，请核对添加数据");
		return add();
	}

	// 初始化数据字典
	public void initSystemDDL() {
		// 获取到站点的名称【不重复的】
		List<String> stationList = bugService.findDistinctStationNameList();
		request.setAttribute("stationList", stationList);
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
