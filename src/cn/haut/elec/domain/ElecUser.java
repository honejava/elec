package cn.haut.elec.domain;

import java.io.File;
import java.io.InputStream;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class ElecUser implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5935404173812511731L;
	private String userID; // 主键ID
	private String jctID; // 所属单位code
	private String jctUnitID; // 所属单位的单位名称（联动）

	private String userName; // 用户姓名
	private String logonName; // 登录名
	private String logonPwd; // 密码

	private String sexID; // 性别
	private Date birthday; // 出生日期
	private String address; // 联系地址

	private String contactTel; // 联系电话
	private String email; // 电子邮箱
	private String mobile; // 手机

	private String isDuty; // 是否在职
	private String postID; // 职位
	private Date onDutyDate; // 入职时间

	private Date offDutyDate; // 离职时间
	private String remark; // 备注

	public final static String INITPASSWORD = "123"; // 初始化的密码

	// 【一概一个用户对应一个用户权限】
	/** 这里与问题吧 【应该一个用户对应一个权限 要不然怎么判断用户的权限？？？？？？？？？？？？】 */
	private Set<ElecRole> elecRoles = new HashSet<ElecRole>();

	/** 映射与申请信息表的申请人ID建议一对多的关系 */
	private Set<ElecApplication> elecApplications = new HashSet<ElecApplication>();

	/** 映射与审核信息表的审核人ID建议一对多的关系 */
	private Set<ElecApproveInfo> elecApproveInfos = new HashSet<ElecApproveInfo>();

	public Set<ElecApproveInfo> getElecApproveInfos() {
		return elecApproveInfos;
	}

	public void setElecApproveInfos(Set<ElecApproveInfo> elecApproveInfos) {
		this.elecApproveInfos = elecApproveInfos;
	}

	public Set<ElecApplication> getElecApplications() {
		return elecApplications;
	}

	public void setElecApplications(Set<ElecApplication> elecApplications) {
		this.elecApplications = elecApplications;
	}

	public Set<ElecRole> getElecRoles() {
		return elecRoles;
	}

	public void setElecRoles(Set<ElecRole> elecRoles) {
		this.elecRoles = elecRoles;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getJctID() {
		return jctID;
	}

	public void setJctID(String jctID) {
		this.jctID = jctID;
	}

	public String getJctUnitID() {
		return jctUnitID;
	}

	public void setJctUnitID(String jctUnitID) {
		this.jctUnitID = jctUnitID;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getLogonName() {
		return logonName;
	}

	public void setLogonName(String logonName) {
		this.logonName = logonName;
	}

	public String getLogonPwd() {
		return logonPwd;
	}

	public void setLogonPwd(String logonPwd) {
		this.logonPwd = logonPwd;
	}

	public String getSexID() {
		return sexID;
	}

	public void setSexID(String sexID) {
		this.sexID = sexID;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContactTel() {
		return contactTel;
	}

	public void setContactTel(String contactTel) {
		this.contactTel = contactTel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIsDuty() {
		return isDuty;
	}

	public void setIsDuty(String isDuty) {
		this.isDuty = isDuty;
	}

	public String getPostID() {
		return postID;
	}

	public void setPostID(String postID) {
		this.postID = postID;
	}

	public Date getOnDutyDate() {
		return onDutyDate;
	}

	public void setOnDutyDate(Date onDutyDate) {
		this.onDutyDate = onDutyDate;
	}

	public Date getOffDutyDate() {
		return offDutyDate;
	}

	public void setOffDutyDate(Date offDutyDate) {
		this.offDutyDate = offDutyDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	private Date onDutyDateBegin;
	private Date onDutyDateEnd;

	public Date getOnDutyDateBegin() {
		return onDutyDateBegin;
	}

	public void setOnDutyDateBegin(Date onDutyDateBegin) {
		this.onDutyDateBegin = onDutyDateBegin;
	}

	public Date getOnDutyDateEnd() {
		return onDutyDateEnd;
	}

	public void setOnDutyDateEnd(Date onDutyDateEnd) {
		this.onDutyDateEnd = onDutyDateEnd;
	}

	/** 验证登录名是否存在的标识 */
	private String message;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/** 进入到 编辑用户和查看用户的标示符 */
	private String viewflag;

	public String getViewflag() {
		return viewflag;
	}

	public void setViewflag(String viewflag) {
		this.viewflag = viewflag;
	}

	/** 角色的flag */
	private boolean flag;

	public boolean isFlag() {
		return flag;
	}

	public void setFlag(boolean flag) {
		this.flag = flag;
	}

	private InputStream inputStream;

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

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
