package cn.haut.elec.service;

import java.util.List;

import cn.haut.elec.domain.ElecAdjust;
import cn.haut.elec.domain.ElecBug;
import cn.haut.elec.domain.ElecFileUpload;
import cn.haut.elec.domain.ElecPlan;
import cn.haut.elec.domain.ElecRepair;

public interface IElecFileUploadService {

	public static final String SERVICE_NAME = "cn.haut.elec.service.impl.ElecFileUploadServiceImpl";

	public void save(ElecFileUpload upload);

	public ElecBug findBugById(Integer bugID);

	public List<ElecFileUpload> findFileUploadByCondition();

	public ElecFileUpload findFileUploadById(Integer fileUploadID);

	public void deleteBugFileUploadById(Integer fileUploadID);

	public ElecPlan findPlanById(Integer planID);

	public List<ElecFileUpload> findFileUploadListByCondition(
			ElecFileUpload model);

	public void deletePlanFileUploadById(Integer fileUploadID);

	public List<ElecFileUpload> findAdjustFileUploadByCondition(Integer adjustID);

	public ElecAdjust finAdjustById(Integer adjustID);

	public void deleteAdjustFileUploadById(Integer fileUploadID);

	public ElecRepair finRepairById(Integer repairID);

	public List<ElecFileUpload> findRepairFileUploadByCondition(Integer repairID);

	public void deleteRepairFileUploadById(Integer fileUploadID);
	
	
	
	

}
