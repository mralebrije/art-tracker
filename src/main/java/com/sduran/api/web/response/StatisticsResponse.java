package com.sduran.api.web.response;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.web.resource.StatisticsResource;
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
@ApiObject(name = "StatisticsResponse", description = "Response with a list of StatisticsResource objects", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class StatisticsResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "statistics_zip_code_list", description = "Object list of StatisticsResource", required = true)
	private List<StatisticsResource> zipCodeStatisticsList;

	@ApiObjectField(name = "max_zip_code", description = "Object that contains information about the zip code region with more Museums and Art Organizations inside", required = true)
	private StatisticsResource maxZipCode;

	public StatisticsResponse(List<StatisticsResource> zipCodeStatisticsList, StatisticsResource maxZipCode  ) {
		super();
		this.zipCodeStatisticsList = zipCodeStatisticsList;
		this.maxZipCode = maxZipCode;

	}

	public StatisticsResponse() {
		super();
	}
	
}