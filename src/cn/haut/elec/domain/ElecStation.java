package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;

public class ElecStation implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Integer stationID;
	private String jctID;
	private String stationName;

	private String stationCode;
	private Date useStartDate;
	private String jcfrequency;

	private String produceHome;
	private String contactType;
	private String stationType;

	private String attributionGround;
	private String comment;

	public Integer getStationID() {
		return stationID;
	}

	public void setStationID(Integer stationID) {
		this.stationID = stationID;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public String getStationName() {
		return stationName;
	}

	public void setStationName(String stationName) {
		this.stationName = stationName;
	}

	public String getStationCode() {
		return stationCode;
	}

	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}

	public Date getUseStartDate() {
		return useStartDate;
	}

	public void setUseStartDate(Date useStartDate) {
		this.useStartDate = useStartDate;
	}

	public String getJcfrequency() {
		return jcfrequency;
	}

	public void setJcfrequency(String jcfrequency) {
		this.jcfrequency = jcfrequency;
	}

	public String getProduceHome() {
		return produceHome;
	}

	public void setProduceHome(String produceHome) {
		this.produceHome = produceHome;
	}

	public String getContactType() {
		return contactType;
	}

	public void setContactType(String contactType) {
		this.contactType = contactType;
	}

	public String getStationType() {
		return stationType;
	}

	public void setStationType(String stationType) {
		this.stationType = stationType;
	}

	public String getAttributionGround() {
		return attributionGround;
	}

	public void setAttributionGround(String attributionGround) {
		this.attributionGround = attributionGround;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	/**
	 * 站点信息的导出字段
	 */
	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	/**
	 * 站点信息的导入
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
