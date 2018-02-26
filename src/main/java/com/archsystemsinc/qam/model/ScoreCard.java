package com.archsystemsinc.qam.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;


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
	
	@Column(name="call_sub_category_id")
	private Integer callSubCategoryId;

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
	
	@Column(name="fail_reason")
	private String failReason;
	
	@Column(name="jur_id")
	private Integer jurId;
	
	@Column(name="mac_call_reference_number")
	private String macCallReferenceNumber;

	@Column(name="mac_id")
	private Integer macId;

	@Column(name="program_id")
	private Integer programId;
	
	@Column(name="qam_enddate_time_2")
	//@Convert(converter = StringToDateConverter.class)
	private Date qamEnddateTime;

	@Column(name="qam_full_name")
	private String qamFullName;
	
	@Column(name="qam_startdate_time_2")
	//@Convert(converter = StringToDateConverter.class)
	private Date qamStartdateTime;

	@Column(name="scorecard_comments")
	private String scorecardComments;

	@Column(name="scorecard_status")
	private String scorecardStatus;
	
	@Column(name="scorecard_type")
	private String scorecardType;
	
	@Column(name="lob")
	private String lob;
	

	@Column(name="user_id")
	private Integer userId;
	
	@Column(name="accuracy_call_failure_reason")
	private String accuracyCallFailureReason;
	
	@Column(name="accuracy_call_failure_time")
	private String accuracyCallFailureTime;
	
	@Column(name="completeness_call_failure_reason")
	private String completenessCallFailureReason;
	
	@Column(name="completeness_call_failure_time")
	private String completenessCallFailureTime;
	
	@Column(name="privacy_call_failure_reason")
	private String privacyCallFailureReason;
	
	@Column(name="privacy_call_failure_time")
	private String privacyCallFailureTime;
	
	@Column(name="customer_skills_call_failure_reason")
	private String customerSkillsCallFailureReason;
	
	@Column(name="customer_skills_call_failure_time")
	private String customerSkillsCallFailureTime;
	
	@Transient
	private Date filterFromDate;
	
	@Transient
	private Date filterToDate;
	
	@Transient
	private String macIdReportSearchString;	
	
	@Transient
	private String jurisIdReportSearchString;
	
	@Transient
	private String programIdReportSearchString;
	
	//Temporary Variables
	@Transient
	private String jurisdictionName;	
	
	@Transient
	private String macName;	
	
	@Transient
	private ArrayList<Integer> jurIdList;	
	
	@Transient
	private ArrayList<Integer> macIdList;	
	
	
	public ScoreCard() {
	}


	public ArrayList<Integer> getJurIdList() {
		return jurIdList;
	}




	public void setJurIdList(ArrayList<Integer> jurIdList) {
		this.jurIdList = jurIdList;
	}




	public ArrayList<Integer> getMacIdList() {
		return macIdList;
	}




	public void setMacIdList(ArrayList<Integer> macIdList) {
		this.macIdList = macIdList;
	}




	public String getJurisdictionName() {
		return jurisdictionName;
	}





	public void setJurisdictionName(String jurisdictionName) {
		this.jurisdictionName = jurisdictionName;
	}





	public String getMacName() {
		return macName;
	}





	public void setMacName(String macName) {
		this.macName = macName;
	}





	public String getMacIdReportSearchString() {
		return macIdReportSearchString;
	}








	public void setMacIdReportSearchString(String macIdReportSearchString) {
		this.macIdReportSearchString = macIdReportSearchString;
	}








	public String getJurisIdReportSearchString() {
		return jurisIdReportSearchString;
	}








	public void setJurisIdReportSearchString(String jurisIdReportSearchString) {
		this.jurisIdReportSearchString = jurisIdReportSearchString;
	}








	public String getProgramIdReportSearchString() {
		return programIdReportSearchString;
	}








	public void setProgramIdReportSearchString(String programIdReportSearchString) {
		this.programIdReportSearchString = programIdReportSearchString;
	}








	public Date getQamEnddateTime() {
		return qamEnddateTime;
	}




	public void setQamEnddateTime(Date qamEnddateTime) {
		this.qamEnddateTime = qamEnddateTime;
	}




	public Date getQamStartdateTime() {
		return qamStartdateTime;
	}




	public void setQamStartdateTime(Date qamStartdateTime) {
		this.qamStartdateTime = qamStartdateTime;
	}




	public Date getFilterFromDate() {
		return filterFromDate;
	}


	public void setFilterFromDate(Date filterFromDate) {
		this.filterFromDate = filterFromDate;
	}


	public Date getFilterToDate() {
		return filterToDate;
	}


	public void setFilterToDate(Date filterToDate) {
		this.filterToDate = filterToDate;
	}


	public String getAccuracyCallFailureReason() {
		return accuracyCallFailureReason;
	}




	public void setAccuracyCallFailureReason(String accuracyCallFailureReason) {
		this.accuracyCallFailureReason = accuracyCallFailureReason;
	}




	public String getAccuracyCallFailureTime() {
		return accuracyCallFailureTime;
	}




	public void setAccuracyCallFailureTime(String accuracyCallFailureTime) {
		this.accuracyCallFailureTime = accuracyCallFailureTime;
	}




	public String getCompletenessCallFailureReason() {
		return completenessCallFailureReason;
	}




	public void setCompletenessCallFailureReason(String completenessCallFailureReason) {
		this.completenessCallFailureReason = completenessCallFailureReason;
	}




	public String getCompletenessCallFailureTime() {
		return completenessCallFailureTime;
	}




	public void setCompletenessCallFailureTime(String completenessCallFailureTime) {
		this.completenessCallFailureTime = completenessCallFailureTime;
	}




	public String getPrivacyCallFailureReason() {
		return privacyCallFailureReason;
	}




	public void setPrivacyCallFailureReason(String privacyCallFailureReason) {
		this.privacyCallFailureReason = privacyCallFailureReason;
	}




	public String getPrivacyCallFailureTime() {
		return privacyCallFailureTime;
	}




	public void setPrivacyCallFailureTime(String privacyCallFailureTime) {
		this.privacyCallFailureTime = privacyCallFailureTime;
	}




	public String getCustomerSkillsCallFailureReason() {
		return customerSkillsCallFailureReason;
	}




	public void setCustomerSkillsCallFailureReason(String customerSkillsCallFailureReason) {
		this.customerSkillsCallFailureReason = customerSkillsCallFailureReason;
	}




	public String getCustomerSkillsCallFailureTime() {
		return customerSkillsCallFailureTime;
	}




	public void setCustomerSkillsCallFailureTime(String customerSkillsCallFailureTime) {
		this.customerSkillsCallFailureTime = customerSkillsCallFailureTime;
	}


	public String getCallMonitoringDate() {
		return callMonitoringDate;
	}

	public void setCallMonitoringDate(String callMonitoringDate) {
		this.callMonitoringDate = callMonitoringDate;
	}

	public String getLob() {
		return lob;
	}
	
	public void setLob(String lob) {
		this.lob = lob;
	}

	public Integer getCallSubCategoryId() {
		return callSubCategoryId;
	}


	public void setCallSubCategoryId(Integer callSubCategoryId) {
		this.callSubCategoryId = callSubCategoryId;
	}

	public String getScorecardType() {
		return scorecardType;
	}



	public void setScorecardType(String scorecardType) {
		this.scorecardType = scorecardType;
	}




	public String getFailReasonAdditionalComments() {
		return failReasonAdditionalComments;
	}



	public void setFailReasonAdditionalComments(String failReasonAdditionalComments) {
		this.failReasonAdditionalComments = failReasonAdditionalComments;
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



	public Integer getJurId() {
		return jurId;
	}
	
	


	public String getFailReason() {
		return failReason;
	}



	public void setFailReason(String failReason) {
		this.failReason = failReason;
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



	public String getQamFullName() {
		return qamFullName;
	}



	public void setQamFullName(String qamFullName) {
		this.qamFullName = qamFullName;
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