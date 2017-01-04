package com.sduran.api.web.response;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.web.resource.OrganizationResource;
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
@ApiObject(name = "OrganizationSearchResponse", description = "Response with a list of art organizations", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class OrganizationSearchResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "organizations", description = "Object list of OrganizationResource", required = true)
	private List<OrganizationResource> organizations;
	private long total;

	public OrganizationSearchResponse(List<OrganizationResource> organizations, long total) {
		super();
		this.organizations = organizations;
		this.total = total;
	}

	public OrganizationSearchResponse() {
		super();
	}
	
}