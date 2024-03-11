package com.bean;

import lombok.Data;

@Data
public class RespsonseBean <T> {
	
		T data;
		int status;
		String message;
		public T getData() {
			return data;
		}
		public void setData(T data) {
			this.data = data;
		}
		public int getStatus() {
			return status;
		}
		public void setStatus(int status) {
			this.status = status;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		
}
