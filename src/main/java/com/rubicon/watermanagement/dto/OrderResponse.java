package com.rubicon.watermanagement.dto;

import java.time.LocalDateTime;

public class OrderResponse {

	    Long requestId;
		int farmid;
		@Override
		public String toString() {
			return "OrderResponse [requestId=" + requestId + ", farmid=" + farmid + ", dateTime=" + dateTime
					+ ", duration=" + duration + ", status=" + status + "]";
		}
		LocalDateTime dateTime;
		int duration;
		String status;
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
		
		

