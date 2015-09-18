package cn.haut.elec.service;

import java.io.InputStream;
import java.util.List;
import java.util.Set;

import cn.haut.elec.domain.ElecApplication;
import cn.haut.elec.domain.ElecApplicationTemplate;
import cn.haut.elec.domain.ElecApproveInfo;
import cn.haut.elec.domain.ElecSystemDDL;
import cn.haut.elec.domain.ElecUser;

public interface IElecApplicationFlowService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecApplicationFlowImplService";

	public List<ElecApplicationTemplate> findAllApplicationTemplate();

	public ElecApplicationTemplate findApplicationTemplateById(String id);

	public List<ElecUser> findDepartmentUser();

	public void startProcess(ElecApplication elecApplication);

	public List<ElecApplication> findMyTask();

	public ElecApplication findApplicationById(String applicationID);

	public void downloadApplication(ElecApplication elecApplication);

	public List<ElecUser> findManagerUser();

	public void approveApplication(ElecApplication elecApplication);

	public Set<String> findMyTaskOutcomes(String key);

	public List<ElecApproveInfo> findAllApproveInfo(
			ElecApplication elecApplication);

	public List<ElecApplication> findMyApplication(ElecApplication elecApplication);

	public void viewProcessPicture(ElecApplication elecApplication);

	public InputStream findInputStreamByDeploymentId(String deploymentId);

	public List<ElecSystemDDL> findStatusBySystemDDL();
	
	
	
	

}
