package cn.haut.elec.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.struts2.ServletActionContext;
import org.hibernate.Hibernate;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.ProcessInstance;
import org.jbpm.api.history.HistoryProcessInstance;
import org.jbpm.api.model.ActivityCoordinates;
import org.jbpm.api.task.Task;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecApplicationFlowDao;
import cn.haut.elec.dao.IElecApplicationTemplateDao;
import cn.haut.elec.dao.IElecApproveInfoDao;
import cn.haut.elec.dao.IElecSystemDDLDao;
import cn.haut.elec.dao.IElecUserDao;
import cn.haut.elec.domain.ElecApplication;
import cn.haut.elec.domain.ElecApplicationTemplate;
import cn.haut.elec.domain.ElecApproveInfo;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.domain.ElecUser;
import cn.haut.elec.service.IElecApplicationFlowService;
import cn.haut.elec.utils.ValueStackUtils;

/**
 * 相当于spring容器中定义： <bean id="cn.haut.elec.service.impl.ElecTextServiceImpl"
 * class="cn.haut.elec.service.impl.ElecTextServiceImpl"/>
 */
@Service(IElecApplicationFlowService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecApplicationFlowServiceImpl implements
		IElecApplicationFlowService {

	@Resource(name = IElecApplicationFlowDao.SERVICE_NAME)
	private IElecApplicationFlowDao elecApplicationFlowDao;

	@Resource(name = IElecApplicationTemplateDao.SERVICE_NAME)
	private IElecApplicationTemplateDao elecApplicationTemplateDao;

	@Resource(name = IElecUserDao.SERVICE_NAME)
	private IElecUserDao elecUserDao;

	@Resource(name = IElecSystemDDLDao.SERVICE_NAME)
	private IElecSystemDDLDao elecSystemDDLDao;

	@Resource(name = IElecApproveInfoDao.SERVICE_NAME)
	private IElecApproveInfoDao elecApproveInfoDao;

	@Resource(name = "processEngine")
	private ProcessEngine processEngine;

	// 查询所有的模板信息
	public List<ElecApplicationTemplate> findAllApplicationTemplate() {
		return elecApplicationTemplateDao.findCollectionByConditionNoPage(null,
				null, null);
	}

	// 根据模板的id来查询到模板
	public ElecApplicationTemplate findApplicationTemplateById(String id) {
		int applicationTemplateID = Integer.parseInt(id);
		return elecApplicationTemplateDao.findObjectByID(applicationTemplateID);
	}

	// 找到所有的部门经理的用户集合信息
	public List<ElecUser> findDepartmentUser() {
		String condition = " and o.keyword=? and o.ddlName=?  ";
		Object[] params = { "职位", "部门经理" };
		// 首先找到部门经理这门职位的ddlCode
		List<ElecSystemDDL> list = elecSystemDDLDao
				.findCollectionByConditionNoPage(condition, params, null);
		if (list != null && list.size() > 0) {
			Integer ddlCode = list.get(0).getDdlCode();
			String con = " and o.postID=?  ";
			Object[] param = { ddlCode.toString() };
			// 根据用户的职位的postID来查询符合条件的用户
			List<ElecUser> userlist = elecUserDao
					.findCollectionByConditionNoPage(con, param, null);
			return userlist;
		}
		return null;
	}

	// 开启流程 保存文件的上传
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void startProcess(ElecApplication application) {
		// 获取到模板的id
		String applicationTemplateID = application.getId();
		ElecApplicationTemplate applicationTemplate = elecApplicationTemplateDao
				.findObjectByID(Integer.parseInt(applicationTemplateID));
		// 获取到流程的用户id
		String userID = application.getElecUser().getUserID();
		ElecUser elecUser = elecUserDao.findObjectByID(userID);

		// 保存文件的上传
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		String path = realPath + "\\" + application.getUploadFileName();
		File destFile = new File(realPath, application.getUploadFileName());
		try {
			FileUtils.copyFile(application.getUpload(), destFile);
		} catch (IOException e) {
			throw new RuntimeException("文件上传的时候出现异常", e);
		}
		// 保存一个application
		application.setElecUser(elecUser);
		application.setElecApplicationTemplate(applicationTemplate);
		application.setStatus(ElecApplication.APPROVEING);
		application.setApplyTime(new Date());
		application.setPath(path);
		application.setTitle(application.getUploadFileName());
		elecApplicationFlowDao.save(application);
		// 开启流程
		String processDefinitionKey = applicationTemplate
				.getProcessDefinitionKey();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("application", application);
		ProcessInstance key = processEngine.getExecutionService()
				.startProcessInstanceByKey(processDefinitionKey, map);

		String processInstanceID = key.getId();

		application.setProcessInstanceID(processInstanceID);
		// 开启流程之后 为下一步指定任务办理人
		Task task = processEngine.getTaskService().createTaskQuery()
				.processInstanceId(processInstanceID).uniqueResult();
		map.put("departmentManager", elecUser.getLogonName());
		// 设置流程变量
		processEngine.getTaskService().setVariables(task.getId(), map);
		// 完成我的个人任务 也就是【提交申请】
		processEngine.getTaskService().completeTask(task.getId());

	}

	// 找到登陆用户的所有的任务
	public List<ElecApplication> findMyTask() {
		List<ElecApplication> applicationList = new ArrayList<ElecApplication>();
		// 获取到登陆的用户
		ElecUser user = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("logonUser");

		// 获取到登陆用户的全部任务
		List<Task> list = processEngine.getTaskService().createTaskQuery()
				.assignee(user.getLogonName()).list();
		if (list != null && list.size() > 0) {
			for (Task task : list) {
				String taskID = task.getId();
				ElecApplication application = (ElecApplication) processEngine
						.getTaskService().getVariable(taskID, "application");
				applicationList.add(application);
			}
		}
		return applicationList;
	}

	// 根据application的id来查询获得application
	public ElecApplication findApplicationById(String applicationID) {
		int id = Integer.parseInt(applicationID);
		return elecApplicationFlowDao.findObjectByID(id);
	}

	// 下载一个申请文档
	public void downloadApplication(ElecApplication elecApplication) {
		String id = elecApplication.getId();
		ElecApplication application = this.findApplicationById(id);
		String path = application.getPath();
		String fileName = path.substring(path.lastIndexOf('\\') + 1,
				path.length());
		// 上传的文件的流
		ServletContext sc = ServletActionContext.getServletContext();
		InputStream inputStream = sc.getResourceAsStream("/upload/" + fileName);
		// 上传文件的类型
		String contentType = sc.getMimeType(fileName);

		elecApplication.setContentType(contentType);
		elecApplication.setFileName(fileName);
		elecApplication.setInputStream(inputStream);
		// 将该对象设置为栈顶对象
		ValueStackUtils.setValueStatck(elecApplication);
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 查询到总经理的用户集合
	public List<ElecUser> findManagerUser() {
		String condition = " and o.keyword=? and o.ddlName=?  ";
		Object[] params = { "职位", "总经理" };
		// 首先找到部门经理这门职位的ddlCode
		List<ElecSystemDDL> list = elecSystemDDLDao
				.findCollectionByConditionNoPage(condition, params, null);
		if (list != null && list.size() > 0) {
			Integer ddlCode = list.get(0).getDdlCode();
			String con = " and o.postID=?  ";
			Object[] param = { ddlCode.toString() };
			// 根据用户的职位的postID来查询符合条件的用户
			List<ElecUser> userlist = elecUserDao
					.findCollectionByConditionNoPage(con, param, null);
			return userlist;
		}
		return null;
	}

	// 找到当前任务的连线
	public Set<String> findMyTaskOutcomes(String processInstanceID) {
		Task task = processEngine.getTaskService().createTaskQuery()
				.processInstanceId(processInstanceID).uniqueResult();
		String taskID = task.getId();
		Set<String> outcomes = processEngine.getTaskService().getOutcomes(
				taskID);
		return outcomes;
	}

	// 审批一个流程
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void approveApplication(ElecApplication elecApplication) {
		ElecApproveInfo info = new ElecApproveInfo();
		// 首先获取到流程的审批状态
		boolean approval = elecApplication.isApproval();
		info.setApproval(approval);
		// 获取到流程的对象
		int id = elecApplication.getApplicationID();
		ElecApplication application = elecApplicationFlowDao.findObjectByID(id);
		info.setElecApplication(application);

		// 获取到审批者的用户信息 审批用户是登录用户
		ElecUser logonUser = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("logonUser");
		String userID = elecApplication.getElecUser().getUserID();
		ElecUser user = elecUserDao.findObjectByID(userID);
		info.setElecUser(logonUser);

		info.setApproveTime(new Date());
		info.setComment(elecApplication.getComment());
		// 保存一个审批记录
		elecApproveInfoDao.save(info);

		// 获取到完成任务的taskID
		String processInstanceID = application.getProcessInstanceID();
		Task uniqueResult = processEngine.getTaskService().createTaskQuery()
				.processInstanceId(processInstanceID).uniqueResult();
		String taskID = uniqueResult.getId();
		if (user != null) {
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("generalManager", user.getLogonName());
			// 指定下一步的流程变量
			processEngine.getTaskService().setVariables(taskID, map);
		}
		// 4：使用任务ID和连线的名称（outcome），完成当前领导审核的个人任务
		// 连线的名称
		String outcome = elecApplication.getOutcome();
		processEngine.getTaskService().completeTask(taskID, outcome);
		/*
		 * * 5:查询正在执行的执行对象表，获取流程实例的对象ProcessInstance pi，该操作一定要放置到完成任务之后
		 * 如果pi==null：流程结束 如果pi!=null:说明流程没有结束
		 */
		ProcessInstance pi = processEngine.getExecutionService()
				.createProcessInstanceQuery()
				.processInstanceId(application.getProcessInstanceID())// 使用流程实例ID查询正在执行的执行对象表
				.uniqueResult();
		if (approval) {// 如果审批 同意 那么进入到下一个审批流程
			if (pi == null) {// 如果是最后一个节点
				application.setStatus(ElecApplication.CHECK_PASS);
			} else {
				application.setStatus(ElecApplication.APPROVEING);
			}
		} else {// 如果审批 不同意 那么就结束流程
			if (pi != null) {
				processEngine.getExecutionService().endProcessInstance(
						pi.getId(), ProcessInstance.STATE_ENDED);
			}
			application.setStatus(ElecApplication.CHECK_REJECT);
		}
	}

	// 查看历史记录
	public List<ElecApproveInfo> findAllApproveInfo(
			ElecApplication elecApplication) {
		// 获取到要查看的那么流程
		String id = elecApplication.getId();
		ElecApplication application = elecApplicationFlowDao
				.findObjectByID(Integer.parseInt(id));
		String condition = "   and o.elecApplication.applicationID=?  ";
		Object[] params = { application.getApplicationID() };
		Map<String, String> orderby = null;

		List<ElecApproveInfo> list = elecApproveInfoDao
				.findCollectionByConditionNoPage(condition, params, orderby);
		for (ElecApproveInfo elecApproveInfo : list) {
			Hibernate.initialize(elecApproveInfo.getElecUser());
		}
		return list;
	}

	// 根据当前登录的用户来查询到个人申请的查询
	public List<ElecApplication> findMyApplication(ElecApplication application) {
		// 获取到登录用户的信息
		ElecUser logonUser = (ElecUser) ServletActionContext.getRequest()
				.getSession().getAttribute("logonUser");
		// 获取到查询的条件 模板的id 当前流程的审批状态
		String id = application.getId();
		String status = application.getStatus();
		StringBuffer buffer = new StringBuffer();
		List<Object> obj = new ArrayList<Object>();
		if (StringUtils.isNotEmpty(id)) {// 如果id有值
			buffer.append(" and o.elecApplicationTemplate.id=?  ");
			obj.add(Integer.parseInt(id));
		}
		if (StringUtils.isNotEmpty(status) && !status.equals("查看全部状态")) {
			buffer.append(" and o.status=?  ");
			obj.add(status);
		}

		buffer.append("  and o.elecUser.userID=?  ");
		// 获取到查寻 hql语句
		String condition = buffer.toString();
		// 获取到查询的条件
		obj.add(logonUser.getUserID());
		Object[] params = obj.toArray();

		Map<String, String> orderby = new HashMap<String, String>();
		orderby.put("  applyTime ", "desc");
		List<ElecApplication> list = elecApplicationFlowDao
				.findCollectionByConditionNoPage(condition, params, orderby);
		for (ElecApplication elecApplication : list) {
			Hibernate.initialize(elecApplication.getElecUser());
			Hibernate.initialize(elecApplication.getElecApplicationTemplate());
		}
		return list;
	}

	/** 查询坐标和部署对象ID */
	public void viewProcessPicture(ElecApplication elecApplication) {
		// 获取申请ID
		Integer applicationID = elecApplication.getApplicationID();
		ElecApplication application = elecApplicationFlowDao
				.findObjectByID(applicationID);
		// 获取审核状态，用来判断流程是否结束
		String status = application.getStatus();
		// 获取流程实例ID
		String processInstanceId = application.getProcessInstanceID();

		// 1，获取当前正在执行的活动名称
		String deploymentId = null;
		String processDefinitionId = null;
		Set<String> activityNames = null;
		// 表示流程正在执行
		if (status != null && status.equals(ElecApplication.APPROVEING)) {
			// 正在执行的，就使用ExecutionService查询
			ProcessInstance pi = processEngine.getExecutionService()
					.createProcessInstanceQuery()
					.processInstanceId(processInstanceId).uniqueResult();

			processDefinitionId = pi.getProcessDefinitionId();
			activityNames = new HashSet<String>(pi.findActiveActivityNames()); // 当前正在执行的活动的名称
		} else {
			// 已执行完的，就查询历史
			HistoryProcessInstance hpi = processEngine.getHistoryService()//
					.createHistoryProcessInstanceQuery()//
					.processInstanceId(processInstanceId)//
					.uniqueResult();

			processDefinitionId = hpi.getProcessDefinitionId();
			activityNames = new HashSet<String>();
			activityNames.add(hpi.getEndActivityName()); // 结束的活动名称

		}

		// 2，找出他们的坐标
		Set<ActivityCoordinates> coordList = new HashSet<ActivityCoordinates>();
		for (String activityName : activityNames) {
			if (StringUtils.isNotBlank(activityName)) {
				ActivityCoordinates coord = processEngine
						.getRepositoryService().getActivityCoordinates(
								processDefinitionId, activityName);
				coordList.add(coord);
			}
			// 表示jbpm4_hist_procinst表中的endActivity字段为null，则直接指向结束节点。
			else {
				ActivityCoordinates coord = processEngine
						.getRepositoryService().getActivityCoordinates(
								processDefinitionId, "end1");
				coordList.add(coord);
			}
		}
		ServletActionContext.getRequest().setAttribute("coordList", coordList);

		// 3，获取 deploymentId
		deploymentId = processEngine.getRepositoryService()
				.createProcessDefinitionQuery()//
				.processDefinitionId(processDefinitionId)//
				.uniqueResult()//
				.getDeploymentId();
		ServletActionContext.getRequest().setAttribute("deploymentId",
				deploymentId);
	}

	/** 获取输入流 */
	public InputStream findInputStreamByDeploymentId(String deploymentId) {
		String processImageName = processEngine.getRepositoryService()
				.createProcessDefinitionQuery().deploymentId(deploymentId)
				.uniqueResult().getImageResourceName();

		InputStream in = processEngine.getRepositoryService()
				.getResourceAsStream(deploymentId, processImageName);
		return in;
	}

	// 从数据字典中获取到审核的状态
	public List<ElecSystemDDL> findStatusBySystemDDL() {

		String condition="  and o.keyword=? ";
		Object[] params={ "审核状态 " };
		return elecSystemDDLDao.findCollectionByConditionNoPage(condition,
				params, null);
	}

}
