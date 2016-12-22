package com.sduran.api.web.response;

import com.sduran.api.web.resource.UrlResource;
import com.sun.jersey.api.client.GenericType;
import lombok.Getter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.List;


/**
 * This is a java class that represents Arts Organizations response.  This will get
 * loaded through the Arts Organizations Open Baltiomore dataset.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
public class OrganizationSodaResponse {

    public static final GenericType<List<OrganizationSodaResponse>> LIST_TYPE = new GenericType<List<OrganizationSodaResponse>>() {
    };

    private final String organization;
    private final String address;
    private final String cityState;
    private final String zipCode;
    private final UrlResource url;

    @JsonCreator
    public OrganizationSodaResponse(@JsonProperty("organization") String organization,
                                    @JsonProperty("adress") String address,
                                    @JsonProperty("citystate") String cityState,
                                    @JsonProperty("zipcode") String zipCode,
                                    @JsonProperty("url") UrlResource url) {
        this.organization = organization;
        this.address = address;
        this.cityState = cityState;
        this.zipCode = zipCode;
        this.url = url;
    }

}
