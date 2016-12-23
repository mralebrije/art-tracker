package com.sduran.api.web.errors;

import com.sduran.api.util.MessageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;

import java.util.ArrayList;
import java.util.List;

@Component
public class ErrorHelper {
	
	@Autowired
	MessageHelper messageHelper;
	
	public List<FieldErrorItem> buildFromErrors(List<FieldError> fieldErrors){
		List<FieldErrorItem> response = new ArrayList<FieldErrorItem>();
		for(FieldError error : fieldErrors)
			response.add(new FieldErrorItem(error.getField(), messageHelper.getMessage(error.getCode(), error.getArguments())));
		
		return response;
	}

}
