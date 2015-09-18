package cn.haut.elec.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import cn.haut.elec.domain.ElecApplicationTemplate;
import cn.haut.elec.service.IElecApplicationTemplateService;
import cn.haut.elec.utils.AnnotationLimit;

@SuppressWarnings("serial")
@Controller("elecApplicationTemplateAction")
@Scope(value = "prototype")
public class ElecApplicationTemplateAction extends
		BaseAction<ElecApplicationTemplate> {

	private ElecApplicationTemplate elecApplicationTemplate = this.getModel();

	@Resource(name = IElecApplicationTemplateService.SERVICE_NAME)
	private IElecApplicationTemplateService elecApplicationTemplateService;

	/**
	 * @Name: home
	 * @Description: 进入到模板管理页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/applicationTemplateList.jsp
	 */
	@AnnotationLimit(mid = "at", pid = "ar")
	public String home() {
		// 查询所有的模板
		List<ElecApplicationTemplate> applicationTemplateList = elecApplicationTemplateService
				.findAllApplicationTemplate();
		request.setAttribute("applicationTemplateList", applicationTemplateList);
		return "home";
	}

	/**
	 * @Name: add
	 * @Description: 进入到模板管理页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：跳转到/WEB-INF/pages/workflow/applicationTemplateList.jsp
	 */
	@AnnotationLimit(mid = "ib", pid = "ia")
	public String add() {
		// 获取到所有的流程的key
		List<ProcessDefinition> ProcessDefinitionList = elecApplicationTemplateService
				.findAllprocessDefinitionKey();
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for (ProcessDefinition processDefinition : ProcessDefinitionList) {
			map.put(processDefinition.getName(), processDefinition);
		}
		request.setAttribute("ProcessDefinitionList", map.values());
		return "add";
	}

	/**
	 * @Name: save
	 * @Description: 保存模板管理页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到主页
	 */
	@AnnotationLimit(mid = "ic", pid = "ia")
	public String save() {
		elecApplicationTemplateService
				.saveApplicationTemplate(elecApplicationTemplate);
		return "close";
	}

	/**
	 * @Name: delete
	 * @Description: 删除一个模板
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到主页
	 */
	@AnnotationLimit(mid = "if", pid = "ia")
	public String delete() {
		elecApplicationTemplateService
				.deleteApplicationTemplate(elecApplicationTemplate);
		return home();
	}

	/**
	 * @Name: edit
	 * @Description: 进入到编辑模板的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-17（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到主页
	 */
	@AnnotationLimit(mid = "id", pid = "ia")
	public String edit() {
		// 获取到所有的流程的key 并且是最新的版本
		List<ProcessDefinition> processDefinitionList = elecApplicationTemplateService
				.findAllprocessDefinitionKey();
		Map<String, ProcessDefinition> map = new HashMap<String, ProcessDefinition>();
		for (ProcessDefinition processDefinition : processDefinitionList) {
			map.put(processDefinition.getName(), processDefinition);
		}
		request.setAttribute("processDefinitionList", map.values());
		// 获取到当前的模板对象
		ElecApplicationTemplate applicationTemplate = elecApplicationTemplateService
				.findApplicationTemplate(elecApplicationTemplate);
		request.setAttribute("applicationTemplate", applicationTemplate);
		return "edit";
	}

	/**
	 * @Name: download
	 * @Description: 下载模板管理的页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：
	 */
	@AnnotationLimit(mid = "ig", pid = "ia")
	public String download() {
		elecApplicationTemplateService
				.downloadApplicationTemplate(elecApplicationTemplate);
		return "download";
	}

	/**
	 * @Name: update
	 * @Description: 更新模板管理页面
	 * @Author: 甘亮（作者）
	 * @Version: V1.00 （版本号）
	 * @Create Date: 2014-02-18（创建日期）
	 * @Parameters: 无
	 * @Return: String：重定向到主页
	 */
	@AnnotationLimit(mid = "ie", pid = "ia")
	public String update() {
		ElecApplicationTemplate template = elecApplicationTemplateService
				.findApplicationTemplate(elecApplicationTemplate);
		elecApplicationTemplateService.updateApplicationTemplate(
				elecApplicationTemplate, template);
		return "close";

	}

}
