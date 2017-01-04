package com.sduran.api.service;

import com.sduran.api.repository.MuseumRepository;
import com.sduran.api.repository.OrganizationRepository;
import com.sduran.api.web.resource.StatisticsResource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticsService {

    @Autowired
    private OrganizationRepository organizationRepository;
    @Autowired
    private MuseumRepository museumRepository;

    @Transactional(readOnly = true)
    public List<StatisticsResource> findZipCodeStatistics() {

        final String method = "findZipCodeStatistics";

        LOG.info("{}, Searching for statistics using zip code", method);

        List<String> museumZipCodes = museumRepository.findDistinctZipCodes();
        List<String> organizationsZipCodes = organizationRepository.findDistinctZipCodes();

        List<String> zipCodeStatisticsList = mergeZipCodesList(museumZipCodes, organizationsZipCodes);

        List<StatisticsResource> statisticsList = new ArrayList<>();

        for (String zipCode : zipCodeStatisticsList){
            StatisticsResource statisticsResource = new StatisticsResource();
            populateZipCodeStatistics(zipCode, statisticsResource);
            statisticsList.add(statisticsResource);
        }

        return statisticsList;
    }

    public StatisticsResource findMaxZipCode(List<StatisticsResource> list) {
        long max = -1;
        StatisticsResource zipCodeWithMoreAmount = new StatisticsResource();

        for (StatisticsResource item : list){
            long tmpMax = item.getMuseumsCount() + item.getOrganizationsCount();
            if (tmpMax > max){
                max =tmpMax;
                zipCodeWithMoreAmount = item;
            }
        }

        return zipCodeWithMoreAmount;

    }

    private void populateZipCodeStatistics(String zipCode, StatisticsResource statisticsResource) {
        statisticsResource.setZipCode(zipCode);
        statisticsResource.setMuseumsCount(museumRepository.countByZipCode(zipCode));
        statisticsResource.setOrganizationsCount(organizationRepository.countByZipCode(zipCode));
    }

    private List<String> mergeZipCodesList(List<String> museumZipCodes, List<String> organizationsZipCodes) {

        List<String> mergedZipCodesList = museumZipCodes;

        for (String zipCode : organizationsZipCodes) {
            if (!mergedZipCodesList.contains(zipCode)) {
                mergedZipCodesList.add(zipCode);
            }
        }

        return mergedZipCodesList;
    }


    private static final Logger LOG = LoggerFactory.getLogger(StatisticsService.class);


}
