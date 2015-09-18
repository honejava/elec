package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class ElecDevPlan implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer devPlanID;
	private String devType;
	private String devName;

	private String trademark;
	private String specType;
	private String produceHome;

	private Integer quality;

	private String qunit;
	private String useness;
	private String produceArea;

	private double devExpense;
	private String useUnit;
	private String jctID;

	private Date planDate;

	private String adjustPeriod;
	private String apunit;

	private String overhaulPeriod;
	private String opunit;

	private String configure;

	public Integer getDevPlanID() {
		return devPlanID;
	}

	public void setDevPlanID(Integer devPlanID) {
		this.devPlanID = devPlanID;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getSpecType() {
		return specType;
	}

	public void setSpecType(String specType) {
		this.specType = specType;
	}

	public String getProduceHome() {
		return produceHome;
	}

	public void setProduceHome(String produceHome) {
		this.produceHome = produceHome;
	}

	public String getQunit() {
		return qunit;
	}

	public void setQunit(String qunit) {
		this.qunit = qunit;
	}

	public String getUseness() {
		return useness;
	}

	public void setUseness(String useness) {
		this.useness = useness;
	}

	public String getProduceArea() {
		return produceArea;
	}

	public void setProduceArea(String produceArea) {
		this.produceArea = produceArea;
	}

	public double getDevExpense() {
		return devExpense;
	}

	public void setDevExpense(double devExpense) {
		this.devExpense = devExpense;
	}

	public String getUseUnit() {
		return useUnit;
	}

	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public String getAdjustPeriod() {
		return adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	public String getOverhaulPeriod() {
		return overhaulPeriod;
	}

	public void setOverhaulPeriod(String overhaulPeriod) {
		this.overhaulPeriod = overhaulPeriod;
	}

	public String getConfigure() {
		return configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public String getApunit() {
		return apunit;
	}

	public void setApunit(String apunit) {
		this.apunit = apunit;
	}

	public String getOpunit() {
		return opunit;
	}

	public void setOpunit(String opunit) {
		this.opunit = opunit;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	/**
	 * 非持久化字段
	 */
	private Date planDatef;
	private Date planDatet;

	public Date getPlanDatef() {
		return planDatef;
	}

	public void setPlanDatef(Date planDatef) {
		this.planDatef = planDatef;
	}

	public Date getPlanDatet() {
		return planDatet;
	}

	public void setPlanDatet(Date planDatet) {
		this.planDatet = planDatet;
	}

	// 购置计划报表的导出
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	// 购置计划的导入
	private File files;

	public File getFiles() {
		return files;
	}

	public void setFiles(File files) {
		this.files = files;
	}

	// 购置计划实现
	private String plantodev;

	public String getPlantodev() {
		return plantodev;
	}

	public void setPlantodev(String plantodev) {
		this.plantodev = plantodev;
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
