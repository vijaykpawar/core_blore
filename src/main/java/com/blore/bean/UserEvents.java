package com.blore.bean;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class UserEvents {

	@Id
	@GeneratedValue
	public Long idUserEvents;
	@ManyToOne
	public User user;
	@ManyToOne
	public Event event;
	
	@Column
	public String status;
	public Long getIdUserEvents() {
		return idUserEvents;
	}
	public void setIdUserEvents(Long idUserEvents) {
		this.idUserEvents = idUserEvents;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public Event getEvent() {
		return event;
	}
	public void setEvent(Event event) {
		this.event = event;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
