package cn.haut.elec.service.impl;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessDefinitionQuery;
import org.jbpm.api.ProcessEngine;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.haut.elec.dao.IElecApplicationTemplateDao;
import cn.haut.elec.domain.ElecApplicationTemplate;
import cn.haut.elec.service.IElecApplicationTemplateService;
import cn.haut.elec.utils.ValueStackUtils;

@Service(IElecApplicationTemplateService.SERVICE_NAME)
@Transactional(readOnly = true)
public class ElecApplicationTemplateServiceImpl implements
		IElecApplicationTemplateService {
	@Resource(name = IElecApplicationTemplateDao.SERVICE_NAME)
	private IElecApplicationTemplateDao elecApplicationTemplateDao;

	// 注入processEngine
	@Resource(name = "processEngine")
	private ProcessEngine processEngine;

	// 查看所有的模板
	public List<ElecApplicationTemplate> findAllApplicationTemplate() {

		return elecApplicationTemplateDao.findCollectionByConditionNoPage(null,
				null, null);
	}

	// 查看所有的流程的名称
	public List<ProcessDefinition> findAllprocessDefinitionKey() {
		List<ProcessDefinition> list = processEngine.getRepositoryService()
				.createProcessDefinitionQuery()
				.orderAsc(ProcessDefinitionQuery.PROPERTY_VERSION).list();
		return list;
	}

	// 保存一个流程的模板 并且实现文件的上传
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void saveApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate) {
		// 保存模板
		String realPath = ServletActionContext.getServletContext().getRealPath(
				"/upload");
		String path = realPath + "\\"
				+ elecApplicationTemplate.getUploadFileName();
		elecApplicationTemplate.setPath(path);
		elecApplicationTemplateDao.save(elecApplicationTemplate);
		// 文件的上传
		File destFile = new File(realPath,
				elecApplicationTemplate.getUploadFileName());
		try {
			FileUtils.copyFile(elecApplicationTemplate.getUpload(), destFile);
		} catch (IOException e) {
			throw new RuntimeException("文件上传的时候出现异常", e);
		}
	}

	// 下载一个模板的文件
	public void downloadApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate) {
		ServletContext sc = ServletActionContext.getServletContext();
		// 根据id来获取到模板对象
		ElecApplicationTemplate applicationTemplate = elecApplicationTemplateDao
				.findObjectByID(elecApplicationTemplate.getId());
		// 模板对象的上传的文档 路径为
		String path = applicationTemplate.getPath();
		// 上传文件的文件名
		String fileName = path.substring(path.lastIndexOf('\\') + 1,
				path.length());
		// 上传的文件的流
		InputStream inputStream = sc.getResourceAsStream("/upload/" + fileName);
		// 上传文件的类型
		String contentType = sc.getMimeType(fileName);

		elecApplicationTemplate.setContentType(contentType);
		elecApplicationTemplate.setFileName(fileName);
		elecApplicationTemplate.setInputStream(inputStream);
		// 将该对象设置为栈顶对象
		ValueStackUtils.setValueStatck(elecApplicationTemplate);
		try {
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 根据id来查询模板对象
	public ElecApplicationTemplate findApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate) {
		return elecApplicationTemplateDao
				.findObjectByID(elecApplicationTemplate.getId());
	}

	// 修改模板的信息
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void updateApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate,
			ElecApplicationTemplate template) {
		if (elecApplicationTemplate.getUpload() != null) {// 编辑的时候修改了上传文件
			String realPath = ServletActionContext.getServletContext()
					.getRealPath("/upload");
			String path = realPath + "\\"
					+ elecApplicationTemplate.getUploadFileName();
			elecApplicationTemplate.setPath(path);

			File destFile = new File(realPath,
					elecApplicationTemplate.getUploadFileName());
			try {
				// 先删除原来的文件
				new File(template.getPath()).delete();
				// 在添加新的文件
				FileUtils.copyFile(elecApplicationTemplate.getUpload(),
						destFile);
			} catch (IOException e) {
				throw new RuntimeException("文件上传的时候出现异常", e);
			}
			elecApplicationTemplateDao.update(elecApplicationTemplate);
		}
	}

	// 删除一个模板信息 而且要删除删除的文件信息
	@Transactional(isolation = Isolation.DEFAULT, propagation = Propagation.REQUIRED, readOnly = false)
	public void deleteApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate) {
		// 获得要删除的模板信息上传文件
		ElecApplicationTemplate template = elecApplicationTemplateDao
				.findObjectByID(elecApplicationTemplate.getId());
		File file = new File(template.getPath());
		if (file.exists()) {
			file.delete();
		}
		// 删除模板
		elecApplicationTemplateDao.deleteObjectByIDs(elecApplicationTemplate
				.getId());
	}
}
