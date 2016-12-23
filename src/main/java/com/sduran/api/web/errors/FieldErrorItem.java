package com.sduran.api.web.errors;

public class FieldErrorItem {
	
	private String field;
	private String message;
	
	public FieldErrorItem(String field, String message){
		this.field = field;
		this.message = message;
	}

	public String getField() { return field; }
	public void setField(String field) { this.field = field; }

	public String getMessage() { return message; }
	public void setMessage(String message) { this.message = message; }

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("FieldErrorItem [field=");
		builder.append(field);
		builder.append(", message=");
		builder.append(message);
		builder.append("]");
		return builder.toString();
	}
	
}
