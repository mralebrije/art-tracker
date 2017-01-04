package com.sduran.api.web.resource;

import com.sduran.api.constants.ApiConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

@ToString
@Getter
@Setter
@ApiVersion(since = "1.0")
@ApiObject(name = "UrlResource", description = "Resource object that stores a url web page link", group = ApiConstants.RESOURCE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
public class UrlResource {
    public String url;
}

