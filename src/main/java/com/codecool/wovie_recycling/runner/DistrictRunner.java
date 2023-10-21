package com.codecool.wovie_recycling.runner;

import com.codecool.wovie_recycling.dto.DistrictDto;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.service.DistrictService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

import javax.imageio.IIOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@Order(0)
public class DistrictRunner {
    @Bean
    CommandLineRunner initializeDbWithDistricts(DistrictService districtService) {
        return args -> {
            ObjectMapper objectMapper = new ObjectMapper();
            InputStream inputStream = TypeReference.class.getResourceAsStream("/json/districts.json");
            try {
                List<DistrictDto> districts = objectMapper.readValue(inputStream, new TypeReference<List<DistrictDto>>() {});
                districts.forEach(districtDto -> {
                    District district = new District(districtDto.getDistrictName(), districtDto.getDistrictNumber());
                    districtService.save(district);
                });
                System.out.println("Districts saved");
            } catch (IIOException e) {
                System.out.println("Unable to save districts" + e.getMessage());
            }
        };
    }
}
