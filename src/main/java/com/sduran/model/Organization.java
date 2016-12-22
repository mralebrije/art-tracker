package com.sduran.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@ToString
@SuppressWarnings("serial")
@Entity
@Table(name = "organization")

public class Organization implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private int id;

    private String organization;

    private String address;

    @Column(name = "zip_code")
    private String zipCode;

    @Column(name = "city_State")
    private String cityState;

    private String url;

    public Organization() {
    }
}
