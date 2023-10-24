package com.codecool.wovie_recycling.runner;

import com.codecool.wovie_recycling.dto.*;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.service.ContainerService;
import com.codecool.wovie_recycling.service.DistrictService;
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
                List<Feature> features = featureCollection.getFeatures();
                containerService.saveContainersWithDistricts(features, districtService);
                System.out.println("Containers saved");
            } catch (IOException e) {
                System.out.println("Unable to save containers: " + e.getMessage());
            }
        };
    }
}