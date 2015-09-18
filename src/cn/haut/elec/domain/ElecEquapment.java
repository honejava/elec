package cn.haut.elec.domain;

import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class ElecEquapment implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public static final String DEV_STATE_NORMAL = "1";
	public static final String DEV_STATE_CHECK = "2";
	public static final String DEV_STATE_REPAIR = "3";

	private Integer equapmentID;
	private String devName;
	private String jctID;

	private String devType;
	private Integer quality;
	private String qunit;

	private double devExpense;
	private String useUnit;

	private String configure;// 配置
	private String specType;// 规格型号
	private String trademark;// 品牌

	private String devState;// 设备状态
	private String produceHome;
	private String produceArea;

	private String useness;

	private String overhaulPeriod;
	private String opunit;

	private Date useDate;
	private Date planDate;

	private String adjustPeriod;// 校验周期
	private String apunit;

	private String runDescribe;

	private String comment;

	public String getQunit() {
		return qunit;
	}

	public void setQunit(String qunit) {
		this.qunit = qunit;
	}

	public String getOpunit() {
		return opunit;
	}

	public void setOpunit(String opunit) {
		this.opunit = opunit;
	}

	public String getApunit() {
		return apunit;
	}

	public void setApunit(String apunit) {
		this.apunit = apunit;
	}

	public Date getPlanDate() {
		return planDate;
	}

	public void setPlanDate(Date planDate) {
		this.planDate = planDate;
	}

	public Integer getEquapmentID() {
		return equapmentID;
	}

	public void setEquapmentID(Integer equapmentID) {
		this.equapmentID = equapmentID;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public String getDevType() {
		return devType;
	}

	public void setDevType(String devType) {
		this.devType = devType;
	}

	public Integer getQuality() {
		return quality;
	}

	public void setQuality(Integer quality) {
		this.quality = quality;
	}

	public double getDevExpense() {
		return devExpense;
	}

	public void setDevExpense(double devExpense) {
		this.devExpense = devExpense;
	}

	public String getConfigure() {
		return configure;
	}

	public void setConfigure(String configure) {
		this.configure = configure;
	}

	public String getSpecType() {
		return specType;
	}

	public void setSpecType(String specType) {
		this.specType = specType;
	}

	public String getTrademark() {
		return trademark;
	}

	public void setTrademark(String trademark) {
		this.trademark = trademark;
	}

	public String getDevState() {
		return devState;
	}

	public void setDevState(String devState) {
		this.devState = devState;
	}

	public String getProduceHome() {
		return produceHome;
	}

	public void setProduceHome(String produceHome) {
		this.produceHome = produceHome;
	}

	public String getProduceArea() {
		return produceArea;
	}

	public void setProduceArea(String produceArea) {
		this.produceArea = produceArea;
	}

	public String getUseness() {
		return useness;
	}

	public void setUseness(String useness) {
		this.useness = useness;
	}

	public String getUseUnit() {
		return useUnit;
	}

	public void setUseUnit(String useUnit) {
		this.useUnit = useUnit;
	}

	public String getOverhaulPeriod() {
		return overhaulPeriod;
	}

	public void setOverhaulPeriod(String overhaulPeriod) {
		this.overhaulPeriod = overhaulPeriod;
	}

	public Date getUseDate() {
		return useDate;
	}

	public void setUseDate(Date useDate) {
		this.useDate = useDate;
	}

	public String getAdjustPeriod() {
		return adjustPeriod;
	}

	public void setAdjustPeriod(String adjustPeriod) {
		this.adjustPeriod = adjustPeriod;
	}

	public String getRunDescribe() {
		return runDescribe;
	}

	public void setRunDescribe(String runDescribe) {
		this.runDescribe = runDescribe;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 非持久化字段
	 */
	private Date useDatef;
	private Date useDatet;
	private Date planDatef;
	private Date planDatet;

	public Date getUseDatef() {
		return useDatef;
	}

	public void setUseDatef(Date useDatef) {
		this.useDatef = useDatef;
	}

	public Date getUseDatet() {
		return useDatet;
	}

	public void setUseDatet(Date useDatet) {
		this.useDatet = useDatet;
	}

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

	// 导出报表
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
