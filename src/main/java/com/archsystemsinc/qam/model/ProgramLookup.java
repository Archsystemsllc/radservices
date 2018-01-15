package com.archsystemsinc.qam.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the program_lookup database table.
 * 
 */
@Entity
@Table(name="program_lookup")
@NamedQuery(name="ProgramLookup.findAll", query="SELECT p FROM ProgramLookup p")
public class ProgramLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="program_description")
	private String programDescription;

	@Column(name="program_name")
	private String programName;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private String updatedDate;

	public ProgramLookup() {
	}

	
	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
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

	public String getProgramDescription() {
		return this.programDescription;
	}

	public void setProgramDescription(String programDescription) {
		this.programDescription = programDescription;
	}

	public String getProgramName() {
		return this.programName;
	}

	public void setProgramName(String programName) {
		this.programName = programName;
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

}