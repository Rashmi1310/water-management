package com.rubicon.watermanagement.dto;

import java.time.LocalDateTime;

public class OrderResponse {
        
	    Long requestId;
		String farmid;
		@Override
		public String toString() {
			return "OrderResponse [requestId=" + requestId + ", farmid=" + farmid + ", dateTime=" + dateTime
					+ ", duration=" + duration + ", status=" + status + "]";
		}
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
		
		

