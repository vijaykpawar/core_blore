package com.blore.bean;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class OTPLog {

	@Id
	@GeneratedValue
	private Long idOtplog;
	
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="userId")
	private User user;
	
	@Column
	private String otpSent;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date otpSentTime;

	public Long getIdOtplog() {
		return idOtplog;
	}

	public void setIdOtplog(Long idOtplog) {
		this.idOtplog = idOtplog;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getOtpSent() {
		return otpSent;
	}

	public void setOtpSent(String otpSent) {
		this.otpSent = otpSent;
	}

	public String getOptpSent() {
		return otpSent;
	}

	public void setOptpSent(String optpSent) {
		this.otpSent = optpSent;
	}

	public Date getOtpSentTime() {
		return otpSentTime;
	}

	public void setOtpSentTime(Date otpSentTime) {
		this.otpSentTime = otpSentTime;
	}
	
	
}

