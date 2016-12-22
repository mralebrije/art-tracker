package com.sduran.batch.processor;

import com.sduran.api.web.response.OrganizationSodaResponse;
import com.sduran.model.Organization;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

public class OrganizationSodaResponseItemProcessor implements ItemProcessor<OrganizationSodaResponse, Organization> {

    @Override
    public Organization process(final OrganizationSodaResponse organizationSodaResponse) {
        final String method = "process";

        Organization organization = new Organization();

        organization.setOrganization(organizationSodaResponse.getOrganization());
        organization.setAddress(organizationSodaResponse.getAddress());
        organization.setZipCode(organizationSodaResponse.getZipCode());
        organization.setCityState(organizationSodaResponse.getCityState());

        if (organizationSodaResponse.getUrl() != null)
            organization.setUrl(organizationSodaResponse.getUrl().getUrl());
        if (organizationSodaResponse.getLocation1() != null) {
            organization.setLatitude(organizationSodaResponse.getLocation1().getLatitude());
            organization.setLongitude(organizationSodaResponse.getLocation1().getLongitude());
        }

        LOG.debug("{}, Converting row:({}) into entity:({})", method, organizationSodaResponse, organization);
        return organization;
    }

    private static final Logger LOG = LoggerFactory.getLogger(OrganizationSodaResponseItemProcessor.class);
}
