package com.sduran.api.web.builder;

import com.sduran.api.web.request.MuseumRequest;

public class MuseumBuilder {
    private MuseumRequest museumRequest = new MuseumRequest();

    public MuseumBuilder name(String name) {
        museumRequest.setName(name);
        return this;
    }

    public MuseumBuilder address(String address) {
        museumRequest.setAddress(address);
        return this;
    }

    public MuseumBuilder zipCode(String zipCode) {
        museumRequest.setZipCode(zipCode);
        return this;
    }

    public MuseumBuilder councilDistrict(int councilDistrict) {
        museumRequest.setCouncilDistrict(councilDistrict);
        return this;
    }

    public MuseumBuilder neighborhood(String neighborhood) {
        museumRequest.setNeighborhood(neighborhood);
        return this;
    }

    public MuseumBuilder policeDistrict(String policeDistrict) {
        museumRequest.setPoliceDistrict(policeDistrict);
        return this;
    }

    public MuseumRequest build() {
        return museumRequest;
    }
}
