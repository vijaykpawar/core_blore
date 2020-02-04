package com.blore.bean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.codehaus.jackson.annotate.JsonIgnore;



@Entity
public class Event {
	
	@Id
	@GeneratedValue
	private Long eventId;
	
	@Column
	private String eventName;
	
	@Column
	private Double latitude;
	
	@Column
	private Double longitude;
	@JsonIgnore
	@Column
	private Long createdBy;
	
	
	@Column
	private int duaration;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Date createdDate;
	
	@Column
	private int eventType;
	
	
	@OneToMany(mappedBy = "event", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	private List<UserEvents> invities = new ArrayList<UserEvents>();


	public Long getEventId() {
		return eventId;
	}

	public List<String> getInvities() {
		List<String> l=new ArrayList<String>();
		if(invities!=null){
			for(UserEvents con:invities){
				l.add(con.getUser().getContactNo());
			}
		}
		return l;
	}




	public int getEventType() {
		return eventType;
	}

	public void setEventType(int eventType) {
		this.eventType = eventType;
	}

	public void setInvities(List<UserEvents> invities) {
		this.invities = invities;
	}

	public void setEventId(Long eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public Double getLatitude() {
		return latitude;
	}

	public void setLatitude(Double latitude) {
		this.latitude = latitude;
	}

	public Long getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(Long createdBy) {
		this.createdBy = createdBy;
	}

	public Double getLongitude() {
		return longitude;
	}

	public void setLongitude(Double longitude) {
		this.longitude = longitude;
	}

	public int getDuaration() {
		return duaration;
	}
	
	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setDuaration(int duaration) {
		this.duaration = duaration;
	}
	
}
