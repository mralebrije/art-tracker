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
@ApiObject(name = "ZipCodeStatisticsResource", description = "Resource object that stores statistics information about museums and organizations", group = ApiConstants.RESOURCE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
@SuppressWarnings("serial")
public class ZipCodeStatisticsResource implements Serializable {

    @ApiObjectField(name = "zip_code", description = "Zip code (Postal code)")
    @JsonProperty("zip_code")
    private String zipCode;
    @ApiObjectField(name = "museums_count", description = "Museum count for current zip code")
    @JsonProperty("museums_count")
    private Long museumsCount;
    @ApiObjectField(name = "organizations_count", description = "Art Organizations count for current zip code")
    @JsonProperty("organizations_count")
    private Long organizationsCount;



}
