package com.cognizant.Authorization.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

public class AuthResponse {
	
	private String token;
	private long expiresIn;
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	public long getExpiresIn() {
		return expiresIn;
	}
	public AuthResponse(String token, long expiresIn) {
		this.token = token;
		this.expiresIn = expiresIn;
	}
	public AuthResponse() {
	}
	public void setExpiresIn(long expiresIn) {
		this.expiresIn = expiresIn;
	}
	
}
