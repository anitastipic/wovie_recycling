package com.codecool.wovie_recycling.runner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureDTO {

    private GeometryDTO geometry;
    private PropertiesDTO properties;

    public GeometryDTO getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryDTO geometry) {
        this.geometry = geometry;
    }

    public PropertiesDTO getProperties() {
        return properties;
    }

    public void setProperties(PropertiesDTO properties) {
        this.properties = properties;
    }
}
