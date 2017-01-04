package com.sduran.api.web.response;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.web.resource.CouncilStatisticsResource;
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
@ApiObject(name = "CouncilStatisticsResponse", description = "Response with a list of CouncilStatisticsResource objects", group = ApiConstants.RESPONSE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)

public class CouncilStatisticsResponse extends BaseApiFormResponse {

	@ApiObjectField(name = "council_statistics_list", description = "Object list of CouncilStatisticsResource", required = true)
	private List<CouncilStatisticsResource> councilStatisticsList;

	@ApiObjectField(name = "max_council", description = "Object that contains information about the council district with more Museums inside", required = true)
	private CouncilStatisticsResource maxCouncil;

	public CouncilStatisticsResponse(List<CouncilStatisticsResource> councilStatisticsList, CouncilStatisticsResource maxCouncil  ) {
		super();
		this.councilStatisticsList = councilStatisticsList;
		this.maxCouncil = maxCouncil;

	}

	public CouncilStatisticsResponse() {
		super();
	}
	
}