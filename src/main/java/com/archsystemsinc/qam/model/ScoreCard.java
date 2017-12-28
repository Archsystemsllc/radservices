package com.archsystemsinc.qam.model;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the score_card database table.
 * 
 */
@Entity
@Table(name="score_card")
@NamedQuery(name="ScoreCard.findAll", query="SELECT s FROM ScoreCard s")
public class ScoreCard implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="call_category_id")
	private Integer callCategoryId;	

	@Column(name="csr_full_name")
	private String csrFullName;
	
	@Column(name="call_duration")
	private String callDuration;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="call_failure_time")
	private Date callFailureTime;

	@Column(name="call_language")
	private String callLanguage;

	@Column(name="call_monitoring_date")
	private String callMonitoringDate;

	@Column(name="call_result")
	private String callResult;

	@Column(name="call_time")
	private String callTime;

	@Column(name="csr_fall_privacy_prov")
	private String csrFallPrivacyProv;

	@Column(name="csr_level")
	private String csrLevel;

	@Column(name="csr_prv_acc_info")
	private String csrPrvAccInfo;

	@Column(name="csr_prv_comp_info")
	private String csrPrvCompInfo;

	@Column(name="csr_was_courteous")
	private String csrWasCourteous;
	
	@Column(name="fail_reason_comments")
	private String failReasonComments;
	
	@Column(name="fail_reason_add_comments")
	private String failReasonAdditionalComments;
	
	@Column(name="fail_reason_id")
	private Integer failReasonId;
	
	@Column(name="jur_id")
	private Integer jurId;
	
	@Column(name="mac_call_reference_number")
	private String macCallReferenceNumber;

	@Column(name="mac_id")
	private Integer macId;

	@Column(name="program_id")
	private Integer programId;

	@Column(name="qam_enddate_time")
	private String qamEnddateTime;

	@Column(name="qam_full_name")
	private String qamFullName;

	@Column(name="qam_startdate_time")
	private String qamStartdateTime;

	@Column(name="scorecard_comments")
	private String scorecardComments;

	@Column(name="scorecard_status")
	private String scorecardStatus;
	

	@Column(name="user_id")
	private Integer userId;
	
	
	

	public String getFailReasonAdditionalComments() {
		return failReasonAdditionalComments;
	}



	public void setFailReasonAdditionalComments(String failReasonAdditionalComments) {
		this.failReasonAdditionalComments = failReasonAdditionalComments;
	}



	public ScoreCard() {
	}



	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public Integer getCallCategoryId() {
		return callCategoryId;
	}



	public void setCallCategoryId(Integer callCategoryId) {
		this.callCategoryId = callCategoryId;
	}



	public String getCsrFullName() {
		return csrFullName;
	}



	public void setCsrFullName(String csrFullName) {
		this.csrFullName = csrFullName;
	}



	public String getCallDuration() {
		return callDuration;
	}



	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}



	public Date getCallFailureTime() {
		return callFailureTime;
	}



	public void setCallFailureTime(Date callFailureTime) {
		this.callFailureTime = callFailureTime;
	}



	public String getCallLanguage() {
		return callLanguage;
	}



	public void setCallLanguage(String callLanguage) {
		this.callLanguage = callLanguage;
	}



	public String getCallMonitoringDate() {
		return callMonitoringDate;
	}



	public void setCallMonitoringDate(String callMonitoringDate) {
		this.callMonitoringDate = callMonitoringDate;
	}



	public String getCallResult() {
		return callResult;
	}



	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}



	public String getCallTime() {
		return callTime;
	}



	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}



	public String getCsrFallPrivacyProv() {
		return csrFallPrivacyProv;
	}



	public void setCsrFallPrivacyProv(String csrFallPrivacyProv) {
		this.csrFallPrivacyProv = csrFallPrivacyProv;
	}



	public String getCsrLevel() {
		return csrLevel;
	}



	public void setCsrLevel(String csrLevel) {
		this.csrLevel = csrLevel;
	}



	public String getCsrPrvAccInfo() {
		return csrPrvAccInfo;
	}



	public void setCsrPrvAccInfo(String csrPrvAccInfo) {
		this.csrPrvAccInfo = csrPrvAccInfo;
	}



	public String getCsrPrvCompInfo() {
		return csrPrvCompInfo;
	}



	public void setCsrPrvCompInfo(String csrPrvCompInfo) {
		this.csrPrvCompInfo = csrPrvCompInfo;
	}



	public String getCsrWasCourteous() {
		return csrWasCourteous;
	}



	public void setCsrWasCourteous(String csrWasCourteous) {
		this.csrWasCourteous = csrWasCourteous;
	}



	public String getFailReasonComments() {
		return failReasonComments;
	}



	public void setFailReasonComments(String failReasonComments) {
		this.failReasonComments = failReasonComments;
	}



	public Integer getFailReasonId() {
		return failReasonId;
	}



	public void setFailReasonId(Integer failReasonId) {
		this.failReasonId = failReasonId;
	}



	public Integer getJurId() {
		return jurId;
	}



	public void setJurId(Integer jurId) {
		this.jurId = jurId;
	}



	public String getMacCallReferenceNumber() {
		return macCallReferenceNumber;
	}



	public void setMacCallReferenceNumber(String macCallReferenceNumber) {
		this.macCallReferenceNumber = macCallReferenceNumber;
	}



	public Integer getMacId() {
		return macId;
	}



	public void setMacId(Integer macId) {
		this.macId = macId;
	}



	public Integer getProgramId() {
		return programId;
	}



	public void setProgramId(Integer programId) {
		this.programId = programId;
	}



	public String getQamEnddateTime() {
		return qamEnddateTime;
	}



	public void setQamEnddateTime(String qamEnddateTime) {
		this.qamEnddateTime = qamEnddateTime;
	}



	public String getQamFullName() {
		return qamFullName;
	}



	public void setQamFullName(String qamFullName) {
		this.qamFullName = qamFullName;
	}



	public String getQamStartdateTime() {
		return qamStartdateTime;
	}



	public void setQamStartdateTime(String qamStartdateTime) {
		this.qamStartdateTime = qamStartdateTime;
	}



	public String getScorecardComments() {
		return scorecardComments;
	}



	public void setScorecardComments(String scorecardComments) {
		this.scorecardComments = scorecardComments;
	}



	public String getScorecardStatus() {
		return scorecardStatus;
	}



	public void setScorecardStatus(String scorecardStatus) {
		this.scorecardStatus = scorecardStatus;
	}



	public Integer getUserId() {
		return userId;
	}



	public void setUserId(Integer userId) {
		this.userId = userId;
	}	

}