package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.util.Date;

public class ElecBuilding implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer buildingID;
	private String jctID;
	private String buildName;

	private String buildType;
	private Integer buildLayer;
	private double buildArea;

	private Date buildStartDate;

	private Date extendBuildDate;
	private double extendBuildArea;
	private Date dxDate;

	private double buildExpense;
	private Date useDate;
	private String comment;

	public Integer getBuildingID() {
		return buildingID;
	}

	public void setBuildingID(Integer buildingID) {
		this.buildingID = buildingID;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public String getBuildName() {
		return buildName;
	}

	public void setBuildName(String buildName) {
		this.buildName = buildName;
	}

	public String getBuildType() {
		return buildType;
	}

	public void setBuildType(String buildType) {
		this.buildType = buildType;
	}

	public Integer getBuildLayer() {
		return buildLayer;
	}

	public void setBuildLayer(Integer buildLayer) {
		this.buildLayer = buildLayer;
	}

	public double getBuildArea() {
		return buildArea;
	}

	public void setBuildArea(double buildArea) {
		this.buildArea = buildArea;
	}

	public Date getBuildStartDate() {
		return buildStartDate;
	}

	public void setBuildStartDate(Date buildStartDate) {
		this.buildStartDate = buildStartDate;
	}

	public Date getExtendBuildDate() {
		return extendBuildDate;
	}

	public void setExtendBuildDate(Date extendBuildDate) {
		this.extendBuildDate = extendBuildDate;
	}

	public double getExtendBuildArea() {
		return extendBuildArea;
	}

	public void setExtendBuildArea(double extendBuildArea) {
		this.extendBuildArea = extendBuildArea;
	}

	public Date getDxDate() {
		return dxDate;
	}

	public void setDxDate(Date dxDate) {
		this.dxDate = dxDate;
	}

	public double getBuildExpense() {
		return buildExpense;
	}

	public void setBuildExpense(double buildExpense) {
		this.buildExpense = buildExpense;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	/**
	 * 监测台建筑物的导出字段
	 */
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 监测台建筑物的导入
	 */
	private File file;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	/**
	 * 用户jfreechar的统计
	 * 
	 * @return
	 */
	private String jctName; // 所属单位名称
	private String jctCount;// 所属单位人员数量

	public String getJctName() {
		return jctName;
	}

	public void setJctName(String jctName) {
		this.jctName = jctName;
	}

	public String getJctCount() {
		return jctCount;
	}

	public void setJctCount(String jctCount) {
		this.jctCount = jctCount;
	}

}
