package com.codecool.wovie_recycling.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class District {
    @Id
    @GeneratedValue
    @Column(name = "district_id")
    private Long id;

    private String district_name;
    private int districtNumber;

    protected District(){}

    public District(String district_name, int districtNumber) {
        this.district_name = district_name;
        this.districtNumber = districtNumber;
    }
}
