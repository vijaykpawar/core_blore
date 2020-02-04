package com.blore.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;

import org.codehaus.jackson.annotate.JsonIgnore;


/**
 * The Class User.
 */
@Entity
public class User implements Serializable {

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 3318318016946309324L;

	/** The user id. */
	@JsonIgnore
	@Id
	@GeneratedValue
	private Long userId;
	@JsonIgnore
	@Column
	private String sessionToken;
	
	public String getSessionToken() {
		return sessionToken;
	}
	@JsonIgnore
	/** The username. */
	@Column(length = 100, nullable = true, unique = true)
	private String username;
	@JsonIgnore
	/** The password. */
	@Column(length = 255, nullable = true)
	private String password;
	@JsonIgnore
	/** The first name. */
	@Column(length = 50, nullable = true)
	private String firstName;
	@JsonIgnore
	/** The last name. */
	@Column(length = 50, nullable = true)
	private String lastName;

	@Column(length = 100, nullable = false)
	private String displayName;
	@JsonIgnore
	/** The email id. */
	@Column(length = 100, nullable = true, unique = true)
	private String emailId;
	 
	/** The contact no. */
	@Column(length = 40, nullable = false)
	private String contactNo;
	@JsonIgnore
	/** The last login date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDate;
	@JsonIgnore
	/** The is locked. */
	@Basic
	private boolean isLocked;
	@JsonIgnore
	/** The is active. */
	@Basic
	private boolean isActive;
	@JsonIgnore
	/** The created date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	@JsonIgnore
	/** The created by. */
	@Column(length = 10, nullable = true)
	private Long createdBy;
	@JsonIgnore
	/** The updated date. */
	@Temporal(TemporalType.TIMESTAMP)
	private Date updatedDate;
	@JsonIgnore
	/** The updated by. */
	@Column(length = 10, nullable = true)
	private Long updatedBy;
	@JsonIgnore
	/** The version. */
	@Version
	private Integer version;
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="idCountry")
	private Country country;
	
	/**
	 * The Constructor.
	 */
	public User() {
	}

	/**
	 * The Constructor.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param emailId
	 *            the email id
	 * @param isActive
	 *            the is active
	 */
	public User(String username, String password, String emailId, boolean isActive) {
		this.username = username;
		this.password = password;
		this.emailId = emailId;
		this.isActive = isActive;
	}

	

	/**
	 * Gets the user id.
	 *
	 * @return the user id
	 */
	public final Long getUserId() {
		return this.userId;
	}

	/**
	 * Sets the user id.
	 *
	 * @param userId
	 *            the user id
	 */
	public final void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * Gets the username.
	 *
	 * @return the username
	 */
	public final String getUsername() {
		return this.username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username
	 *            the username
	 */
	public final void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public final String getPassword() {
		return this.password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password
	 *            the password
	 */
	public final void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the first name.
	 *
	 * @return the first name
	 */
	public final String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets the first name.
	 *
	 * @param firstName
	 *            the first name
	 */
	public final void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the last name.
	 *
	 * @return the last name
	 */
	public final String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets the last name.
	 *
	 * @param lastName
	 *            the last name
	 */
	public final void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayNmae) {
		this.displayName = displayNmae;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public final String getEmailId() {
		return this.emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId
	 *            the email id
	 */
	public final void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the contact no.
	 *
	 * @return the contact no
	 */
	public final String getContactNo() {
		return this.contactNo;
	}

	/**
	 * Sets the contact no.
	 *
	 * @param contactNo
	 *            the contact no
	 */
	public final void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	
	/**
	 * Gets the last login date.
	 *
	 * @return the last login date
	 */
	public final Date getLastLoginDate() {
		return this.lastLoginDate;
	}

	/**
	 * Sets the last login date.
	 *
	 * @param lastLoginDate
	 *            the last login date
	 */
	public final void setLastLoginDate(Date lastLoginDate) {
		this.lastLoginDate = lastLoginDate;
	}

	/**
	 * Gets the is locked.
	 *
	 * @return the checks if is locked
	 */
	public final boolean getIsLocked() {
		return this.isLocked;
	}

	/**
	 * Sets the is locked.
	 *
	 * @param isLocked
	 *            the checks if is locked
	 */
	public final void setIsLocked(boolean isLocked) {
		this.isLocked = isLocked;
	}

	/**
	 * Gets the is active.
	 *
	 * @return the checks if is active
	 */
	public final boolean getIsActive() {
		return this.isActive;
	}

	/**
	 * Sets the is active.
	 *
	 * @param isActive
	 *            the checks if is active
	 */
	public final void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	/**
	 * Gets the created date.
	 *
	 * @return the created date
	 */
	public final Date getCreatedDate() {
		return this.createdDate;
	}

	/**
	 * Sets the created date.
	 *
	 * @param createdDate
	 *            the created date
	 */
	public final void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	/**
	 * Gets the created by.
	 *
	 * @return the created by
	 */
	public final Long getCreatedBy() {
		return this.createdBy;
	}

	/**
	 * Sets the created by.
	 *
	 * @param createdBy
	 *            the created by
	 */
	public final void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	/**
	 * Gets the updated date.
	 *
	 * @return the updated date
	 */
	public final Date getUpdatedDate() {
		return this.updatedDate;
	}

	/**
	 * Sets the updated date.
	 *
	 * @param updatedDate
	 *            the updated date
	 */
	public final void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

	/**
	 * Gets the updated by.
	 *
	 * @return the updated by
	 */
	public final Long getUpdatedBy() {
		return this.updatedBy;
	}

	/**
	 * Sets the updated by.
	 *
	 * @param updatedBy
	 *            the updated by
	 */
	public final void setUpdatedBy(Long updatedBy) {
		this.updatedBy = updatedBy;
	}

	/**
	 * Gets the version.
	 *
	 * @return the version
	 */
	public final Integer getVersion() {
		return this.version;
	}

	/**
	 * Sets the version.
	 *
	 * @param version
	 *            the version
	 */
	public final void setVersion(Integer version) {
		this.version = version;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", username=" + username
				+ ", password=" + password + ", firstName=" + firstName
				+ ", lastName=" + lastName + ", displayNmae=" + displayName
				+ ", emailId=" + emailId + ", contactNo=" + contactNo
				+ ", lastLoginDate=" + lastLoginDate + ", isLocked=" + isLocked
				+ ", isActive=" + isActive + ", createdDate=" + createdDate
				+ ", createdBy=" + createdBy + ", updatedDate=" + updatedDate
				+ ", updatedBy=" + updatedBy + ", version=" + version + "]";
	}

	public void setSessionToken(String sessionToken) {
		this.sessionToken=sessionToken;
		
	}

}