/**
 * 
 */
package com.archsystemsinc.qam.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Convert;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.archsystemsinc.qam.utils.DateSerializer;
import com.archsystemsinc.qam.utils.StringToDateConverter;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author Prakash T
 *
 */
@Entity
@Table(name = "csr_lists")
public class CsrLists implements Comparable<CsrLists>{
	
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "CSR_FIRST_NAME")
	private String firstName;
	
	@Column(name = "CSR_MIDDLE_NAME")
	private String middleName;
	
	@Column(name = "CSR_LAST_NAME")
	private String lastName;
	
	@Column(name = "CSR_LOCATION")
	private String location;
	
	@Column(name = "CSR_LEVEL")
	private String level;
	
	@Column(name = "JURISDICTION")
	private String jurisdiction;
	
	@Column(name = "PROGRAM")
	private String program;
	
	@Column(name = "USER_ID")
	private Long userId;
	
	@Column(name = "MAC_LOOKUP_ID")
	private Long macLookupId;
	
	@Column(name = "CSR_STATUS")
	private String status;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "RECORD_STATUS")
	private Long recordStatus;
	
	
	@Column(name = "CREATED_DATE")
	@JsonSerialize(using=DateSerializer.class)
	private Date createdDate;
	
	
	@Column(name = "UPDATED_DATE")
	@JsonSerialize(using=DateSerializer.class)
	private Date updateddDate;
	
	@Transient
	@Convert(converter = StringToDateConverter.class)
	private Date createdDateYearMonth;
	
	@Transient
	private String macName;
	
	@Transient
	private String searchStringLiteral;
	
	
	
	public Date getCreatedDateYearMonth() {
		return createdDateYearMonth;
	}

	public void setCreatedDateYearMonth(Date createdDateYearMonth) {
		this.createdDateYearMonth = createdDateYearMonth;
	}

	public String getSearchStringLiteral() {
		return searchStringLiteral;
	}

	public void setSearchStringLiteral(String searchStringLiteral) {
		this.searchStringLiteral = searchStringLiteral;
	}

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	
	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	
	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
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

	
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
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
	
	
	public Long getRecordStatus() {
		return recordStatus;
	}

	public void setRecordStatus(Long recordStatus) {
		this.recordStatus = recordStatus;
	}
	
	
	public Date getUpdateddDate() {
		return updateddDate;
	}

	public void setUpdateddDate(Date updateddDate) {
		this.updateddDate = updateddDate;
	}

	@Override
	public int compareTo(CsrLists o) {
		
		String thisValue = this.macLookupId+"_"+this.jurisdiction+"_"+this.lastName;
		String objectValue = o.macLookupId+"_"+o.jurisdiction+"_"+o.lastName;
		
		//ascending order
		return thisValue.compareTo(objectValue);
	}
}
