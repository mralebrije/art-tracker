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
@ApiObject(name = "OrganizationResource", description = "Resource object that stores Art Organization information", group = ApiConstants.RESOURCE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
@SuppressWarnings("serial")
public class OrganizationResource implements Serializable {

    @ApiObjectField(name = "organization", description = "Organization name")
    private String organization;
    @ApiObjectField(name = "zip_code", description = "Organization zip code")
    @JsonProperty("zip_code")
    private String zipCode;
    @ApiObjectField(name = "address", description = "Organization address")
    private String address;
    @ApiObjectField(name = "url", description = "Organization website link")
    private String url;
    @ApiObjectField(name = "latitude", description = "Organization latitude position")
    private Double latitude;
    @ApiObjectField(name = "longitude", description = "Organization longitude position")
    private Double longitude;

}
