package com.sduran.api.web.response;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.web.resource.MuseumResource;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

import java.util.List;

@Getter
@Setter
@ToString
@ApiVersion(since = "1.0")
@ApiObject(name = "DistrictsSearchResponse", description = "Response with a list of districts", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class DistrictsSearchResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "districts", description = "Object list of districts", required = true)
	private List<String> districts;
	private long total;

	public DistrictsSearchResponse(List<String> districts, long total) {
		super();
		this.districts = districts;
		this.total = total;
	}

	public DistrictsSearchResponse() {
		super();
	}
	
}