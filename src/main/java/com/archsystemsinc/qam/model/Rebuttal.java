package com.archsystemsinc.qam.model;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

import javax.persistence.*;

import org.springframework.web.multipart.MultipartFile;


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
	private Integer id;

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
	private Integer csrId;

	@Column(name="date_posted_2")
	private Date datePosted;

	private String description;

	@Column(name="failure_reason_id")
	private Integer failureReasonId;

	@Column(name="mac_reference_id")
	private String macReferenceId;

	@Column(name="rebuttal_qm_log_id")
	private Integer rebuttalQmLogId;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private String updatedDate;

	@Column(name="user_id")
	private Integer userId;

	/*//bi-directional many-to-one association to MacLookup
	@ManyToOne
	@JoinColumn(name="mac_id")
	private MacLookup macLookup;

	//bi-directional one-to-one association to RebuttalQmLog
	@OneToOne(mappedBy="rebuttal")
	private RebuttalQmLog rebuttalQmLog;
	*/
		
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
	
	@Column(name="program_id")
	private Integer programId;
	
	@Column(name="rebuttal_status")
	private String rebuttalStatus;
	
	@Column(name="rebuttal_result")
	private String rebuttalResult;
	
	@Column(name="accuracy_call_failure_reason")
	private String accuracyCallFailureReason;
	
	@Column(name="completeness_call_failure_reason")
	private String completenessCallFailureReason;
	
	@Column(name="privacy_call_failure_reason")
	private String privacyCallFailureReason;
	
	@Column(name="customer_skills_call_failure_reason")
	private String customerSkillsCallFailureReason;
	
	@Column(name="pcc_location_id")
	private Integer pccLocationId;
	
	@Column(name="rebuttal_call_category")
	private String rebuttalCallCategory;
	
	@Column(name="lob")
	private String lob;
	
	@Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="rebuttal_file_attachment")
    private byte[] rebuttalFileAttachment;
	
	@Column(name="file_name")
	private String fileName;
	
	@Column(name="file_description")
	private String fileDescription;
	
	@Column(name="file_type")
	private String fileType;
	
	@Transient
	private String macName;
	
	@Transient
	private String jurisName;
	
	@Transient
	private ArrayList<Integer> jurisIdList;
	
	@Transient
	private MultipartFile rebuttalFileObject;
	

	public Rebuttal() {
	}
	
	

	public MultipartFile getRebuttalFileObject() {
		return rebuttalFileObject;
	}





	public void setRebuttalFileObject(MultipartFile rebuttalFileObject) {
		this.rebuttalFileObject = rebuttalFileObject;
	}





	public String getFileName() {
		return fileName;
	}



	public void setFileName(String fileName) {
		this.fileName = fileName;
	}



	public String getFileDescription() {
		return fileDescription;
	}



	public void setFileDescription(String fileDescription) {
		this.fileDescription = fileDescription;
	}



	public String getFileType() {
		return fileType;
	}



	public void setFileType(String fileType) {
		this.fileType = fileType;
	}



	public byte[] getRebuttalFileAttachment() {
		return rebuttalFileAttachment;
	}



	public void setRebuttalFileAttachment(byte[] rebuttalFileAttachment) {
		this.rebuttalFileAttachment = rebuttalFileAttachment;
	}



	public ArrayList<Integer> getJurisIdList() {
		return jurisIdList;
	}







	public void setJurisIdList(ArrayList<Integer> jurisIdList) {
		this.jurisIdList = jurisIdList;
	}







	public String getMacName() {
		return macName;
	}





	public void setMacName(String macName) {
		this.macName = macName;
	}





	public String getJurisName() {
		return jurisName;
	}





	public void setJurisName(String jurisName) {
		this.jurisName = jurisName;
	}



	public String getLob() {
		return lob;
	}

	public void setLob(String lob) {
		this.lob = lob;
	}



	public String getRebuttalCallCategory() {
		return rebuttalCallCategory;
	}




	public void setRebuttalCallCategory(String rebuttalCallCategory) {
		this.rebuttalCallCategory = rebuttalCallCategory;
	}




	public Integer getPccLocationId() {
		return pccLocationId;
	}


	public void setPccLocationId(Integer pccLocationId) {
		this.pccLocationId = pccLocationId;
	}


	public String getRebuttalResult() {
		return rebuttalResult;
	}

	public void setRebuttalResult(String rebuttalResult) {
		this.rebuttalResult = rebuttalResult;
	}

	public String getAccuracyCallFailureReason() {
		return accuracyCallFailureReason;
	}


	public void setAccuracyCallFailureReason(String accuracyCallFailureReason) {
		this.accuracyCallFailureReason = accuracyCallFailureReason;
	}


	public String getCompletenessCallFailureReason() {
		return completenessCallFailureReason;
	}

	public void setCompletenessCallFailureReason(String completenessCallFailureReason) {
		this.completenessCallFailureReason = completenessCallFailureReason;
	}


	public String getPrivacyCallFailureReason() {
		return privacyCallFailureReason;
	}


	public void setPrivacyCallFailureReason(String privacyCallFailureReason) {
		this.privacyCallFailureReason = privacyCallFailureReason;
	}





	public String getCustomerSkillsCallFailureReason() {
		return customerSkillsCallFailureReason;
	}





	public void setCustomerSkillsCallFailureReason(String customerSkillsCallFailureReason) {
		this.customerSkillsCallFailureReason = customerSkillsCallFailureReason;
	}





	public Integer getProgramId() {
		return programId;
	}



	public void setProgramId(Integer programId) {
		this.programId = programId;
	}



	public Integer getMacId() {
		return macId;
	}
	

	public String getRebuttalStatus() {
		return rebuttalStatus;
	}

	public void setRebuttalStatus(String rebuttalStatus) {
		this.rebuttalStatus = rebuttalStatus;
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



	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
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

	public Integer getCsrId() {
		return this.csrId;
	}

	public void setCsrId(Integer csrId) {
		this.csrId = csrId;
	}

	public Date getDatePosted() {
		return datePosted;
	}



	public void setDatePosted(Date datePosted) {
		this.datePosted = datePosted;
	}



	public String getDescription() {
		return this.description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getFailureReasonId() {
		return this.failureReasonId;
	}

	public void setFailureReasonId(Integer failureReasonId) {
		this.failureReasonId = failureReasonId;
	}

	public String getMacReferenceId() {
		return this.macReferenceId;
	}

	public void setMacReferenceId(String macReferenceId) {
		this.macReferenceId = macReferenceId;
	}

	public Integer getRebuttalQmLogId() {
		return this.rebuttalQmLogId;
	}

	public void setRebuttalQmLogId(Integer rebuttalQmLogId) {
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

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
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