package com.codecool.wovie_recycling.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class District {
    @Id
    @GeneratedValue
    @Column(name = "districtId")
    private Long id;
    private String districtName;
    private int districtNumber;
    @OneToMany(mappedBy = "district", cascade = CascadeType.ALL)
    private List<Container> containers = new ArrayList<>();

    protected District(){}

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

    public List<Container> getContainers() {
        return containers;
    }

    public void setContainers(List<Container> containers) {
        this.containers = containers;
    }

    public void addContainer(Container container) {
        containers.add(container);
        if (container.getDistrict() != this) {
            container.setDistrict(this);
        }
    }
}
