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
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "USER_NAME")
	private String userName;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@OneToOne
	@JoinColumn(name="role_id")
	private Role role;
	
	
	@OneToOne
	@JoinColumn(name="org_id")
	private OrganizationLookup organizationLookup;
	
	@Column(name = "CREATED_BY")
	private String createdBy;
	
	@Column(name = "UPDATED_BY")
	private String updatedBy;
	
	@Column(name = "CREATED_DATE")
	private Date createdDate;
	
	@Column(name = "UPDATED_DATE")
	private Date updateDate;
	
	@Column(name = "EMAIL_ID")
	private String emailId;
	
	
	@Column(name = "FIRST_NAME")
	private String firstName;
	
	@Column(name = "MIDDLE_NAME")
	private String middleName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "MAC_ID")
	private Long macId;
	
	
	@Column(name = "RECORD_STATUS")
	private Long status;
	
	@Column(name = "JUR_ID")
	private String jurId;
	
	@Column(name = "PCC_ID")
	private Long pccId;
	
	@Column(name = "last_loggedin_date")
	private Date lastLoggedinDate;
	
	@Transient
	private ArrayList<String> jurIdList;
	
	@Transient
	private String roleString;
	
	@Transient
	private String passwordConfirm;
	
	@Transient
	private String passwordFromdb;
	
	/*@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "ENABLED")
	private Boolean enabled;	
	
	@Column(name = "expiry_date")
	private Boolean expiryDate;*/
	
	
	/*public Boolean getExpiryDate() {
		return expiryDate;
	}

	public void setExpiryDate(Boolean expiryDate) {
		this.expiryDate = expiryDate;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}
*/
	public String getPasswordFromdb() {
		return passwordFromdb;
	}

	public void setPasswordFromdb(String passwordFromdb) {
		this.passwordFromdb = passwordFromdb;
	}

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String getRoleString() {
		return roleString;
	}

	public void setRoleString(String roleString) {
		this.roleString = roleString;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	
	public ArrayList<String> getJurIdList() {
		return jurIdList;
	}

	public void setJurIdList(ArrayList<String> jurIdList) {
		this.jurIdList = jurIdList;
	}

	public Date getLastLoggedinDate() {
		return lastLoggedinDate;
	}

	public void setLastLoggedinDate(Date lastLoggedinDate) {
		this.lastLoggedinDate = lastLoggedinDate;
	}

	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	
	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
	public OrganizationLookup getOrganizationLookup() {
		return organizationLookup;
	}

	public void setOrganizationLookup(OrganizationLookup organizationLookup) {
		this.organizationLookup = organizationLookup;
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


	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}
	
	
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}


	public Long getStatus() {
		return status;
	}

	public void setStatus(Long status) {
		this.status = status;
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


	public Long getMacId() {
		return macId;
	}

	
	public Long getPccId() {
		return pccId;
	}
	
	public void setPccId(Long pccId) {
		this.pccId = pccId;
	}

	public void setMacId(Long macId) {
		this.macId = macId;
	}
	

	public String getJurId() {
		return jurId;
	}

	public void setJurId(String jurId) {
		this.jurId = jurId;
	}
}