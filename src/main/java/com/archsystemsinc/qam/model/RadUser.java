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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author Prakash T
 *
 */
@Entity
@Table(name = "user")
public class RadUser {

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("RadUser [id=");
		builder.append(id);
		builder.append(", userName=");
		builder.append(userName);
		builder.append(", role=");
		builder.append(role);
		builder.append(", createdBy=");
		builder.append(createdBy);
		builder.append(", updatedBy=");
		builder.append(updatedBy);
		builder.append(", createdDate=");
		builder.append(createdDate);
		builder.append(", updateDate=");
		builder.append(updateDate);
		builder.append(", emailId=");
		builder.append(emailId);
		builder.append(", firstName=");
		builder.append(firstName);
		builder.append(", middleName=");
		builder.append(middleName);
		builder.append(", lastName=");
		builder.append(lastName);
		builder.append(", macId=");
		builder.append(macId);
		builder.append(", status=");
		builder.append(status);
		builder.append(", jurId=");
		builder.append(jurId);
		builder.append(", pccId=");
		builder.append(pccId);
		builder.append("]");
		return builder.toString();
	}

	private Long id;
	private String userName;
	private String password;
	private Role role;
	
	private OrganizationLookup organizationLookup;
	private String createdBy;
	private String updatedBy;
	private Date createdDate;
	private Date updateDate;
	private String emailId;
	
	private String firstName;
	private String middleName;
	private String lastName;
	
	private Long macId;

	private Long status;
	private Long jurId;
	private Long pccId;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	
	@Column(name = "USER_NAME")
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Column(name = "PASSWORD")
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@OneToOne
	@JoinColumn(name="role_id")
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	@OneToOne
	@JoinColumn(name="org_id")
	public OrganizationLookup getOrganizationLookup() {
		return organizationLookup;
	}

	public void setOrganizationLookup(OrganizationLookup organizationLookup) {
		this.organizationLookup = organizationLookup;
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

	@Column(name = "CREATED_DATE")
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	
	@Column(name = "UPDATED_DATE")
	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	@Column(name = "EMAIL_ID")
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	@Column(name = "RECORD_STATUS")
	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
	}

	@Column(name = "FIRST_NAME")
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	@Column(name = "MIDDLE_NAME")
	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	@Column(name = "LAST_NAME")
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	@Column(name = "MAC_ID")
	public Long getMacId() {
		return macId;
	}

	@Column(name = "PCC_ID")
	public Long getPccId() {
		return pccId;
	}
	
	public void setPccId(Long pccId) {
		this.pccId = pccId;
	}

	public void setMacId(Long macId) {
		this.macId = macId;
	}
	
	@Column(name = "JUR_ID")
	public Long getJurId() {
		return jurId;
	}

	public void setJurId(Long jurId) {
		this.jurId = jurId;
	}

	
	
}
