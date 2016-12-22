package com.sduran.api.web.resource;

import lombok.Getter;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonProperty;
import lombok.ToString;

@ToString
@Getter
@Setter
public class UrlResource {

    @JsonProperty("url")
    public String url;

}

