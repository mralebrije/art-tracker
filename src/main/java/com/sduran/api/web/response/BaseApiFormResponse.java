package com.sduran.api.web.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.sduran.api.constants.ApiConstants;
import com.sduran.api.web.errors.FieldErrorItem;
import lombok.Getter;
import lombok.Setter;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ApiVersion(since = "1.0")
@ApiObject(name = "BaseApiFormResponse", description = "Response object that stores errors about fields", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
public class BaseApiFormResponse {

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private String message;

	@JsonInclude(JsonInclude.Include.NON_EMPTY)
	private List<FieldErrorItem> errors = new ArrayList<FieldErrorItem>();

	public List<FieldErrorItem> getErrors() { return errors; }
	public void setErrors(List<FieldErrorItem> errors) { this.errors = errors; }
	
	public void addError(String field, String message){
		errors.add(new FieldErrorItem(field, message));
	}
}