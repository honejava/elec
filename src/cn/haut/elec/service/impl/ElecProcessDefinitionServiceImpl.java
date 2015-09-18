package cn.haut.elec.service.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.zip.ZipInputStream;

import javax.annotation.Resource;

import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.service.IElecProcessDefinitionService;
import cn.haut.elec.web.form.ApplicationForm;

/**
 * 相当于spring容器中定义： <bean id="cn.haut.elec.service.impl.ElecTextServiceImpl"
 * class="cn.haut.elec.service.impl.ElecTextServiceImpl"/>
 */
@Service(IElecProcessDefinitionService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecProcessDefinitionServiceImpl implements
		IElecProcessDefinitionService {

	// 注入流程引擎
	@Resource(name = "processEngine")
	private ProcessEngine processEngine;

	// 查询到当前的所有的流程
	public List<ProcessDefinition> findAllProcessDefinition() {
		List<ProcessDefinition> list = processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION).list();
		return list;
	}

	// 部署流程
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveProcessDefinition(ApplicationForm applicationForm) {
		try {
			ZipInputStream zipinputStream = new ZipInputStream(
					new FileInputStream(applicationForm.getUpload()));
			String deploy = processEngine.getRepositoryService()
					.createDeployment()
					.addResourcesFromZipInputStream(zipinputStream).deploy();
			System.out.println(deploy);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	// 删除一个流程 包括该流程下的所有的版本
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteProcessDifition(ApplicationForm applicationForm) {
		// 这里要处理乱码 在js中 如果使用 href或者window.open()的时候可能出现乱码问题
		String key = applicationForm.getKey();
		try {
			key = new String(key.getBytes("iso-8859-1"), "utf-8");
			// 获取到当前流程的id
			List<ProcessDefinition> list = processEngine.getRepositoryService()
					.createProcessDefinitionQuery().processDefinitionKey(key)
					.list();
			// 根据流程的id来删除
			for (ProcessDefinition processDefinition : list) {
				String id = processDefinition.getDeploymentId();
				processEngine.getRepositoryService()
						.deleteDeploymentCascade(id);
			}
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
	}

	// 查看一个流程
	public InputStream lookUpProcess(ApplicationForm applicationForm) {
		String id = applicationForm.getId();
		try {
			id = new String(id.getBytes("iso-8859-1"), "utf-8");
			ProcessDefinition uniqueResult = processEngine
					.getRepositoryService().createProcessDefinitionQuery()
					.processDefinitionId(id).uniqueResult();
			InputStream resourceAsStream = processEngine.getRepositoryService()
					.getResourceAsStream(uniqueResult.getDeploymentId(),
							uniqueResult.getImageResourceName());
			return resourceAsStream;
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException("查看流程出现异常", e);
		}
	}
}
