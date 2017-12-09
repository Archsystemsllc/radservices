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
	private int callCategoryId;

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

	@Column(name="csr_full_name")
	private String csrFullName;

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

	@Column(name="fail_reason_id")
	private int failReasonId;

	@Column(name="jur_id")
	private String jurId;

	@Column(name="mac_call_reference_number")
	private String macCallReferenceNumber;

	@Column(name="mac_id")
	private String macId;

	@Column(name="program_id")
	private int programId;

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
	private int userId;

	//bi-directional one-to-one association to FailReasonsLookup
	@OneToOne(mappedBy="scoreCard")
	private FailReasonsLookup failReasonsLookup;

	//bi-directional one-to-one association to ProgramLookup
	@OneToOne(mappedBy="scoreCard")
	private ProgramLookup programLookup;

	public ScoreCard() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCallCategoryId() {
		return this.callCategoryId;
	}

	public void setCallCategoryId(int callCategoryId) {
		this.callCategoryId = callCategoryId;
	}

	public String getCallDuration() {
		return this.callDuration;
	}

	public void setCallDuration(String callDuration) {
		this.callDuration = callDuration;
	}

	public Date getCallFailureTime() {
		return this.callFailureTime;
	}

	public void setCallFailureTime(Date callFailureTime) {
		this.callFailureTime = callFailureTime;
	}

	public String getCallLanguage() {
		return this.callLanguage;
	}

	public void setCallLanguage(String callLanguage) {
		this.callLanguage = callLanguage;
	}

	public String getCallMonitoringDate() {
		return this.callMonitoringDate;
	}

	public void setCallMonitoringDate(String callMonitoringDate) {
		this.callMonitoringDate = callMonitoringDate;
	}

	public String getCallResult() {
		return this.callResult;
	}

	public void setCallResult(String callResult) {
		this.callResult = callResult;
	}

	public String getCallTime() {
		return this.callTime;
	}

	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}

	public String getCsrFallPrivacyProv() {
		return this.csrFallPrivacyProv;
	}

	public void setCsrFallPrivacyProv(String csrFallPrivacyProv) {
		this.csrFallPrivacyProv = csrFallPrivacyProv;
	}

	public String getCsrFullName() {
		return this.csrFullName;
	}

	public void setCsrFullName(String csrFullName) {
		this.csrFullName = csrFullName;
	}

	public String getCsrLevel() {
		return this.csrLevel;
	}

	public void setCsrLevel(String csrLevel) {
		this.csrLevel = csrLevel;
	}

	public String getCsrPrvAccInfo() {
		return this.csrPrvAccInfo;
	}

	public void setCsrPrvAccInfo(String csrPrvAccInfo) {
		this.csrPrvAccInfo = csrPrvAccInfo;
	}

	public String getCsrPrvCompInfo() {
		return this.csrPrvCompInfo;
	}

	public void setCsrPrvCompInfo(String csrPrvCompInfo) {
		this.csrPrvCompInfo = csrPrvCompInfo;
	}

	public String getCsrWasCourteous() {
		return this.csrWasCourteous;
	}

	public void setCsrWasCourteous(String csrWasCourteous) {
		this.csrWasCourteous = csrWasCourteous;
	}

	public String getFailReasonComments() {
		return this.failReasonComments;
	}

	public void setFailReasonComments(String failReasonComments) {
		this.failReasonComments = failReasonComments;
	}

	public int getFailReasonId() {
		return this.failReasonId;
	}

	public void setFailReasonId(int failReasonId) {
		this.failReasonId = failReasonId;
	}

	public String getJurId() {
		return this.jurId;
	}

	public void setJurId(String jurId) {
		this.jurId = jurId;
	}

	public String getMacCallReferenceNumber() {
		return this.macCallReferenceNumber;
	}

	public void setMacCallReferenceNumber(String macCallReferenceNumber) {
		this.macCallReferenceNumber = macCallReferenceNumber;
	}

	public String getMacId() {
		return this.macId;
	}

	public void setMacId(String macId) {
		this.macId = macId;
	}

	public int getProgramId() {
		return this.programId;
	}

	public void setProgramId(int programId) {
		this.programId = programId;
	}

	public String getQamEnddateTime() {
		return this.qamEnddateTime;
	}

	public void setQamEnddateTime(String qamEnddateTime) {
		this.qamEnddateTime = qamEnddateTime;
	}

	public String getQamFullName() {
		return this.qamFullName;
	}

	public void setQamFullName(String qamFullName) {
		this.qamFullName = qamFullName;
	}

	public String getQamStartdateTime() {
		return this.qamStartdateTime;
	}

	public void setQamStartdateTime(String qamStartdateTime) {
		this.qamStartdateTime = qamStartdateTime;
	}

	public String getScorecardComments() {
		return this.scorecardComments;
	}

	public void setScorecardComments(String scorecardComments) {
		this.scorecardComments = scorecardComments;
	}

	public String getScorecardStatus() {
		return this.scorecardStatus;
	}

	public void setScorecardStatus(String scorecardStatus) {
		this.scorecardStatus = scorecardStatus;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public FailReasonsLookup getFailReasonsLookup() {
		return this.failReasonsLookup;
	}

	public void setFailReasonsLookup(FailReasonsLookup failReasonsLookup) {
		this.failReasonsLookup = failReasonsLookup;
	}

	public ProgramLookup getProgramLookup() {
		return this.programLookup;
	}

	public void setProgramLookup(ProgramLookup programLookup) {
		this.programLookup = programLookup;
	}

}