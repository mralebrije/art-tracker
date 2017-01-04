package com.sduran.api.web.response;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.web.resource.ZipCodeStatisticsResource;
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
@ApiObject(name = "ZipCodeStatisticsResponse", description = "Response with a list of ZipCodeStatisticsResource objects", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class ZipCodeStatisticsResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "zip_code_statistics_list", description = "Object list of ZipCodeStatisticsResource", required = true)
	private List<ZipCodeStatisticsResource> zipCodeStatisticsList;

	@ApiObjectField(name = "max_zip_code", description = "Object that contains information about the zip code region with more Museums and Art Organizations inside", required = true)
	private ZipCodeStatisticsResource maxZipCode;

	public ZipCodeStatisticsResponse(List<ZipCodeStatisticsResource> zipCodeStatisticsList, ZipCodeStatisticsResource maxZipCode  ) {
		super();
		this.zipCodeStatisticsList = zipCodeStatisticsList;
		this.maxZipCode = maxZipCode;

	}

	public ZipCodeStatisticsResponse() {
		super();
	}
	
}