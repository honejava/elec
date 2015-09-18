package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;

public class ElecDataChart implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer dataChartID;

	private String jctID;

	private String belongTo;

	private String dataChartName;

	private String comment;

	public void setComment(String comment) {
		this.comment = comment;
	}

	public Integer getDataChartID() {
		return dataChartID;
	}

	public void setDataChartID(Integer dataChartID) {
		this.dataChartID = dataChartID;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public String getBelongTo() {
		return belongTo;
	}

	public void setBelongTo(String belongTo) {
		this.belongTo = belongTo;
	}

	public String getDataChartName() {
		return dataChartName;
	}

	public void setDataChartName(String dataChartName) {
		this.dataChartName = dataChartName;
	}

	public String getComment() {
		return comment;
	}

	public void DataChartName(String comment) {
		this.comment = comment;
	}

	/**
	 * 非持久化 字段
	 */
	// 多文件的上传
	private File[] files;
	private String[] filesFileName;
	private String[] filesContentType;

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

	// 全文检索
	private String queryString;

	public String getQueryString() {
		return queryString;
	}

	public void setQueryString(String queryString) {
		this.queryString = queryString;
	}

}
