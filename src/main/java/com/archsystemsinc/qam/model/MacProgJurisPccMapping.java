package com.archsystemsinc.qam.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

/**
 * The persistent class for the score_card database table.
 * 
 */
@Entity
@Table(name="mac_pgm_juris_pcc_mapping")
@NamedQuery(name="MacProgJurisPccMapping.findAll", query="SELECT m FROM MacProgJurisPccMapping m")
public class MacProgJurisPccMapping implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;

	@Column(name="mac_id")
	private Integer macId;	
	
	@Column(name="program_id")
	private Integer programId;	

	@Column(name="jurisdiction_id")
	private Integer jurisdictionId;	
	
	@Column(name="pcc_id")
	private Integer pccId;

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

	public Integer getProgramId() {
		return programId;
	}

	public void setProgramId(Integer programId) {
		this.programId = programId;
	}

	public Integer getJurisdictionId() {
		return jurisdictionId;
	}

	public void setJurisdictionId(Integer jurisdictionId) {
		this.jurisdictionId = jurisdictionId;
	}

	public Integer getPccId() {
		return pccId;
	}

	public void setPccId(Integer pccId) {
		this.pccId = pccId;
	}	
}
