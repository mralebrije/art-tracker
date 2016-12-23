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
@ApiObject(name = "MuseumsSearchResponse", description = "Response with a list of museums", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class MuseumsSearchResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "museums", description = "Object list of MuseumResource", required = true)
	private List<MuseumResource> museums;
	private long total;

	public MuseumsSearchResponse(List<MuseumResource> museums, long total) {
		super();
		this.museums = museums;
		this.total = total;
	}

	public MuseumsSearchResponse() {
		super();
	}
	
}