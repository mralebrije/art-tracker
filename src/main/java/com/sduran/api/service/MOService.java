package com.sduran.api.service;

import com.sduran.api.repository.MuseumRepository;
import com.sduran.api.repository.OrganizationRepository;
import com.sduran.api.web.resource.MOStatisticsResource;
import com.sduran.api.web.resource.OrganizationResource;
import com.sduran.api.web.response.MOStatisticsResponse;
import com.sduran.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MOService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private MuseumRepository museumRepository;

    @Transactional(readOnly = true)
    public List<MOStatisticsResource> findZipCodeStatistics() {

        final String method = "findZipCodeStatistics";

        LOG.info("{}, Searching for statistics using zip code", method);

        List<String> museumZipCodes = museumRepository.findDistinctZipCodes();
        List<String> organizationsZipCodes = organizationRepository.findDistinctZipCodes();

        List<String> moZipCodes = mergeMOZipCodes(museumZipCodes, organizationsZipCodes);

        List<MOStatisticsResource> statisticsList = new ArrayList<>();

        for (String zipCode : moZipCodes){
            MOStatisticsResource moStatisticsResource = new MOStatisticsResource();
            populateMOStatistics(zipCode, moStatisticsResource);
            statisticsList.add(moStatisticsResource);
        }
        return statisticsList;
    }

    private void populateMOStatistics(String zipCode, MOStatisticsResource moStatisticsResource) {
        moStatisticsResource.setZipCode(zipCode);
        moStatisticsResource.setMuseumsCount(museumRepository.countByZipCode(zipCode));
        moStatisticsResource.setOrganizationsCount(organizationRepository.countByZipCode(zipCode));
    }

    private List<String> mergeMOZipCodes(List<String> museumZipCodes, List<String> organizationsZipCodes) {

        List<String> moZipCodes = museumZipCodes;

        for (String zipCode : organizationsZipCodes) {
            if (!moZipCodes.contains(zipCode)) {
                moZipCodes.add(zipCode);
            }
        }

        return moZipCodes;
    }


    private static final Logger LOG = LoggerFactory.getLogger(MOService.class);

}
