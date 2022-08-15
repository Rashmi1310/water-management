package com.rubicon.watermanagement.dto;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import java.time.LocalDateTime;
import java.util.Objects;

public class OrderResponse {
        
	    Long requestId;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof OrderResponse)) return false;
		OrderResponse that = (OrderResponse) o;
		return Objects.equals(getRequestId(), that.getRequestId()) && Objects.equals(getFarmid(), that.getFarmid()) && Objects.equals(getDateTime(), that.getDateTime()) && Objects.equals(getDuration(), that.getDuration()) && Objects.equals(getStatus(), that.getStatus());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getRequestId(), getFarmid(), getDateTime(), getDuration(), getStatus());
	}

	String farmid;
		@Override
		public String toString() {
			return "OrderResponse [requestId=" + requestId + ", farmid=" + farmid + ", dateTime=" + dateTime
					+ ", duration=" + duration + ", status=" + status + "]";
		}
	@JsonSerialize(using = LocalDateTimeSerializer.class)
	@JsonDeserialize(using = LocalDateTimeDeserializer.class)
		LocalDateTime dateTime;
	    String duration;
		String status;
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
		
		

