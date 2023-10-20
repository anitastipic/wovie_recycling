package com.codecool.wovie_recycling.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
public class Container {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double latitude;
    private Double longitude;
    private int district;
    private String address;
    private Boolean paperWaste;
    private Boolean organicWaste;
    private Boolean metalWaste;
    private Boolean glassWaste;
    private Boolean plasticWaste;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Boolean getPaperWaste() {
        return paperWaste;
    }

    public void setPaperWaste(Boolean paperWaste) {
        this.paperWaste = paperWaste;
    }

    public Boolean getOrganicWaste() {
        return organicWaste;
    }

    public void setOrganicWaste(Boolean organicWaste) {
        this.organicWaste = organicWaste;
    }

    public Boolean getMetalWaste() {
        return metalWaste;
    }

    public void setMetalWaste(Boolean metalWaste) {
        this.metalWaste = metalWaste;
    }

    public Boolean getGlassWaste() {
        return glassWaste;
    }

    public void setGlassWaste(Boolean glassWaste) {
        this.glassWaste = glassWaste;
    }

    public Boolean getPlasticWaste() {
        return plasticWaste;
    }

    public void setPlasticWaste(Boolean plasticWaste) {
        this.plasticWaste = plasticWaste;
    }


}
