package com.sduran.api.service;

import com.sduran.api.repository.OrganizationRepository;
import com.sduran.api.web.resource.OrganizationResource;
import com.sduran.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrganizationService {

    @Autowired
    private OrganizationRepository organizationRepository;

    @Transactional(readOnly = true)
    public List<OrganizationResource> findOrganizationsByZipCode(String zipCode) {

        final String method = "findOrganizationsByZipCode";

        LOG.info("{}, Searching organizations with zip code: {} ",method, zipCode);

        // List to storage each museum resource response
        List<OrganizationResource> organizations = new ArrayList<>();

        List<Organization> organizationList = organizationRepository.findByZipCode(zipCode);

        for (Organization item : organizationList) {
            OrganizationResource organization = new OrganizationResource();
            populateOrganization(item, organization);
            organizations.add(organization);
        }

        return organizations;
    }

    @Transactional(readOnly = true)
    public Long countOrganizationsByZipCode(String zipCode) {
        return organizationRepository.countByZipCode(zipCode);
    }


    private void populateOrganization(Organization item, OrganizationResource organization) {

        organization.setOrganization(item.getOrganization());
        organization.setZipCode(item.getZipCode());
        organization.setLongitude(item.getLongitude());
        organization.setLatitude(item.getLatitude());
        organization.setAddress(item.getAddress());
        organization.setUrl(item.getUrl());
    }

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationService.class);

}
