/**
 * 
 */
package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.archsystemsinc.qam.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author PrakashTotta
 *
 */
@Entity
@Table(name = "csr_log_table")
public class CsrLog {

	private Long id;
	private Long userId;

	@JsonSerialize(using=DateSerializer.class)
	private Date createdDate;
	private Long complianceStatus;
	private Long uploadStatus;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "UPLOADED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "COMPLIANCE_STATUS")
	public Long getComplianceStatus() {
		return complianceStatus;
	}

	public void setComplianceStatus(Long complianceStatus) {
		this.complianceStatus = complianceStatus;
	}

	@Column(name = "UPLOAD_SUCCESS_STATUS")
	public Long getUploadStatus() {
		return uploadStatus;
	}

	public void setUploadStatus(Long uploadStatus) {
		this.uploadStatus = uploadStatus;
	}
	
}
