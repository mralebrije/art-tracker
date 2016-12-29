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
import java.math.BigDecimal;

@Getter
@Setter
@ToString
@ApiVersion(since = "1.0")
@ApiObject(name = "MuseumResource", description = "Resource object that stores museum information", group = ApiConstants.RESOURCE_OBJECT_GROUP, visibility = ApiVisibility.PUBLIC)
@SuppressWarnings("serial")
public class MuseumResource implements Serializable {

    @ApiObjectField(name = "id", description = "Museum id")
    private Integer id;
    @ApiObjectField(name = "name", description = "Museum name")
    private String name;
    @ApiObjectField(name = "zip_code", description = "Museum zip code")
    @JsonProperty("zip_code")
    private String zipCode;
    @ApiObjectField(name = "neighborhood", description = "Museum neighborhood")
    private String neighborhood;
    @ApiObjectField(name = "council_district", description = "Museum council district number")
    @JsonProperty("council_district")
    private Integer councilDistrict;
    @ApiObjectField(name = "police_district", description = "Museum police district area")
    @JsonProperty("police_district")
    private String policeDistrict;
    @ApiObjectField(name = "address", description = "Museum address")
    @JsonProperty("address")
    private String address;


}
