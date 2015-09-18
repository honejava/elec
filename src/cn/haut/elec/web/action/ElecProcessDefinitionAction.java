package cn.haut.elec.web.action;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.service.IElecProcessDefinitionService;
import cn.haut.elec.utils.AnnotationLimit;
import cn.haut.elec.web.form.ApplicationForm;

@SuppressWarnings("serial")
@Controller("elecProcessDefinitionAction")
@Scope(value = "prototype")
public class ElecProcessDefinitionAction extends BaseAction<ApplicationForm> {

	private ApplicationForm applicationForm = this.getModel();

	@Resource(name = IElecProcessDefinitionService.SERVICE_NAME)
	private IElecProcessDefinitionService elecElecProcessDefinitionService;

	/**
	 * @Name: home
	 * @Description: 跳转到流程管理的页面 applicationList 使用jbpm工作流来实现 在这里查询所有的流程实例
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：/WEB-INF/pages/workflow/processDefinitionList.jsp
	 */
	@AnnotationLimit(mid = "as", pid = "ar")
	public String home() {
		List<ProcessDefinition> processDefinitionList = elecElecProcessDefinitionService
				.findAllProcessDefinition();
		// 在这里去除版本的重复 使用map集合的不重复性 而且processDefinitionList集合是安装升序来排
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for (ProcessDefinition processDefinition : processDefinitionList) {
			map.put(processDefinition.getName(), processDefinition);
		}
		request.setAttribute("processDefinitionList", map.values());
		return "home";
	}

	/**
	 * @Name: add
	 * @Description: 进入到添加一个流程的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：/WEB-INF/pages/workflow/processDefinitionAdd.jsp
	 */
	@AnnotationLimit(mid = "hb", pid = "ha")
	public String add() {
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 部署流程
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-09（创建日期）
	 * @Parameters: 无
	 * @Return: String：/WEB-INF/pages/workflow/processDefinitionAdd.jsp
	 */
	@AnnotationLimit(mid = "hc", pid = "ha")
	public String save() {
		elecElecProcessDefinitionService.saveProcessDefinition(applicationForm);
		return "close";
	}

	/**
	 * @Name: downloadProcessImage
	 * @Description: 查看流程图 直接显示 而不是现在 附件
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：下载
	 */
	@AnnotationLimit(mid = "hd", pid = "ha")
	public String downloadProcessImage() {
		InputStream inputStream = elecElecProcessDefinitionService
				.lookUpProcess(applicationForm);
		applicationForm.setInputStream(inputStream);
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "downloadProcessImage";
	}

	/**
	 * @Name: delete
	 * @Description: 删除一个流程 包括该流程下的所有的版本
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到主页
	 */
	@AnnotationLimit(mid = "he", pid = "ha")
	public String delete() {
		elecElecProcessDefinitionService.deleteProcessDifition(applicationForm);
		return home();
	}

}
