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
 * @author Abdul Nissar S
 *
 */
@Entity
@Table(name = "mac_lookup")
public class MacLookup {

	private Long id;
	private String macName;
	private String macDescription;
	private String createdBy;
	private String updatedBy;
		
	@JsonSerialize(using=DateSerializer.class)
	private Date createdDate;
	
	@JsonSerialize(using=DateSerializer.class)
	private Date updateddDate;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name = "MAC_NAME")
	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}
	
	@Column(name = "MAC_DESCRIPTION")
	public String getMacDescription() {
		return macDescription;
	}

	public void setMacDescription(String macDescription) {
		this.macDescription = macDescription;
	}	
	
	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	@Column(name = "CREATED_BY")
	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	@Column(name = "UPDATED_BY")
	public String getUpdatedBy() {
		return updatedBy;
	}

	public void setUpdatedBy(String updatedBy) {
		this.updatedBy = updatedBy;
	}
	
	@Column(name = "UPDATED_DATE")
	public Date getUpdateddDate() {
		return updateddDate;
	}

	public void setUpdateddDate(Date updateddDate) {
		this.updateddDate = updateddDate;
	}	
}
