package com.sduran.api.web.resource;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sduran.api.constants.ApiConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

import java.io.Serializable;

@Getter
@Setter
@ToString
@ApiVersion(since = "1.0")
@ApiObject(name = "CouncilStatisticsResource", description = "Resource object that stores statistics information about museums", group = ApiConstants.RESOURCE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
@SuppressWarnings("serial")
public class CouncilStatisticsResource implements Serializable {

    @ApiObjectField(name = "council_district", description = "Number of Council District")
    @JsonProperty("council_district")
    private Integer councilDistrict;
    @ApiObjectField(name = "museums_count", description = "Museum count for current council district")
    @JsonProperty("museums_count")
    private Long museumsCount;


}
