package com.rubicon.watermanagement.exception;

import java.util.Date;
import java.util.List;

public class ErrorDetails {
	
	private Date timestamp;
	private String message;
	private String detailMessage;
	private List<String> errors;
	
	public List<String> getErrors() {
		return errors;
	}

	public void setErrors(List<String> errors) {
		this.errors = errors;
	}

	public ErrorDetails(Date timestamp, String message, String detailMessage) {
		this.timestamp = timestamp;
		this.message = message;
		this.detailMessage = detailMessage;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDetailMessage() {
		return detailMessage;
	}

	public void setDetailMessage(String detailMessage) {
		this.detailMessage = detailMessage;
	}
	

}
