package com.sduran.batch.processor;

import com.sduran.api.web.response.MuseumSodaResponse;
import com.sduran.model.Museum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class MuseumSodaResponseItemProcessor implements ItemProcessor<MuseumSodaResponse, Museum> {

    @Override
    public Museum process(final MuseumSodaResponse museumSodaResponse) {
        final String method = "process";

        Museum museum = new Museum();

        museum.setName(museumSodaResponse.getName());
        museum.setZipCode(museumSodaResponse.getZipCode());
        museum.setNeighborhood(museumSodaResponse.getNeighborhood());
        museum.setCouncilDistrict(museumSodaResponse.getCouncilDistrict());
        museum.setPoliceDistrict(museumSodaResponse.getPoliceDistrict());


        if (museumSodaResponse.getLocationBaseResource() != null) {
            String address =  museumSodaResponse.getLocationBaseResource().getHumanAddress().getAddress();
            if (!StringUtils.isBlank(address)){
                museum.setAddress(address);
            }
        }


        LOG.debug("{}, Converting row:({}) into entity:({})", method, museumSodaResponse, museum);
        return museum;
    }

    private static final Logger LOG = LoggerFactory.getLogger(MuseumSodaResponseItemProcessor.class);
}
