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
                    Container newContainer = new Container();
                    Geometry geometry = feature.getGeometry();
                    Properties properties = feature.getProperties();
                    newContainer.setLatitude(geometry.getCoordinates().get(0));
                    newContainer.setLongitude(geometry.getCoordinates().get(1));
                    newContainer.setAddress(properties.getAddress());
                    newContainer.setDistrict(properties.getDistrict());
                    newContainer.setGlassWaste(properties.getGlassWaste());
                    newContainer.setMetalWaste(properties.getMetalWaste());
                    newContainer.setPaperWaste(properties.getPaperWaste());
                    newContainer.setPlasticWaste(properties.getPlasticWaste());
                    newContainer.setOrganicWaste(properties.getOrganicWaste());
                    containerService.save(newContainer);
                    System.out.println("Container saved");
                });

            } catch (IOException e) {
                System.out.println("Unable to save containers: " + e.getMessage());
            }
        };
    }
}