package com.rubicon.watermanagement.dto;

import java.time.LocalDateTime;

public class OrderRequest {
	int farmid;
	LocalDateTime dateTime;
	int duration;
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
	

	
	
}
