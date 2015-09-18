package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class ElecBug implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer bugID;
	private String bugType;
	private Date occurDate;

	private String produceHome;
	private String bugDescribe;
	private Date resolveDate;

	private String resolveMethod;
	private String btnResolve;
	private String bugReason;

	private String comment;

	private String sbYearFrom;
	private String sbMonthFrom;
	private String sbYearTo;
	private String sbMonthTo;

	private Date bugTimeFrom;
	private Date bugTimeTo;

	private String sbYear;
	private String sbMonth;

	// 站点和运行时候出现的错误是一对多的关系 一个站点可能出现去多bug
	private ElecStation elecStation;

	public Integer getBugID() {
		return bugID;
	}

	public void setBugID(Integer bugID) {
		this.bugID = bugID;
	}

	public String getBugType() {
		return bugType;
	}

	public void setBugType(String bugType) {
		this.bugType = bugType;
	}

	public Date getOccurDate() {
		return occurDate;
	}

	public void setOccurDate(Date occurDate) {
		this.occurDate = occurDate;
	}

	public String getProduceHome() {
		return produceHome;
	}

	public void setProduceHome(String produceHome) {
		this.produceHome = produceHome;
	}

	public String getBugDescribe() {
		return bugDescribe;
	}

	public void setBugDescribe(String bugDescribe) {
		this.bugDescribe = bugDescribe;
	}

	public Date getResolveDate() {
		return resolveDate;
	}

	public void setResolveDate(Date resolveDate) {
		this.resolveDate = resolveDate;
	}

	public String getResolveMethod() {
		return resolveMethod;
	}

	public void setResolveMethod(String resolveMethod) {
		this.resolveMethod = resolveMethod;
	}

	public String getBtnResolve() {
		return btnResolve;
	}

	public void setBtnResolve(String btnResolve) {
		this.btnResolve = btnResolve;
	}

	public String getBugReason() {
		return bugReason;
	}

	public void setBugReason(String bugReason) {
		this.bugReason = bugReason;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getSbYearFrom() {
		return sbYearFrom;
	}

	public void setSbYearFrom(String sbYearFrom) {
		this.sbYearFrom = sbYearFrom;
	}

	public String getSbMonthFrom() {
		return sbMonthFrom;
	}

	public void setSbMonthFrom(String sbMonthFrom) {
		this.sbMonthFrom = sbMonthFrom;
	}

	public String getSbYearTo() {
		return sbYearTo;
	}

	public void setSbYearTo(String sbYearTo) {
		this.sbYearTo = sbYearTo;
	}

	public String getSbMonthTo() {
		return sbMonthTo;
	}

	public void setSbMonthTo(String sbMonthTo) {
		this.sbMonthTo = sbMonthTo;
	}

	public Date getBugTimeFrom() {
		return bugTimeFrom;
	}

	public void setBugTimeFrom(Date bugTimeFrom) {
		this.bugTimeFrom = bugTimeFrom;
	}

	public Date getBugTimeTo() {
		return bugTimeTo;
	}

	public void setBugTimeTo(Date bugTimeTo) {
		this.bugTimeTo = bugTimeTo;
	}

	public ElecStation getElecStation() {
		return elecStation;
	}

	public void setElecStation(ElecStation elecStation) {
		this.elecStation = elecStation;
	}

	public String getSbYear() {
		return sbYear;
	}

	public void setSbYear(String sbYear) {
		this.sbYear = sbYear;
	}

	public String getSbMonth() {
		return sbMonth;
	}

	public void setSbMonth(String sbMonth) {
		this.sbMonth = sbMonth;
	}

	/**
	 * 非持久化字段 故障的时间 总故障时间
	 */
	private double hour;

	public double getHour() {
		return hour;
	}

	public void setHour(double hour) {
		this.hour = hour;
	}

	/**
	 * 导出
	 */
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 导入
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
