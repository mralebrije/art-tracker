package com.sduran.api.service;

import com.sduran.api.repository.MuseumRepository;
import com.sduran.api.service.exception.MuseumNotFoundException;
import com.sduran.api.web.request.MuseumRequest;
import com.sduran.api.web.resource.MuseumResource;
import com.sduran.model.Museum;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class MuseumService {

    @Autowired
    private MuseumRepository museumRepository;

    @Transactional(readOnly = true)
    public List<MuseumResource> findAllMuseums() {

        // List to storage each museum resource response
        List<MuseumResource> museums = new ArrayList<>();

        List<Museum> museumList = museumRepository.findAll();

        for (Museum item : museumList) {
            MuseumResource museum = new MuseumResource();
            populateMuseum(item, museum);
            museums.add(museum);
        }

        return museums;
    }

    @Transactional(readOnly = true)
    public Long countAllMuseums() {
        return museumRepository.count();
    }


    @Transactional(readOnly = false)
    public int createOrUpdateMuseum(final MuseumRequest museumRequest) {

        Museum museum = null;

        if (!StringUtils.isBlank(museumRequest.getId())){
            museum = museumRepository.findById(Integer.valueOf(museumRequest.getId()));
        }


        if (museum == null) {
            museum = new Museum();
            LOG.info("Museum to create: {}", museumRequest.getName());
        }

        populateNewMuseum(museum,museumRequest);

        museumRepository.save(museum);
        LOG.info("Museum updated: {} with id: {}", museumRequest.getName(), museum.getId());
        return museum.getId();
    }

    @Transactional(readOnly = false)
    public void deleteMuseum(final String id) throws MuseumNotFoundException {

        Museum museum = museumRepository.findById(Integer.valueOf(id));
        if (museum == null) {
            LOG.warn("Museum not exists, for id:{}", id);
            throw new MuseumNotFoundException("Museum not exists, for id: " + id);
        }

        museumRepository.delete(museum);
        LOG.info("Museum deleted:{}", museum.getName());
    }

    @Transactional(readOnly = true)
    public List<String> findAllDistricts() {

        List<String> councilList = museumRepository.findDistinctByPoliceDistrict();
        return councilList;
    }

    @Transactional(readOnly = true)
    public Long countAllDistricts() {
        return museumRepository.countDistinctByPoliceDistrict();
    }

    private void populateMuseum(Museum item, MuseumResource museum) {

        museum.setId(item.getId());
        museum.setName(item.getName());
        museum.setNeighborhood(item.getNeighborhood());
        museum.setCouncilDistrict(item.getCouncilDistrict());
        museum.setPoliceDistrict(item.getPoliceDistrict());
        museum.setAddress(item.getAddress());
        museum.setZipCode(item.getZipCode());
    }

    private void populateNewMuseum(Museum museum, MuseumRequest museumRequest) {
        museum.setName(museumRequest.getName());
        museum.setZipCode(museumRequest.getZipCode());
        museum.setAddress(museumRequest.getAddress());
        museum.setNeighborhood(museumRequest.getNeighborhood());
        museum.setPoliceDistrict(museumRequest.getPoliceDistrict());
        museum.setCouncilDistrict(museumRequest.getCouncilDistrict());
    }

    private static final Logger LOG = LoggerFactory.getLogger(MuseumService.class);

}
