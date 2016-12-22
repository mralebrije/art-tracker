package com.sduran.api.web.resource;

import com.sduran.api.constants.ApiConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

@ToString
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
@ApiVersion(since = "1.0")
@ApiObject(name = "AddressResource", description = "Resource object that stores a location address", group = ApiConstants.RESOURCE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
@SuppressWarnings("serial")
public class AddressResource {
    public String address;
}

