package com.sduran.api.web.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

@ToString
@Getter
@Setter
public class AddressResource {

    @JsonProperty("address")
    public String address;

    @JsonProperty("city")
    public String city;

    @JsonProperty("state")
    public String state;

    @JsonProperty("zip")
    public String zip;
}

