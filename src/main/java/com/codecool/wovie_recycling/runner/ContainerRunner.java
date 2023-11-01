package com.codecool.wovie_recycling.runner;

import com.codecool.wovie_recycling.service.ContainerService;
import com.codecool.wovie_recycling.service.DistrictService;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration

public class ContainerRunner {
    @Bean
    @Order(1)
    @DependsOn("initializeDbWithDistricts")
    CommandLineRunner initializeDbWithContainers(ContainerService containerService, DistrictService districtService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/containers.json");
            try {
                FeatureCollection featureCollection = mapper.readValue(inputStream, FeatureCollection.class);
                List<FeatureDTO> features = featureCollection.getFeatures();
                containerService.saveContainersWithDistricts(features, districtService);
                System.out.println("Containers saved");
            } catch (IOException e) {
                System.out.println("Unable to save containers: " + e.getMessage());
            }
        };
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Properties {
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

        protected Properties() {}
        public Properties(int district, String street, String streetNumber, Boolean paperWaste, Boolean organicWaste, Boolean metalWaste, Boolean glassWaste, Boolean plasticWaste) {
            this.district = district;
            this.street = street;
            this.streetNumber = streetNumber;
            this.paperWaste = paperWaste;
            this.organicWaste = organicWaste;
            this.metalWaste = metalWaste;
            this.glassWaste = glassWaste;
            this.plasticWaste = plasticWaste;
        }

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
}