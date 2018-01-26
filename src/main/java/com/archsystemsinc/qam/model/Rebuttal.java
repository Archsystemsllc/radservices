package com.archsystemsinc.qam.model;

import java.io.File;
import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the rebuttal database table.
 * 
 */
@Entity
@Table(name="rebuttal")
@NamedQuery(name="Rebuttal.findAll", query="SELECT r FROM Rebuttal r")
public class Rebuttal implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="call_date")
	private String callDate;

	@Column(name="call_type")
	private String callType;

	@Column(name="contact_person")
	private String contactPerson;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="csr_id")
	private int csrId;

	@Column(name="date_posted")
	private String datePosted;

	private String description;

	@Column(name="failure_reason_id")
	private int failureReasonId;

	@Column(name="mac_reference_id")
	private String macReferenceId;

	@Column(name="rebuttal_qm_log_id")
	private int rebuttalQmLogId;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private String updatedDate;

	@Column(name="user_id")
	private int userId;

	/*//bi-directional many-to-one association to MacLookup
	@ManyToOne
	@JoinColumn(name="mac_id")
	private MacLookup macLookup;

	//bi-directional one-to-one association to RebuttalQmLog
	@OneToOne(mappedBy="rebuttal")
	private RebuttalQmLog rebuttalQmLog;
	*/
	@Column(name="mac_pcc_name")
	private String macPCCName;
	
	@Column(name="csr_full_name")
	private String csrFullName;
	
	@Column(name="failure_reason")
	private String failureReason;
	
	@Column(name="qam_full_name")
	private String qamFullName;
	
	@Column(name="call_time")
	private String callTime;
	
	@Column(name="call_category")
	private String callCategory;
	
	@Column(name="descriptive_comments")
	private String descriptionComments;
	
	@Column(name="mac_call_ref_number")
	private String macCallReferenceNumber;
	
	@Column(name="mac_id")
	private Integer macId;
	
	@Column(name="juris_id")
	private Integer jurisId;

	public Rebuttal() {
	}
	
	public Integer getMacId() {
		return macId;
	}




	public void setMacId(Integer macId) {
		this.macId = macId;
	}




	public Integer getJurisId() {
		return jurisId;
	}




	public void setJurisId(Integer jurisId) {
		this.jurisId = jurisId;
	}




	public String getMacCallReferenceNumber() {
		return macCallReferenceNumber;
	}




	public void setMacCallReferenceNumber(String macCallReferenceNumber) {
		this.macCallReferenceNumber = macCallReferenceNumber;
	}




	public String getMacPCCName() {
		return macPCCName;
	}




	public void setMacPCCName(String macPCCName) {
		this.macPCCName = macPCCName;
	}




	public String getCsrFullName() {
		return csrFullName;
	}



	public void setCsrFullName(String csrFullName) {
		this.csrFullName = csrFullName;
	}



	public String getFailureReason() {
		return failureReason;
	}



	public void setFailureReason(String failureReason) {
		this.failureReason = failureReason;
	}



	public String getQamFullName() {
		return qamFullName;
	}



	public void setQamFullName(String qamFullName) {
		this.qamFullName = qamFullName;
	}



	public String getCallTime() {
		return callTime;
	}



	public void setCallTime(String callTime) {
		this.callTime = callTime;
	}
	



	public String getCallCategory() {
		return callCategory;
	}



	public void setCallCategory(String callCategory) {
		this.callCategory = callCategory;
	}



	public String getDescriptionComments() {
		return descriptionComments;
	}



	public void setDescriptionComments(String descriptionComments) {
		this.descriptionComments = descriptionComments;
	}



	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCallDate() {
		return this.callDate;
	}

	public void setCallDate(String callDate) {
		this.callDate = callDate;
	}

	public String getCallType() {
		return this.callType;
	}

	public void setCallType(String callType) {
		this.callType = callType;
	}

	public String getContactPerson() {
		return this.contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public String getCreatedBy() {
		return this.createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getCreatedDate() {
		return this.createdDate;
	}

	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}

	public int getCsrId() {
		return this.csrId;
	}

	public void setCsrId(int csrId) {
		this.csrId = csrId;
	}

	public String getDatePosted() {
		return this.datePosted;
	}

	public void setDatePosted(String datePosted) {
		this.datePosted = datePosted;
	}

	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFailureReasonId() {
		return this.failureReasonId;
	}

	public void setFailureReasonId(int failureReasonId) {
		this.failureReasonId = failureReasonId;
	}

	public String getMacReferenceId() {
		return this.macReferenceId;
	}

	public void setMacReferenceId(String macReferenceId) {
		this.macReferenceId = macReferenceId;
	}

	public int getRebuttalQmLogId() {
		return this.rebuttalQmLogId;
	}

	public void setRebuttalQmLogId(int rebuttalQmLogId) {
		this.rebuttalQmLogId = rebuttalQmLogId;
	}

	public String getUpdatedBy() {
		return this.updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}

	public String getUpdatedDate() {
		return this.updatedDate;
	}

	public void setUpdatedDate(String updatedDate) {
		this.updatedDate = updatedDate;
	}

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	/*public MacLookup getMacLookup() {
		return this.macLookup;
	}

	public void setMacLookup(MacLookup macLookup) {
		this.macLookup = macLookup;
	}

	public RebuttalQmLog getRebuttalQmLog() {
		return this.rebuttalQmLog;
	}

	public void setRebuttalQmLog(RebuttalQmLog rebuttalQmLog) {
		this.rebuttalQmLog = rebuttalQmLog;
	}*/

}