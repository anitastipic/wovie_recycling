package com.codecool.wovie_recycling.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    @JsonProperty("BEZIRK")
    private int district;
    @JsonProperty("STRASSE")
    private String street;
    @JsonProperty("ONR")
    private String streetNumber;
    @JsonProperty("FRAKTION_PA")
    private Boolean paperWaste;
    @JsonProperty("FRAKTION_BI")
    private Boolean organicWaste;
    @JsonProperty("FRAKTION_DO")
    private Boolean metalWaste;
    @JsonProperty("FRAKTION_G")
    private Boolean glassWaste;
    @JsonProperty("FRAKTION_KV")
    private Boolean plasticWaste;

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getDistrict() {
        return district;
    }

    public void setDistrict(int district) {
        this.district = district;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
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
