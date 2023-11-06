package com.codecool.wovie_recycling.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class District {
    @Id
    @GeneratedValue
    @Column(name = "districtId")
    private Long id;
    private String districtName;
    private int districtNumber;

    protected District() {
    }

    public District(String districtName, int districtNumber) {
        this.districtName = districtName;
        this.districtNumber = districtNumber;
    }

    public Long getId() {
        return id;
    }

    public String getDistrictName() {
        return districtName;
    }

    public void setDistrictName(String district_name) {
        this.districtName = district_name;
    }

    public int getDistrictNumber() {
        return districtNumber;
    }

    public void setDistrictNumber(int districtNumber) {
        this.districtNumber = districtNumber;
    }


}
