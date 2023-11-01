package com.codecool.wovie_recycling.runner;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class FeatureDTO {

    private GeometryDTO geometry;
    private ContainerRunner.Properties properties;

    public GeometryDTO getGeometry() {
        return geometry;
    }

    public void setGeometry(GeometryDTO geometry) {
        this.geometry = geometry;
    }

    public ContainerRunner.Properties getProperties() {
        return properties;
    }

    public void setProperties(ContainerRunner.Properties properties) {
        this.properties = properties;
    }
}
