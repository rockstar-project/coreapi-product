package com.rockstar.product.web;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorResource {

	private String error;
	
	@JsonProperty(value="error_description")
	private String description;
	
	@JsonProperty(value="error_messages")
	private List<String> messages;
	
	public ErrorResource() {
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<String> getMessages() {
		return messages;
	}

	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
	
}
