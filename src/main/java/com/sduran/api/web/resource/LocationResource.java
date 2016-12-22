package com.sduran.api.web.resource;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonProperty;

@ToString
@Getter
@Setter
public class LocationResource {

    @JsonProperty("human_address")
    public AddressResource humanAddress;

    @JsonProperty("needs_recording")
    public Boolean needsRecording;
}

