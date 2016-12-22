package com.sduran.api.service;

import com.sduran.api.repository.MuseumRepository;
import com.sduran.api.service.exception.MuseumNotFoundException;
import com.sduran.api.web.request.MuseumRequest;
import com.sduran.model.Museum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MuseumService {

    @Autowired
    private MuseumRepository museumRepository;

    @Transactional(readOnly = true)
    public List<Museum> listAllMuseums() {

        List<Museum> museumList = museumRepository.findAll();
        return museumList;
    }

    @Transactional(readOnly = false)
    public void createOrUpdateMuseum(final MuseumRequest museumRequest) {

        Museum museum = museumRepository.findByName(museumRequest.getName());
        if (museum == null) {
            museum = new Museum();
            LOG.info("Museum to create: {}", museumRequest.getName());
        }

        museum.setName(museumRequest.getName());
        museum.setZipCode(museumRequest.getZipCode());
        museum.setAddress(museumRequest.getAddress());
        museum.setNeighborhood(museumRequest.getNeighborhood());
        museum.setPoliceDistrict(museumRequest.getPoliceDistrict());
        museum.setCouncilDistrict(museumRequest.getCouncilDistrict());

        museumRepository.save(museum);
        LOG.info("Museum updated: {}", museumRequest.getName());

    }

    @Transactional(readOnly = false)
    public void deleteMuseum(final String museumName) throws MuseumNotFoundException {

        Museum museum = museumRepository.findByName(museumName);
        if (museum == null) {
            LOG.warn("Museum not exists: {}", museumName);
            throw new MuseumNotFoundException("Museum not exists: " + museumName);
        }

        museumRepository.delete(museum);
        LOG.info("Museum deleted: {}", museum.getName());
    }

    @Transactional(readOnly = true)
    public List<String> listAllCouncil() {

        List<String> councilList = museumRepository.findDistinctByPoliceDistrict();
        return councilList;
    }


    private static final Logger LOG = LoggerFactory.getLogger(MuseumService.class);

}
