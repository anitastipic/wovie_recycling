package com.codecool.wovie_recycling.runner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureCollection {
    private List<FeatureDTO> features;

    public List<FeatureDTO> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDTO> features) {
        this.features = features;
    }
}

