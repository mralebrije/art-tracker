package com.sduran.api.web;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.service.OrganizationService;
import com.sduran.api.service.exception.MuseumNotFoundException;
import com.sduran.api.web.request.MuseumRequest;
import com.sduran.api.web.response.DistrictsSearchResponse;
import com.sduran.api.web.response.MuseumsSearchResponse;
import com.sduran.api.web.response.NewMuseumResponse;
import com.sduran.api.web.response.OrganizationSearchResponse;
import com.sduran.api.web.validator.MuseumValidator;
import org.jsondoc.core.annotation.*;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(name = "Art Organization Services", description = "Methods to manage art organizations", visibility = ApiVisibility.PUBLIC, group = ApiConstants.MUSEUM_GROUP)
@ApiVersion(since = "1.0")
@RestController
@RequestMapping("/organization")
public class OrganizationRestController extends BaseController {

    @Autowired
    private OrganizationService organizationService;

    @ApiMethod(path = "/organization/{zip}", verb = ApiVerb.GET, description = "Search all organizations that belongs to the received zip code", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(value = "/{zip}", method = RequestMethod.GET)
    public ResponseEntity<OrganizationSearchResponse> getMuseums(
            @ApiPathParam(name = "zip", description = "Zip Code") @PathVariable("zip") String zip
    ) {

        OrganizationSearchResponse organizationSearchResponse = new OrganizationSearchResponse();

        organizationSearchResponse.setOrganizations(organizationService.findOrganizationsByZipCode(zip));
        organizationSearchResponse.setTotal(organizationService.countOrganizationsByZipCode(zip));

        return new ResponseEntity<OrganizationSearchResponse>(organizationSearchResponse, HttpStatus.OK);
    }


    private static final Logger LOG = LoggerFactory.getLogger(OrganizationRestController.class);
}
