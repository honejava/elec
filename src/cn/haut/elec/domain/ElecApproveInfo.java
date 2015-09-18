package cn.haut.elec.domain;


import java.util.Date;


@SuppressWarnings("serial")
public class ElecApproveInfo implements java.io.Serializable {
	
	private Integer approveID;				//主键ID
	private String comment;					//审批意见
	private boolean approval;				//审批结果，是否通过（同意或者不同意）
	private Date approveTime;				//审批日期
	
	/**映射多对一申请信息表*/
	private ElecApplication elecApplication;
	
	/**映射多对一用户信息表*/
	private ElecUser elecUser;
	

	public Integer getApproveID() {
		return approveID;
	}

	public void setApproveID(Integer approveID) {
		this.approveID = approveID;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public boolean isApproval() {
		return approval;
	}

	public void setApproval(boolean approval) {
		this.approval = approval;
	}

	public Date getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(Date approveTime) {
		this.approveTime = approveTime;
	}

	public ElecApplication getElecApplication() {
		return elecApplication;
	}

	public void setElecApplication(ElecApplication elecApplication) {
		this.elecApplication = elecApplication;
	}

	public ElecUser getElecUser() {
		return elecUser;
	}

	public void setElecUser(ElecUser elecUser) {
		this.elecUser = elecUser;
	}
	
	
	
}
