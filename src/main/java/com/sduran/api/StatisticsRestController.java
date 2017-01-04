package com.sduran.api;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.service.StatisticsService;
import com.sduran.api.web.BaseController;
import com.sduran.api.web.resource.StatisticsResource;
import com.sduran.api.web.response.StatisticsResponse;
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

@Api(name = "Museums and Art Organizations Statistics Services", description = "Methods to manage art organizations and museums statistics", visibility = ApiVisibility.PUBLIC, group = ApiConstants.STATISTICS_GROUP)
@ApiVersion(since = "1.0")
@RestController
@RequestMapping("/statistics")
public class StatisticsRestController extends BaseController {

    @Autowired
    private StatisticsService statisticsService;

    @ApiMethod(path = "/statistics/zip", verb = ApiVerb.GET, description = "Search statistics about the amount of museums and organizations found for each ZIP CODE", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(value= "/zip", method = RequestMethod.GET)
    public ResponseEntity<StatisticsResponse> getMuseumsAndOrganizationsStatistics() {

        StatisticsResponse statisticsResponse = new StatisticsResponse();

        List<StatisticsResource> list = statisticsService.findZipCodeStatistics();
        statisticsResponse.setZipCodeStatisticsList(list);
        statisticsResponse.setMaxZipCode(statisticsService.findMaxZipCode(list));

        return new ResponseEntity<StatisticsResponse>(statisticsResponse, HttpStatus.OK);
    }


    private static final Logger LOG = LoggerFactory.getLogger(StatisticsRestController.class);
}
