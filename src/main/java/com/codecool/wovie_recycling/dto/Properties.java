package com.codecool.wovie_recycling.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Properties {
    @JsonProperty("BEZIRK")
    private int district;

    @JsonProperty("STRASSE")
    private String address;
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

    @JsonProperty("BEZIRK")
    private int jsonDistrict;

}
