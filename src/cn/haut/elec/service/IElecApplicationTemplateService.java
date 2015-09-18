package cn.haut.elec.service;

import java.util.List;

import org.jbpm.api.ProcessDefinition;

import cn.haut.elec.domain.ElecApplicationTemplate;

public interface IElecApplicationTemplateService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecApplicationTemplateServiceImpl";

	public List<ElecApplicationTemplate> findAllApplicationTemplate();

	public List<ProcessDefinition> findAllprocessDefinitionKey();

	public void saveApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate);

	public void downloadApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate);

	public ElecApplicationTemplate findApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate);

	public void updateApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate, ElecApplicationTemplate template);

	public void deleteApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate);
	
	
	
	

}
