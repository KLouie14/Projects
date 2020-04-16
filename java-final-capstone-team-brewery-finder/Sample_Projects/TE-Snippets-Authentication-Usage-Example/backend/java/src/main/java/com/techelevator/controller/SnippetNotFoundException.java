package com.techelevator.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code=HttpStatus.NOT_FOUND)
public class SnippetNotFoundException extends Exception {

	private static final long serialVersionUID = 1227422333792346806L;
	
	private long id;
	
	public SnippetNotFoundException(Long id, String message) {
		super(message);
		setId(id);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}
	
	

}
