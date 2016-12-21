package com.sduran.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    @Column(name = "street_address")
    private String streetAddress;

    private String latitude;

    private String longitude;

    private Location(String streetAddress, String latitude, String longitude) {
        this.streetAddress = streetAddress;
        this.longitude = longitude;
        this.latitude = latitude;
    }

    public Location() {
    }
}
