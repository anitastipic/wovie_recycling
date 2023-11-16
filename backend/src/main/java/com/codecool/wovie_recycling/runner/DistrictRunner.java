package com.codecool.wovie_recycling.runner;

import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.service.DistrictService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.annotation.Order;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@Profile("!dev")
public class DistrictRunner {
    @Bean
    @Order(0)
    CommandLineRunner initializeDbWithDistricts(DistrictService districtService) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/districts.json");
            try {
                List<DistrictDTO> districts = objectMapper.readValue(inputStream, new TypeReference<List<DistrictDTO>>() {});
                districts.forEach(districtDto -> {
                    District district = new District(districtDto.getDistrictName(), districtDto.getDistrictNumber());
                    districtService.save(district);
                });
                System.out.println("Districts saved");
            } catch (IOException e) {
                System.out.println("Unable to save districts" + e.getMessage());
            }
        };
    }
}
