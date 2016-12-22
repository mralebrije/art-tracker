package com.sduran.api.web.response;

import com.sduran.api.web.resource.LocationBaseResource;
import lombok.Getter;
import lombok.ToString;
import org.codehaus.jackson.annotate.JsonCreator;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;
import com.sun.jersey.api.client.GenericType;

import java.util.List;


/**
 * This is a java class that represents a Museum.  This will get
 * loaded through the Museum Open Baltiomore dataset.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
@ToString
@Getter
public class MuseumSodaResponse{

    public static final GenericType<List<MuseumSodaResponse>> LIST_TYPE = new GenericType<List<MuseumSodaResponse>>() {
    };

    final String name;
    final String zipCode;
    final String neighborhood;
    final int councilDistrict;
    final String policeDistrict;
    final LocationBaseResource locationBaseResource;

    @JsonCreator
    public MuseumSodaResponse(@JsonProperty("name") String name,
                              @JsonProperty("zipcode") String zipCode,
                              @JsonProperty("neighborhood") String neighborhood,
                              @JsonProperty("councildistrict") int councilDistrict,
                              @JsonProperty("policedistrict") String policeDistrict,
                              @JsonProperty("location_1") LocationBaseResource locationBaseResource) {
        this.name = name;
        this.zipCode = zipCode;
        this.neighborhood = neighborhood;
        this.councilDistrict = councilDistrict;
        this.policeDistrict = policeDistrict;
        this.locationBaseResource = locationBaseResource;
    }

}
