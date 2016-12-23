package com.sduran.model;

import com.sun.jersey.api.client.GenericType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
@Entity
@Table(name = "museum")

public class Museum implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(nullable = false)
    private int id;

    private String name;

    @Column(name = "zip_code")
    private String zipCode;

    private String neighborhood;

    @Column(name = "council_district")
    private Integer councilDistrict;

    @Column(name = "police_district")
    private String policeDistrict;

    private String address;

    public Museum() {

    }

    public Museum(String name, String address, String neighborhood, Integer councilDistrict, String policeDistrict, String zipCode) {
        this.name = name;
        this.address = address;
        this.neighborhood = neighborhood;
        this.councilDistrict = councilDistrict;
        this.policeDistrict = policeDistrict;
        this.zipCode = zipCode;
    }


}
