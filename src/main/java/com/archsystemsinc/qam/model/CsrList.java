/**
 * 
 *//*
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

*//**
 * @author PrakashTotta
 *
 *//*
@Entity
@Table(name = "csr_listsdummy")
public class CsrList {

	private Long id;
	private String fisrtName;
	private String middleName;
	private String lastName;
	private String location;
	private String level;
	private String jurisdiction;
	private String program;
	private Long userId;
	private Long macLookupId;
	
	
	private String status;
	
	@JsonSerialize(using=DateSerializer.class)
	private Date createdDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "CSR_FIRST_NAME")
	public String getFisrtName() {
		return fisrtName;
	}

	public void setFisrtName(String fisrtName) {
		this.fisrtName = fisrtName;
	}

	@Column(name = "CSR_MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	
	@Column(name = "CSR_LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	
	@Column(name = "CSR_LOCATION")
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "CSR_LEVEL")
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	@Column(name = "JURISDICTION")
	public String getJurisdiction() {
		return jurisdiction;
	}

	public void setJurisdiction(String jurisdiction) {
		this.jurisdiction = jurisdiction;
	}

	@Column(name = "PROGRAM")
	public String getProgram() {
		return program;
	}

	public void setProgram(String program) {
		this.program = program;
	}

	@Column(name = "USER_ID")
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	@Column(name = "MAC_LOOKUP_ID")
	public Long getMacLookupId() {
		return macLookupId;
	}

	public void setMacLookupId(Long macLookupId) {
		this.macLookupId = macLookupId;
	}

	@Column(name = "CSR_STATUS")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "CSR_CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
}
*/