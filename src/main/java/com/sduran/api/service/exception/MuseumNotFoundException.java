package com.sduran.api.service.exception;

@SuppressWarnings("serial")
public class MuseumNotFoundException extends Exception {

	public MuseumNotFoundException(String message){
		super(message);
	}

}
