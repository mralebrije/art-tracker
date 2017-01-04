package com.sduran.api.service;

import com.sduran.api.repository.MuseumRepository;
import com.sduran.api.repository.OrganizationRepository;
import com.sduran.api.web.resource.CouncilStatisticsResource;
import com.sduran.api.web.resource.ZipCodeStatisticsResource;
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
    public List<ZipCodeStatisticsResource> findZipCodeStatistics() {

        final String method = "findZipCodeStatistics";

        LOG.info("{}, Searching for statistics using zip code", method);

        List<String> museumZipCodes = museumRepository.findDistinctZipCodes();
        List<String> organizationsZipCodes = organizationRepository.findDistinctZipCodes();

        List<String> zipCodeStatisticsList = mergeZipCodesList(museumZipCodes, organizationsZipCodes);

        List<ZipCodeStatisticsResource> statisticsList = new ArrayList<>();

        for (String zipCode : zipCodeStatisticsList){
            ZipCodeStatisticsResource zipCodeStatisticsResource = new ZipCodeStatisticsResource();
            populateZipCodeStatistics(zipCode, zipCodeStatisticsResource);
            statisticsList.add(zipCodeStatisticsResource);
        }

        return statisticsList;
    }

    public ZipCodeStatisticsResource findMaxZipCode(List<ZipCodeStatisticsResource> list) {
        long max = -1;
        ZipCodeStatisticsResource zipCodeWithMoreAmount = new ZipCodeStatisticsResource();

        for (ZipCodeStatisticsResource item : list){
            long tmpMax = item.getMuseumsCount() + item.getOrganizationsCount();
            if (tmpMax > max){
                max =tmpMax;
                zipCodeWithMoreAmount = item;
            }
        }

        return zipCodeWithMoreAmount;

    }

    @Transactional(readOnly = true)
    public List<CouncilStatisticsResource> findCouncilStatistics() {

        final String method = "findCouncilStatistics";

        LOG.info("{}, Searching for statistics using council district", method);

        List<Integer> museumCouncilDistrict = museumRepository.findDistinctCouncilDistrict();
        List<CouncilStatisticsResource> statisticsList = new ArrayList<>();

        for (Integer councilDistrict : museumCouncilDistrict){
            CouncilStatisticsResource councilStatisticsResource = new CouncilStatisticsResource();
            populateCouncilStatistics(councilDistrict, councilStatisticsResource);
            statisticsList.add(councilStatisticsResource);
        }

        return statisticsList;
    }

    public CouncilStatisticsResource findMaxCouncil(List<CouncilStatisticsResource> list) {
        long max = -1;
        CouncilStatisticsResource councilWithMoreAmount = new CouncilStatisticsResource();

        for (CouncilStatisticsResource item : list){
            long tmpMax = item.getMuseumsCount();
            if (tmpMax > max){
                max =tmpMax;
                councilWithMoreAmount = item;
            }
        }

        return councilWithMoreAmount;

    }

    private void populateCouncilStatistics(Integer councilDistrict, CouncilStatisticsResource councilStatisticsResource) {

        councilStatisticsResource.setCouncilDistrict(councilDistrict);
        councilStatisticsResource.setMuseumsCount(museumRepository.countByCouncilDistrict(councilDistrict));
    }

    private void populateZipCodeStatistics(String zipCode, ZipCodeStatisticsResource zipCodeStatisticsResource) {
        zipCodeStatisticsResource.setZipCode(zipCode);
        zipCodeStatisticsResource.setMuseumsCount(museumRepository.countByZipCode(zipCode));
        zipCodeStatisticsResource.setOrganizationsCount(organizationRepository.countByZipCode(zipCode));
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
