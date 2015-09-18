package cn.haut.elec.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class ElecAdjust implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String ADJUST_ISHAVING = "2";
	public static final String ADJUST_NOHAVING = "1";

	private Integer adjustID;

	private Integer equapmentID;

	private String jctID;
	private String devName;

	private String adjustPeriod;// 检修周期
	private String apunit;

	private Date useDate;
	private String devType;
	private Date startDate;// 校准日期

	private String isHaving;// 检修状态

	private String comment;
	private String record;// 记录描述

	public Integer getAdjustID() {
		return adjustID;
	}

	public void setAdjustID(Integer adjustID) {
		this.adjustID = adjustID;
	}

	public Integer getEquapmentID() {
		return equapmentID;
	}

	public void setEquapmentID(Integer equapmentID) {
		this.equapmentID = equapmentID;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getAdjustPeriod() {
		return adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	public String getApunit() {
		return apunit;
	}

	public void setApunit(String apunit) {
		this.apunit = apunit;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public String getIsHaving() {
		return isHaving;
	}

	public void setIsHaving(String isHaving) {
		this.isHaving = isHaving;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getRecord() {
		return record;
	}

	public void setRecord(String record) {
		this.record = record;
	}

	/**
	 * 非持久化字段
	 */
	// private Date startDatef;
	// private Date startDatet;

	// 报表的导出
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
