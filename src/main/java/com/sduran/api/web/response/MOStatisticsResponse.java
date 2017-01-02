package com.sduran.api.web.response;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.web.resource.MOStatisticsResource;
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
@ApiObject(name = "MOStatisticsResponse", description = "Response with a list of MOStatisticsResource objects", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class MOStatisticsResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "statistics_zip_code_list", description = "Object list of MOStatisticsResource", required = true)
	private List<MOStatisticsResource> statisticsZipCodeList;

	@ApiObjectField(name = "max_zip_code", description = "Object with that contains information about zip code region with more Museums and Art Organizations", required = true)
	private MOStatisticsResource maxZipCode;

	public MOStatisticsResponse(List<MOStatisticsResource> statisticsZipCodeList,MOStatisticsResource maxZipCode  ) {
		super();
		this.statisticsZipCodeList =  statisticsZipCodeList;
		this.maxZipCode = maxZipCode;

	}

	public MOStatisticsResponse() {
		super();
	}
	
}