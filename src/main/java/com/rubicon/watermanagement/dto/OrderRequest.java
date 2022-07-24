package com.rubicon.watermanagement.dto;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

public class OrderRequest {
	@Pattern(regexp = "^(?!00000000)[0-9]{8}$", message = "Please enter valid 8 digit numeric farm-id.")
	String farmid;
	//@Pattern(regexp = "^\\d\\d\\d\\d-(0?[1-9]|1[0-2])-(0?[1-9]|[12][0-9]|3[01]) (00|[0-9]|1[0-9]|2[0-3]):([0-9]|[0-5][0-9]):([0-9]|[0-5][0-9])$")
	//@JsonFormat(shape= JsonFormat.Shape.STRING, pattern="yyyy/MM/dd hh:mm:ss")
	LocalDateTime dateTime;
	@Pattern(regexp = "^(?!00)[0-9]{2}$", message = "Enter hours as 2 digit numeric, max allowed hours is 99.")
	String duration;
	public  String getFarmid() {
		return farmid;
	}
	public void setFarmid(String farmid) {
		this.farmid = farmid;
	}
	public LocalDateTime getDateTime() {
		return dateTime;
	}
	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}
	public String getDuration() {
		return duration;
	}
	public void setDuration(String duration) {
		this.duration = duration;
	}
	

	
	
}
