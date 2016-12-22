package com.sduran.api.repository;

import com.sduran.model.Museum;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface MuseumRepository extends CrudRepository<Museum, Long> {

    List<Museum> findByZipCode(String zipCode);
}
