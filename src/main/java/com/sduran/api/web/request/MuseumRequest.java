package com.sduran.api.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sduran.api.constants.ApiConstants;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.NotEmpty;
import org.jsondoc.core.annotation.ApiObject;
import org.jsondoc.core.annotation.ApiObjectField;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVisibility;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Getter
@Setter
@ToString
@ApiVersion(since = "1.0")
@ApiObject(name = "MuseumRequest", description = "Request object for creation or update of Museum", group = ApiConstants.REQUEST_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
@SuppressWarnings("serial")
public class MuseumRequest implements Serializable {

    @ApiObjectField(name = "id", description = "Museum id", required = false)
    private String id;

    @ApiObjectField(name = "name", description = "Museum name", required = true)
    @NotEmpty
    private String name;

    @ApiObjectField(name = "zip_code", description = "Museum zip Code", required = true)
    @JsonProperty("zip_code")
    @NotEmpty
    private String zipCode;

    @ApiObjectField(name = "neighborhood", description = "Museum neighborhood", required = true)
    @NotEmpty
    private String neighborhood;

    @ApiObjectField(name = "council_district", description = "Museum council district number", required = true)
    @JsonProperty("council_district")
    @NotNull
    private Integer councilDistrict;

    @ApiObjectField(name = "police_district", description = "Museum police district", required = true)
    @JsonProperty("police_district")
    @NotEmpty
    private String policeDistrict;

    @ApiObjectField(name = "address", description = "Museum Street and Number", required = true)
    @NotEmpty
    private String address;


}