/*package com.archsystemsinc.qam.model;

import java.io.Serializable;
import javax.persistence.*;


*//**
 * The persistent class for the rebuttal_qm_log database table.
 * 
 *//*
@Entity
@Table(name="rebuttal_qm_log")
@NamedQuery(name="RebuttalQmLog.findAll", query="SELECT r FROM RebuttalQmLog r")
public class RebuttalQmLog implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;

	@Column(name="created_by")
	private String createdBy;

	@Column(name="created_date")
	private String createdDate;

	@Column(name="rebuttal_calibration_date")
	private String rebuttalCalibrationDate;

	@Column(name="rebuttal_id")
	private int rebuttalId;

	@Column(name="rebuttal_status")
	private String rebuttalStatus;

	@Column(name="updated_by")
	private String updatedBy;

	@Column(name="updated_date")
	private String updatedDate;

	@Column(name="user_id")
	private int userId;

	//bi-directional one-to-one association to Rebuttal
	@OneToOne
	@JoinColumn(name="id")
	private Rebuttal rebuttal;

	public RebuttalQmLog() {
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

	public String getRebuttalCalibrationDate() {
		return this.rebuttalCalibrationDate;
	}

	public void setRebuttalCalibrationDate(String rebuttalCalibrationDate) {
		this.rebuttalCalibrationDate = rebuttalCalibrationDate;
	}

	public int getRebuttalId() {
		return this.rebuttalId;
	}

	public void setRebuttalId(int rebuttalId) {
		this.rebuttalId = rebuttalId;
	}

	public String getRebuttalStatus() {
		return this.rebuttalStatus;
	}

	public void setRebuttalStatus(String rebuttalStatus) {
		this.rebuttalStatus = rebuttalStatus;
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

	public int getUserId() {
		return this.userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Rebuttal getRebuttal() {
		return this.rebuttal;
	}

	public void setRebuttal(Rebuttal rebuttal) {
		this.rebuttal = rebuttal;
	}

}*/