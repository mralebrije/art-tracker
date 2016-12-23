package com.sduran.api.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.validation.ObjectError;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class MessageHelper {
	
	@Autowired
	@Qualifier("messageSource")
	private MessageSource messageSource;
	
	public String getMessage(ObjectError error){
		String codes[] = error.getCodes();
		String code = error.getCode();
		if(code.equals(MISMATCH_ERROR))
			code = codes[0];
		
		return messageSource.getMessage(code, error.getArguments(), LocaleContextHolder.getLocale());
	}
	
	public String getMessage(String code){
		return messageSource.getMessage(code, new Object[]{}, LocaleContextHolder.getLocale());
	}
	
	public String getMessage(String code, Object []args){
		return messageSource.getMessage(code, args, LocaleContextHolder.getLocale());
	}
	
	public static String buildErrorMessageFromDefault(List<ObjectError> errors){
		Set<String> messages = new HashSet<String>();
		for(ObjectError error : errors)
			messages.add(error.getDefaultMessage());
		
		return StringUtils.arrayToDelimitedString(messages.toArray(), ", ");
	}
	
	private static final String MISMATCH_ERROR = "typeMismatch";

}
