package cn.haut.elec.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class ElecRepair implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String REPAIR_ISHAVING = "2";
	public static final String REPAIR_NOHAVING = "1";

	private Integer repairID;
	private Integer equapmentID;

	private String jctID;
	private String devName;

	private String overhaulPeriod;// 检修周期
	private String opunit;

	private Date useDate;
	private String devType;
	private Date startDate;

	private String isHaving;

	private String comment;
	private String record;

	public Integer getRepairID() {
		return repairID;
	}

	public void setRepairID(Integer repairID) {
		this.repairID = repairID;
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

	public String getIsHaving() {
		return isHaving;
	}

	public void setIsHaving(String isHaving) {
		this.isHaving = isHaving;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getOverhaulPeriod() {
		return overhaulPeriod;
	}

	public void setOverhaulPeriod(String overhaulPeriod) {
		this.overhaulPeriod = overhaulPeriod;
	}

	public String getOpunit() {
		return opunit;
	}

	public void setOpunit(String opunit) {
		this.opunit = opunit;
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
	private Date startDatef;
	private Date startDatet;

	public Date getStartDatef() {
		return startDatef;
	}

	public void setStartDatef(Date startDatef) {
		this.startDatef = startDatef;
	}

	public Date getStartDatet() {
		return startDatet;
	}

	public void setStartDatet(Date startDatet) {
		this.startDatet = startDatet;
	}

	// 报表的导出
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
