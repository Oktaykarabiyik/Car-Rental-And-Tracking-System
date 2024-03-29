package com.project.webapp.responses;

import lombok.Data;

@Data
public class AuthResponse {

	String message;
	Long userId;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
}