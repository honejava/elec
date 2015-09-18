package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

public class ElecFileUpload implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 持久化字段
	 */
	private Integer fileUploadID;
	private File file;
	private String fileFileName;
	private String fileContentType;
	// 站点信息
	private ElecBug elecBug;
	// 站点维护
	private ElecPlan elecPlan;
	// 设备校准
	private Integer adjustID;
	// 设备校验
	private Integer repairID;
	/**
	 * 非持久化字段
	 */
	private File[] files;
	private String[] filesFileName;
	private String[] filesContentType;

	public Integer getFileUploadID() {
		return fileUploadID;
	}

	public void setFileUploadID(Integer fileUploadID) {
		this.fileUploadID = fileUploadID;
	}

	public ElecBug getElecBug() {
		return elecBug;
	}

	public void setElecBug(ElecBug elecBug) {
		this.elecBug = elecBug;
	}

	public ElecPlan getElecPlan() {
		return elecPlan;
	}

	public void setElecPlan(ElecPlan elecPlan) {
		this.elecPlan = elecPlan;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public Integer getRepairID() {
		return repairID;
	}

	public void setRepairID(Integer repairID) {
		this.repairID = repairID;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public void RepairID(String fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public File[] getFiles() {
		return files;
	}

	public void setFiles(File[] files) {
		this.files = files;
	}

	public String[] getFilesFileName() {
		return filesFileName;
	}

	public void setFilesFileName(String[] filesFileName) {
		this.filesFileName = filesFileName;
	}

	public String[] getFilesContentType() {
		return filesContentType;
	}

	public void setFilesContentType(String[] filesContentType) {
		this.filesContentType = filesContentType;
	}

	public Integer getAdjustID() {
		return adjustID;
	}

	public void setAdjustID(Integer adjustID) {
		this.adjustID = adjustID;
	}

	/**
	 * 文件的下载的字段
	 */
	private String contentType;
	private InputStream targetName;
	private String filename;

	public String getContentType() {
		return contentType;
	}

	public void setContentType(String contentType) {
		this.contentType = contentType;
	}

	public InputStream getTargetName() {
		return targetName;
	}

	public void setTargetName(InputStream targetName) {
		this.targetName = targetName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

}
