package com.archsystemsinc.qam.model;

import java.io.Serializable;
import javax.persistence.*;


/**
 * The persistent class for the fail_reasons_lookup database table.
 * 
 */
@Entity
@Table(name="fail_reasons_lookup")
@NamedQuery(name="FailReasonsLookup.findAll", query="SELECT f FROM FailReasonsLookup f")
public class FailReasonsLookup implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="fail_reason")
	private String failReason;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private String updatedDate;

	//bi-directional one-to-one association to ScoreCard
	@OneToOne
	@JoinColumn(name="id")
	private ScoreCard scoreCard;

	public FailReasonsLookup() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
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

	public String getFailReason() {
		return this.failReason;
	}

	public void setFailReason(String failReason) {
		this.failReason = failReason;
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

	public ScoreCard getScoreCard() {
		return this.scoreCard;
	}

	public void setScoreCard(ScoreCard scoreCard) {
		this.scoreCard = scoreCard;
	}

}