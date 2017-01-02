package com.sduran.api.repository;

import com.sduran.model.Organization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface OrganizationRepository extends JpaRepository<Organization, Long> {

    @Query(value = "select * from organization where zip_code = ?1 ;", nativeQuery = true)
    List<Organization> findByZipCode(String zipCode);

    @Query(value = "select COUNT(*) from organization where zip_code = ?1 ;", nativeQuery = true)
    Long countByZipCode(String zipCode);

    @Query(value = "select DISTINCT zip_code from organization;", nativeQuery = true)
    List<String> findDistinctZipCodes();
}
