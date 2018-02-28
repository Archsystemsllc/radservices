/**
 * 
 */
package com.archsystemsinc.qam.model;

import java.util.ArrayList;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

/**
 * @author PrakashTotta
 *
 */
@Entity
@Table(name = "csr_log_table")
public class CsrLog {

	private Long id;
	private Long userId;

	private Date createdDate;
	private Long complianceStatus;
	private Long uploadStatus;
	
	private Integer macId;
	private String jurisdiction;
	
	
	private ArrayList<String> jurisdictionNameList;
	
	private ArrayList<Integer> macIdList;
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Transient	
	public ArrayList<Integer> getMacIdList() {
		return macIdList;
	}
	
	@Transient
	public void setMacIdList(ArrayList<Integer> macIdList) {
		this.macIdList = macIdList;
	}

	
	@Transient
	public ArrayList<String> getJurisdictionNameList() {
		return jurisdictionNameList;
	}
	
	@Transient
	public void setJurisdictionNameList(ArrayList<String> jurisdictionNameList) {
		this.jurisdictionNameList = jurisdictionNameList;
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
	
	@Column(name = "MAC_ID")
	public Integer getMacId() {
		return macId;
	}

	public void setMacId(Integer macId) {
		this.macId = macId;
	}
	
	@Column(name = "JURISDICTION")
	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}
}
