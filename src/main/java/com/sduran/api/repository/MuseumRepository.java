package com.sduran.api.repository;

import com.sduran.model.Museum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;


public interface MuseumRepository extends JpaRepository<Museum, Long> {

    Museum findByName(String name);

    @Query(value = "select DISTINCT police_district from museum;", nativeQuery = true)
    List<String> findDistinctByPoliceDistrict();
}
