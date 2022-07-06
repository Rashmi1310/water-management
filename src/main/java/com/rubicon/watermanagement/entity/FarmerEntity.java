package com.rubicon.watermanagement.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="farmer")
public class FarmerEntity {
	
	@Column
	@GeneratedValue
	@Id
	private Long requestId;
	@Column
	private int farmid;
	@Column
	private LocalDateTime dateTime;
	@Column
	private int duration;
	@Column
	private String status;
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public int getFarmid() {
		return farmid;
	}
	public void setFarmid(int farmid) {
		this.farmid = farmid;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
