package com.sduran.api.web;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.service.MOService;
import com.sduran.api.service.OrganizationService;
import com.sduran.api.web.response.MOStatisticsResponse;
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

@Api(name = "Museums and Art Organizations Statistics Services", description = "Methods to manage art organizations and museums statistics", visibility = ApiVisibility.PUBLIC, group = ApiConstants.STATISTICS_GROUP)
@ApiVersion(since = "1.0")
@RestController
@RequestMapping("/mo")
public class MORestController extends BaseController {

    @Autowired
    private MOService moService;

    @ApiMethod(path = "/mo/zip/", verb = ApiVerb.GET, description = "Search statistics about the amount of museums and organizations found for each ZIP CODE", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(value = "/zip/", method = RequestMethod.GET)
    public ResponseEntity<MOStatisticsResponse> getMuseumsAndOrganizationsStatistics() {

        MOStatisticsResponse moStatisticsResponse = new MOStatisticsResponse();

        moStatisticsResponse.setStatisticsZipCodeList(moService.findZipCodeStatistics());

        return new ResponseEntity<MOStatisticsResponse>(moStatisticsResponse, HttpStatus.OK);
    }


    private static final Logger LOG = LoggerFactory.getLogger(MORestController.class);
}
