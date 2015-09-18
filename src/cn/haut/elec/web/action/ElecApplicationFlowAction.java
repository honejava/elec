package cn.haut.elec.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.commons.io.IOUtils;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecApplication;
import cn.haut.elec.domain.ElecApplicationTemplate;
import cn.haut.elec.domain.ElecApproveInfo;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecApplicationFlowService;
import cn.haut.elec.utils.AnnotationLimit;

@SuppressWarnings("serial")
@Controller("elecApplicationFlowAction")
@Scope(value = "prototype")
public class ElecApplicationFlowAction extends BaseAction<ElecApplication> {

	private ElecApplication elecApplication = this.getModel();

	@Resource(name = IElecApplicationFlowService.SERVICE_NAME)
	private IElecApplicationFlowService elecApplicationFlowService;

	/**
	 * @Name: home
	 * @Description: 进入到申请管理页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowTemplateList.jsp
	 */
	@AnnotationLimit(mid = "au", pid = "ar")
	public String home() {
		// 查询所有的模板
		List<ElecApplicationTemplate> applicationTemplateList = elecApplicationFlowService
				.findAllApplicationTemplate();
		request.setAttribute("applicationTemplateList", applicationTemplateList);
		return "home";
	}

	/**
	 * @Name: submitApplication
	 * @Description: 进入到一个模板的页面管理中
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowSubmitApplication.jsp
	 */
	@AnnotationLimit(mid = "jb", pid = "ja")
	public String submitApplication() {
		// 获取到模板的id
		String id = elecApplication.getId();
		// 获取到模板信息
		ElecApplicationTemplate applicationTemplate = elecApplicationFlowService
				.findApplicationTemplateById(id);
		request.setAttribute("applicationTemplate", applicationTemplate);
		// 获取到审核人的信息
		List<ElecUser> userList = elecApplicationFlowService
				.findDepartmentUser();
		request.setAttribute("userList", userList);
		return "submitApplication";
	}

	/**
	 * @Name: saveApplication
	 * @Description: 开启一个流程 保存上传的文件
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowSubmitApplication.jsp
	 */
	@AnnotationLimit(mid = "jc", pid = "ja")
	public String saveApplication() {
		elecApplicationFlowService.startProcess(elecApplication);
		return myApplicationHome();
	}

	/**
	 * @Name: myTaskHome
	 * @Description: 进入到审批管理的页面【待我审批】
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowmyTaskHome.jsp
	 */
	@AnnotationLimit(mid = "je", pid = "ja")
	public String myTaskHome() {
		List<ElecApplication> myTaskList = elecApplicationFlowService
				.findMyTask();
		request.setAttribute("myTaskList", myTaskList);
		return "myTaskHome";
	}

	/**
	 * @Name: flowApprove
	 * @Description: 进入到审批管理的页面中
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-20（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowApprove.jsp
	 */
	@AnnotationLimit(mid = "jf", pid = "ja")
	public String flowApprove() {
		// 获取到aplication对象
		String applicationID = elecApplication.getId();
		ElecApplication application = elecApplicationFlowService
				.findApplicationById(applicationID);
		request.setAttribute("application", application);
		// 获取到下一步审批的用户集合【总经理】
		List<ElecUser> userList = elecApplicationFlowService.findManagerUser();
		request.setAttribute("userList", userList);
		// 获取到当前任务的连线
		Set<String> outcomes = elecApplicationFlowService
				.findMyTaskOutcomes(application.getProcessInstanceID());
		request.setAttribute("outcomes", outcomes);
		return "flowApprove";
	}

	/**
	 * @Name: download
	 * @Description: 下载审批的路径的文件
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-20（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowApproveInfo.jsp
	 */
	@AnnotationLimit(mid = "jg", pid = "ja")
	public String download() {
		elecApplicationFlowService.downloadApplication(elecApplication);
		return "download";
	}

	/**
	 * @Name: approve
	 * @Description: 审批一个申请
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-20（创建日期）
	 * @Parameters: 无
	 * @Return: 重定向到审批的主页 /WEB-INF/pages/workflow/flowmyTaskHome.jsp
	 */
	@AnnotationLimit(mid = "jh", pid = "ja")
	public String approve() {
		elecApplicationFlowService.approveApplication(elecApplication);
		return myTaskHome();
	}

	/**
	 * @Name: flowApprovedHistory
	 * @Description: 审批的记录
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-20（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowApproveInfo.jsp
	 */
	@AnnotationLimit(mid = "ji", pid = "ja")
	public String flowApprovedHistory() {
		// 查看历史记录
		List<ElecApproveInfo> approveInfoList = elecApplicationFlowService
				.findAllApproveInfo(elecApplication);
		request.setAttribute("approveInfoList", approveInfoList);
		return "flowApprovedHistory";
	}

	/**
	 * @Name: myApplicationHome
	 * @Description: 进入到 我的申请查询
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-20（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowMyApplicationList.jsp
	 */
	@AnnotationLimit(mid = "jd", pid = "ja")
	public String myApplicationHome() {
		// 获取到所有申请的模板对象
		List<ElecApplicationTemplate> applicationTemplatelist = elecApplicationFlowService
				.findAllApplicationTemplate();
		request.setAttribute("applicationTemplatelist", applicationTemplatelist);
		// 获取到所有的审核的状态【从数据字典中获取到】
		List<ElecSystemDDL> systemDDLList = elecApplicationFlowService
				.findStatusBySystemDDL();
		request.setAttribute("systemDDLList", systemDDLList);
		// 根据当前登录的用户来查询到个人申请的查询
		List<ElecApplication> applicationList = elecApplicationFlowService
				.findMyApplication(elecApplication);
		request.setAttribute("applicationList", applicationList);
		return "myApplicationHome";
	}

	/**
	 * @Name: approvedHistoryPic
	 * @Description: 查看流程图
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-20（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/flowMyApplicationList.jsp
	 */
	/** 查看动态流程图 */
	@AnnotationLimit(mid = "jj", pid = "ja")
	public String approvedHistoryPic() {
		/** 查看流程图的位置 */
		elecApplicationFlowService.viewProcessPicture(elecApplication);
		return "approvedHistoryPic";
	}

	/** 查看流程图 */
	@AnnotationLimit(mid = "jk", pid = "ja")
	public String processImage() {
		try {
			String deploymentId = request.getParameter("deploymentId");
			InputStream in = elecApplicationFlowService
					.findInputStreamByDeploymentId(deploymentId);
			IOUtils.copy(in, response.getOutputStream());
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
