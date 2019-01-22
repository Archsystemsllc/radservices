/**
 * 
 */
package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

import com.archsystemsinc.qam.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Abdul Nissar S
 *
 */
@Entity
@Table(name = "qam_environment_change_form")
public class QamEnvironmentChangeForm implements Comparable<QamEnvironmentChangeForm>{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "qam_environment_change_form_id")
	private Long qamEnvironmentChangeFormId;
		
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "MAC_LOOKUP_ID")
	private Long macLookupId;
	
	@Column(name = "JURISDICTION_ID")
	private Long jurisdictionId;
	
	@Column(name = "qam_env_form_status")
	private String qamEnvFormstatus;
	
	@Column(name = "type")
	private String fileType;
	
	@Column(name = "document_name")
	private String documentName;
	
	@Column(name = "description")
	private String description;
	
	@Lob @Basic(fetch = FetchType.LAZY)
    @Column(name="document_content", nullable=false)
    private byte[] documentContent;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "CREATED_DATE")
	
	private String  createdDate;
	
	
	@Column(name = "UPDATED_DATE")
	
	private String updateddDate;
	
	@Column(name = "RECORD_STATUS")
	private Long recordStatus;
	
	@Column(name = "FORM_TYPE")
	private String formType;
	
	
	

	public String getFormType() {
		return formType;
	}



	public void setFormType(String formType) {
		this.formType = formType;
	}



	public Long getQamEnvironmentChangeFormId() {
		return qamEnvironmentChangeFormId;
	}



	public void setQamEnvironmentChangeFormId(Long qamEnvironmentChangeFormId) {
		this.qamEnvironmentChangeFormId = qamEnvironmentChangeFormId;
	}





	public Long getUserId() {
		return userId;
	}





	public void setUserId(Long userId) {
		this.userId = userId;
	}





	public Long getMacLookupId() {
		return macLookupId;
	}





	public void setMacLookupId(Long macLookupId) {
		this.macLookupId = macLookupId;
	}





	public Long getJurisdictionId() {
		return jurisdictionId;
	}





	public void setJurisdictionId(Long jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}





	public String getQamEnvFormstatus() {
		return qamEnvFormstatus;
	}





	public void setQamEnvFormstatus(String qamEnvFormstatus) {
		this.qamEnvFormstatus = qamEnvFormstatus;
	}


	public String getFileType() {
		return fileType;
	}



	public void setFileType(String fileType) {
		this.fileType = fileType;
	}



	public String getDocumentName() {
		return documentName;
	}





	public void setDocumentName(String documentName) {
		this.documentName = documentName;
	}





	public String getDescription() {
		return description;
	}





	public void setDescription(String description) {
		this.description = description;
	}





	public byte[] getDocumentContent() {
		return documentContent;
	}





	public void setDocumentContent(byte[] documentContent) {
		this.documentContent = documentContent;
	}





	public String getCreatedBy() {
		return createdBy;
	}





	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}





	public String getUpdatedBy() {
		return updatedBy;
	}





	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}





	





	public String getCreatedDate() {
		return createdDate;
	}



	public void setCreatedDate(String createdDate) {
		this.createdDate = createdDate;
	}



	public String getUpdateddDate() {
		return updateddDate;
	}



	public void setUpdateddDate(String updateddDate) {
		this.updateddDate = updateddDate;
	}



	public Long getRecordStatus() {
		return recordStatus;
	}





	public void setRecordStatus(Long recordStatus) {
		this.recordStatus = recordStatus;
	}





	@Override
	public int compareTo(QamEnvironmentChangeForm o) {
		
		String thisValue = this.macLookupId+"_"+this.jurisdictionId;
		String objectValue = o.macLookupId+"_"+o.jurisdictionId;
		
		//ascending order
		return thisValue.compareTo(objectValue);
	}
}
