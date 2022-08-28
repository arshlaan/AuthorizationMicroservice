package com.cognizant.Authorization.exception;

public class ExceptionResponse {
	
	private String timestamp;
	private String error;
	public String getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}
	public ExceptionResponse(String timestamp, String error, int status, String message) {
		this.timestamp = timestamp;
		this.error = error;
		this.status = status;
		this.message = message;
	}
	public ExceptionResponse() {
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
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
	private int status;
	private String message;
	
}
