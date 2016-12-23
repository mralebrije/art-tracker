package com.sduran.api.web;

import com.sduran.api.constants.ApiConstants;
import com.sduran.api.service.MuseumService;
import com.sduran.api.service.exception.MuseumNotFoundException;
import com.sduran.api.web.request.MuseumRequest;
import com.sduran.api.web.response.BaseApiFormResponse;
import com.sduran.api.web.response.DistrictsSearchResponse;
import com.sduran.api.web.response.MuseumsSearchResponse;
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

@Api(name = "Museum Services", description = "Methods to manage museums", visibility = ApiVisibility.PUBLIC, group = ApiConstants.MUSEUM_GROUP)
@ApiVersion(since = "1.0")
@RestController
@RequestMapping("/museum")
public class MuseumRestController extends BaseController {

    @Autowired
    private MuseumService museumService;

    @ApiMethod(path = "/museum", verb = ApiVerb.GET, description = "Search all museums", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<MuseumsSearchResponse> getMuseums() {

        MuseumsSearchResponse museumsSearchResponse = new MuseumsSearchResponse();

        museumsSearchResponse.setMuseums(museumService.findAllMuseums());
        museumsSearchResponse.setTotal(museumService.countAllMuseums());

        return new ResponseEntity<MuseumsSearchResponse>(museumsSearchResponse, HttpStatus.OK);
    }

    @ApiMethod(path = "/museum", verb = ApiVerb.POST, description = "Create or Update a Museum record", consumes = {
            MediaType.APPLICATION_JSON_VALUE})
    @ApiErrors(apierrors = {@ApiError(code = "404", description = "Wrong fields")})
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BaseApiFormResponse> updateMuseum(
            @ApiQueryParam(name = "museumRequest", description = "Request that contains museum information", required = true) @ApiBodyObject @Validated @RequestBody MuseumRequest museumRequest,
            BindingResult result) {

        LOG.info("updateMuseum: {}", museumRequest);

        new MuseumValidator().validate(museumRequest, result);
        if (result.hasErrors()) {
            return buildErrors(new BaseApiFormResponse(), result);
        }

        museumService.createOrUpdateMuseum(museumRequest);
        return new ResponseEntity<BaseApiFormResponse>(HttpStatus.OK);
    }

    @ApiMethod(path = "/museum/{id}", verb = ApiVerb.POST, description = "Delete a Museum record")
    @ApiErrors(apierrors = {@ApiError(code = "404", description = "Museum not found")})
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> deleteMuseum(
            @ApiPathParam(name = "id", description = "Museum ID") @PathVariable("id") String id) {

        try {
            museumService.deleteMuseum(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        } catch (MuseumNotFoundException e) {
            LOG.error("Museum was not found with id:{}", id);
            return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiMethod(path = "/museum/district", verb = ApiVerb.GET, description = "Search all council districts", produces = {MediaType.APPLICATION_JSON_VALUE})
    @RequestMapping(value = "/district",method = RequestMethod.GET)
    public ResponseEntity<DistrictsSearchResponse> getCouncilDistricts() {

        DistrictsSearchResponse districtsSearchResponse = new DistrictsSearchResponse();

        districtsSearchResponse.setDistricts(museumService.findAllDistricts());
        districtsSearchResponse.setTotal(museumService.countAllDistricts());

        return new ResponseEntity<DistrictsSearchResponse>(districtsSearchResponse, HttpStatus.OK);
    }


    private static final Logger LOG = LoggerFactory.getLogger(MuseumRestController.class);
}
