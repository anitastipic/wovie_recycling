package com.codecool.wovie_recycling.runner;

import com.codecool.wovie_recycling.dto.*;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.service.ContainerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
public class ContainerRunner {
    @Bean
    CommandLineRunner initializeDbWithContainers(ContainerService containerService) {
        return args -> {
            ObjectMapper mapper = new ObjectMapper();
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/containers.json");
            try {
                FeatureCollection featureCollection = mapper.readValue(inputStream, FeatureCollection.class);
                List<Feature> features = featureCollection.getFeatures();
                features.forEach(feature -> {
                    Geometry geometry = feature.getGeometry();
                    Properties properties = feature.getProperties();
                    Container newContainer = new Container(geometry.getCoordinates().get(0), geometry.getCoordinates().get(1),
                                                            properties.getDistrict(), properties.getAddress(), properties.getPaperWaste(),
                                                            properties.getOrganicWaste(), properties.getMetalWaste(), properties.getGlassWaste(),
                                                            properties.getPlasticWaste());
                    containerService.save(newContainer);
                    System.out.println("Container saved");
                });

            } catch (IOException e) {
                System.out.println("Unable to save containers: " + e.getMessage());
            }
        };
    }
}