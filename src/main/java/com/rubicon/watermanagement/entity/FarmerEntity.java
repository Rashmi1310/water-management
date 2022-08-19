package com.rubicon.watermanagement.entity;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="farmer")
public class FarmerEntity {
	
	@Override
	public String toString() {
		return "FarmerEntity [requestId=" + requestId + ", farmid=" + farmid + ", dateTime=" + dateTime + ", duration="
				+ duration + ", status=" + status + "]";
	}
	@Column
	@GeneratedValue
	@Id
	private Long requestId;
	@Column
	private String farmid;

	@Column
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
	private LocalDateTime dateTime;
	@Column
	private String duration;
	@Column
	private String status;
	public Long getRequestId() {
		return requestId;
	}
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}
	public String getFarmid() {
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
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
