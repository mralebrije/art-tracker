package com.sduran.api.web.resource;

import com.sduran.api.constants.ApiConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiVersion(since = "1.0")
@ApiObject(name = "LocationResource", description = "Resource object that stores a specific location", group = ApiConstants.RESOURCE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
public class LocationResource extends LocationBaseResource {

    public double latitude;

    public double longitude;
}

