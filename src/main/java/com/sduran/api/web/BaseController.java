package com.sduran.api.web;

import com.sduran.api.web.errors.ErrorHelper;
import com.sduran.api.web.response.BaseApiFormResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

public abstract class BaseController {
	
	@Autowired
	protected ErrorHelper errorHelper;
	
	protected<T extends BaseApiFormResponse> ResponseEntity<T> buildErrors(T response, BindingResult errors){
		LOG.warn("Errors = {}", errors.getAllErrors());
		response.setErrors(errorHelper.buildFromErrors(errors.getFieldErrors()));

		return new ResponseEntity<T>(response, HttpStatus.BAD_REQUEST);
	}

	protected<T extends BaseApiFormResponse> ResponseEntity<T> buildUserErrorMessage(T response, String message, HttpStatus status){
		LOG.warn("Message = {}", message);
		response.setMessage(message);

		return new ResponseEntity<T>(response, status);
	}
	
	private static final Logger LOG = LoggerFactory.getLogger(BaseController.class);

}