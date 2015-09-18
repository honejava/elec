package cn.haut.elec.service;

import java.io.InputStream;
import java.util.List;

import org.jbpm.api.ProcessDefinition;

import cn.haut.elec.web.form.ApplicationForm;

public interface IElecProcessDefinitionService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecProcessDefinitionImplService";

	public List<ProcessDefinition> findAllProcessDefinition();

	public void saveProcessDefinition(ApplicationForm applicationForm);

	public void deleteProcessDifition(ApplicationForm applicationForm);

	public InputStream lookUpProcess(ApplicationForm applicationForm);
	
	

}
