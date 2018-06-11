package com.archsystemsinc.qam.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import com.archsystemsinc.qam.utils.DateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;


/**
 * The persistent class for the score_card database table.
 * 
 */
@Entity
@Table(name="mac_assignment_table")

public class MacAssignmentObject implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	@Column(name="mac_name")
	private String macName;
	
	@Column(name="jurisdiction_name")
	private String jurisdictionName;
	
	@Column(name="program_name")
	private String programName;
	
	
	
	@Column(name="assigned_calls")
	private String assignedCalls;
	
	@Column(name="assigned_quality_monitor")
	private String assignedQualityMonitor;
	
	@Column(name="assigned_month_year")
	private String assignedMonthYear;
	
	@Column(name="created_date")
	private Date createdDate;
	
	@Column(name="updated_date")
	private Date updatedDate;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="monthly_saved_final_date")
	private Date monthlySavedFinalDate;
	
	@Column(name="default_calls")
	private String plannedCalls;
	
	@Column(name="created_method")
	private String createdMethod;
	
	@Column(name="mac_id")
	private Integer macId;
	
	@Column(name="jurisdiction_id")
	private Integer jurisdictionId;
	
	@Column(name="program_id")
	private Integer programId;
	
	public MacAssignmentObject() {
	}

	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}


	public Integer getMacId() {
		return macId;
	}



	public void setMacId(Integer macId) {
		this.macId = macId;
	}



	public Integer getJurisdictionId() {
		return jurisdictionId;
	}



	public void setJurisdictionId(Integer jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}



	public Integer getProgramId() {
		return programId;
	}



	public void setProgramId(Integer programId) {
		this.programId = programId;
	}



	public String getAssignedMonthYear() {
		return assignedMonthYear;
	}

	public void setAssignedMonthYear(String assignedMonthYear) {
		this.assignedMonthYear = assignedMonthYear;
	}

	

	public String getMacName() {
		return macName;
	}

	public void setMacName(String macName) {
		this.macName = macName;
	}

	public String getJurisdictionName() {
		return jurisdictionName;
	}

	public void setJurisdictionName(String jurisdictionName) {
		this.jurisdictionName = jurisdictionName;
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
	}

	
	

	public String getPlannedCalls() {
		return plannedCalls;
	}



	public void setPlannedCalls(String plannedCalls) {
		this.plannedCalls = plannedCalls;
	}



	public String getCreatedMethod() {
		return createdMethod;
	}



	public void setCreatedMethod(String createdMethod) {
		this.createdMethod = createdMethod;
	}



	

	public String getAssignedCalls() {
		return assignedCalls;
	}



	public void setAssignedCalls(String assignedCalls) {
		this.assignedCalls = assignedCalls;
	}



	public String getAssignedQualityMonitor() {
		return assignedQualityMonitor;
	}

	public void setAssignedQualityMonitor(String assignedQualityMonitor) {
		this.assignedQualityMonitor = assignedQualityMonitor;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
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

	public Date getMonthlySavedFinalDate() {
		return monthlySavedFinalDate;
	}

	public void setMonthlySavedFinalDate(Date monthlySavedFinalDate) {
		this.monthlySavedFinalDate = monthlySavedFinalDate;
	}
	
	

}