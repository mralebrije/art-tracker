package com.sduran.api.web;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.service.StatisticsService;
import com.sduran.api.web.resource.CouncilStatisticsResource;
import com.sduran.api.web.resource.ZipCodeStatisticsResource;
import com.sduran.api.web.response.CouncilStatisticsResponse;
import com.sduran.api.web.response.ZipCodeStatisticsResponse;
import org.jsondoc.core.annotation.Api;
import org.jsondoc.core.annotation.ApiMethod;
import org.jsondoc.core.annotation.ApiVersion;
import org.jsondoc.core.pojo.ApiVerb;
import org.jsondoc.core.pojo.ApiVisibility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SuppressWarnings("unused")
@Api(name = "Museums and Art Organizations Statistics Services", description = "Methods to manage art organizations and museums statistics", visibility = ApiVisibility.PUBLIC, group = ApiConstants.STATISTICS_GROUP)
@ApiVersion(since = "1.0")
@RestController
@RequestMapping("/statistics")
public class StatisticsRestController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiMethod(path = "/statistics/zip", verb = ApiVerb.GET, description = "Search statistics about the amount of museums and organizations found for each ZIP CODE", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(value= "/zip", method = RequestMethod.GET)
    public ResponseEntity<ZipCodeStatisticsResponse> getZipCodeStatistics() {

        ZipCodeStatisticsResponse zipCodeStatisticsResponse = new ZipCodeStatisticsResponse();

        List<ZipCodeStatisticsResource> list = statisticsService.findZipCodeStatistics();
        zipCodeStatisticsResponse.setZipCodeStatisticsList(list);
        zipCodeStatisticsResponse.setMaxZipCode(statisticsService.findMaxZipCode(list));

        return new ResponseEntity<ZipCodeStatisticsResponse>(zipCodeStatisticsResponse, HttpStatus.OK);
    }

    @ApiMethod(path = "/statistics/council", verb = ApiVerb.GET, description = "Search statistics about the amount of museums found for each COUNCIL DISTRICT", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(value= "/council", method = RequestMethod.GET)
    public ResponseEntity<CouncilStatisticsResponse> getCouncilStatistics() {

        CouncilStatisticsResponse councilStatisticsResponse = new CouncilStatisticsResponse();

        List<CouncilStatisticsResource> list = statisticsService.findCouncilStatistics();
        councilStatisticsResponse.setCouncilStatisticsList(list);
        councilStatisticsResponse.setMaxCouncil(statisticsService.findMaxCouncil(list));

        return new ResponseEntity<CouncilStatisticsResponse>(councilStatisticsResponse, HttpStatus.OK);
    }


    private static final Logger LOG = LoggerFactory.getLogger(StatisticsRestController.class);
}
