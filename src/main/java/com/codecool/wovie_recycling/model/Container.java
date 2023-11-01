package com.codecool.wovie_recycling.model;

import jakarta.persistence.*;

@Entity
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "containerId")
    private Long id;
    private Double latitude;
    private Double longitude;
    private int districtNumber;
    private Long districtId;
    private String street;
    @Column(nullable = true)
    private String streetNumber;
    private boolean paperWaste;
    private boolean organicWaste;
    private boolean metalWaste;
    private boolean glassWaste;
    private boolean plasticWaste;

    protected Container() {
    }

    public Container(Double latitude, Double longitude, int districtNumber, String street,
                     boolean paperWaste, boolean organicWaste, boolean metalWaste,
                     boolean glassWaste, boolean plasticWaste, String streetNumber) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.districtNumber = districtNumber;
        this.street = street;
        this.streetNumber = streetNumber;
        this.paperWaste = paperWaste;
        this.organicWaste = organicWaste;
        this.metalWaste = metalWaste;
        this.glassWaste = glassWaste;
        this.plasticWaste = plasticWaste;
    }


    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public int getDistrictNumber() {
        return districtNumber;
    }

    public void setDistrictNumber(int districtNumber) {
        this.districtNumber = districtNumber;
    }

    public Long getDistrictId() {
        return districtId;
    }

    public void setDistrictId(Long districtId) {
        this.districtId = districtId;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public boolean getPaperWaste() {
        return paperWaste;
    }

    public void setPaperWaste(Boolean paperWaste) {
        this.paperWaste = paperWaste;
    }

    public boolean getOrganicWaste() {
        return organicWaste;
    }

    public void setOrganicWaste(Boolean organicWaste) {
        this.organicWaste = organicWaste;
    }

    public boolean getMetalWaste() {
        return metalWaste;
    }

    public void setMetalWaste(Boolean metalWaste) {
        this.metalWaste = metalWaste;
    }

    public boolean getGlassWaste() {
        return glassWaste;
    }

    public void setGlassWaste(Boolean glassWaste) {
        this.glassWaste = glassWaste;
    }

    public boolean getPlasticWaste() {
        return plasticWaste;
    }

    public void setPlasticWaste(Boolean plasticWaste) {
        this.plasticWaste = plasticWaste;
    }
}
