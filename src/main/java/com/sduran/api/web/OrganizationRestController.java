package com.sduran.api.web;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.service.OrganizationService;
import com.sduran.api.web.response.OrganizationSearchResponse;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiPathParam;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@SuppressWarnings("unused")
@Api(name = "Art Organization Services", description = "Methods to manage art organizations", visibility = ApiVisibility.PUBLIC, group = ApiConstants.ORGANIZATION_GROUP)
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
