package cn.haut.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecPlan;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.service.IElecPlanService;
import cn.haut.elec.utils.AnnotationLimit;

@Controller("elecPlanAction")
@Scope(value = "prototype")
public class ElecPlanAction extends BaseAction<ElecPlan> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	// 注入elecBugService
	@Resource(name = IElecPlanService.SERVICE_NAME)
	private IElecPlanService planService;

	/**
	 * @Name: home
	 * @Description: 跳转到站点维护的主页
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-29（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteMaintainIndex.jsp
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String home() {
		this.initSystemDDL();
		// 查询到所有的符合条件的维护计划
		List<ElecPlan> planList = planService.findPlanListByCondition(this
				.getModel());
		request.setAttribute("planList", planList);
		String initPage = request.getParameter("initPage");
		if (initPage != null && initPage.equals("1")) {
			return "list";
		}
		return "home";
	}

	/**
	 * @Name: add
	 * @Description: 跳转到站点维护添加的的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-29（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteMaintainAdd.jsp
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String add() {
		this.initSystemDDL();
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 保存站点维护信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-29（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteMaintainIndex.jsp
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String save() {
		planService.save(this.getModel());
		return "close";
	}

	/**
	 * @Name: view
	 * @Description:进入到编辑站点维护页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-29（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteMaintainView.jsp
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String view() {
		this.initSystemDDL();
		ElecPlan plan = planService.findPlanById(this.getModel().getPlanID());
		request.setAttribute("plan", plan);
		return "view";
	}

	/**
	 * @Name: edit
	 * @Description:进入到编辑站点维护页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-29（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteMaintainEdit.jsp
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String edit() {
		this.initSystemDDL();
		ElecPlan plan = planService.findPlanById(this.getModel().getPlanID());
		request.setAttribute("plan", plan);
		return "edit";
	}

	/**
	 * @Name: update
	 * @Description:更新站点维护
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-29（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteMaintainIndex.jsp
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String update() {
		planService.update(this.getModel());
		return "close";
	}

	/**
	 * @Name: delete
	 * @Description: 保存站点维护信息
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-04-29（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/siteEquapment/siteMaintainIndex.jsp
	 */
	@AnnotationLimit(mid = "aj", pid = "ag")
	public String delete() {
		// 要删除一个 站点的维护的时候 应该先要删除 elecFileUpload里面关联的数据
		
		planService.delete(this.getModel().getPlanID());
		return home();
	}

	// 初始化数据字典的内容
	public void initSystemDDL() {
		// 所属单位
		List<ElecSystemDDL> jctList = planService
				.findSystemDDLListByKeyword("所属单位");
		request.setAttribute("jctList", jctList);
	}
}
