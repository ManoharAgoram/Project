package com.dvlp.quizapp.model;

public class Responses {
	private int id;
	private String response;

	public Responses(int id, String response) {
		this.id = id;
		this.response = response;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResponse() {
		return response;
	}

	public void setResponse(String response) {
		this.response = response;
	}
}
