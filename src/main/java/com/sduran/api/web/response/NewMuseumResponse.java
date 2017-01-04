package com.sduran.api.web.response;

import com.sduran.api.constants.ApiConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

@Getter
@Setter
@ToString
@ApiVersion(since = "1.0")
@ApiObject(name = "NewMuseumResponse", description = "Response with id of added museum", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class NewMuseumResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "id", description = "Id of added museum", required = true)
	private Integer id;

	public NewMuseumResponse(Integer id) {
		super();
		this.id = id;
	}

	public NewMuseumResponse() {
		super();
	}
	
}