package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ElecApplication implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6290206675326757465L;

	private Integer applicationID; // 主键ID
	private String title; // 上传的文件标题
	private String path; // 上传的文件的存储路径
	private Date applyTime; // 申请日期
	private String status; // 审核状态
	private String processInstanceID; // 流程实例ID（流程实例ID，用于工作流中查看活动执行位置的流程图）

	public static final String APPROVEING = "审核中";
	public static final String CHECK_PASS = "审核通过";
	public static final String CHECK_REJECT = "审核不通过";
	/** 映射申请模板的多对一的关系 */
	private ElecApplicationTemplate elecApplicationTemplate;

	/** 映射用户的多对一的关系 */
	private ElecUser elecUser;

	/** 映射一对多的关联关系，与审核信息表 */
	Set<ElecApproveInfo> elecApproveInfos = new HashSet<ElecApproveInfo>();

	public Set<ElecApproveInfo> getElecApproveInfos() {
		return elecApproveInfos;
	}

	public void setElecApproveInfos(Set<ElecApproveInfo> elecApproveInfos) {
		this.elecApproveInfos = elecApproveInfos;
	}

	public Integer getApplicationID() {
		return applicationID;
	}

	public void setApplicationID(Integer applicationID) {
		this.applicationID = applicationID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public Date getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public ElecApplicationTemplate getElecApplicationTemplate() {
		return elecApplicationTemplate;
	}

	public void setElecApplicationTemplate(
			ElecApplicationTemplate elecApplicationTemplate) {
		this.elecApplicationTemplate = elecApplicationTemplate;
	}

	public ElecUser getElecUser() {
		return elecUser;
	}

	public void setElecUser(ElecUser elecUser) {
		this.elecUser = elecUser;
	}

	public String getProcessInstanceID() {
		return processInstanceID;
	}

	public void setProcessInstanceID(String processInstanceID) {
		this.processInstanceID = processInstanceID;
	}

	/** 非持久化对象 */
	private String id;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	// 文件的上传
	private File upload;
	private String uploadFileName;
	private String uploadcContentType;

	public File getUpload() {
		return upload;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public String getUploadFileName() {
		return uploadFileName;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public String getUploadcContentType() {
		return uploadcContentType;
	}

	public void setUploadcContentType(String uploadcContentType) {
		this.uploadcContentType = uploadcContentType;
	}

	// 文件的下载
	private String contentType;
	private InputStream inputStream;
	private String fileName;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	private boolean approval;
	private String comment;
	private String outcome;

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getOutcome() {
		return outcome;
	}

	public void setOutcome(String outcome) {
		this.outcome = outcome;
	}

}
