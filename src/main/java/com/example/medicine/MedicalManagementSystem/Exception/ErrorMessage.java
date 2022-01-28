package com.example.medicine.MedicalManagementSystem.Exception;

import java.util.List;

public class ErrorMessage {

	private String message;
	private List<String> details;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public List<String> getDetails() {
		return details;
	}

	public void setDetails(List<String> details) {
		this.details = details;
	}

	public ErrorMessage(String message, List<String> details) {
		super();
		this.message = message;
		this.details = details;
	}

}