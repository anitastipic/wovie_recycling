package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.dto.Feature;
import com.codecool.wovie_recycling.dto.Geometry;
import com.codecool.wovie_recycling.dto.Properties;
import com.codecool.wovie_recycling.exception.ContainerNotFoundException;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.repository.ContainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ContainerService {
    private final ContainerRepository containerRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContainerService.class);

    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }

    public List<Container> findAll() {
        return containerRepository.findAll();
    }

    @Transactional
    public Container save(Container container) {
        return containerRepository.save(container);
    }

    public Container findById(long id) throws ContainerNotFoundException {
        return containerRepository.findById(id)
                .orElseThrow(ContainerNotFoundException::new);
    }

    @Transactional
    public void saveContainersWithDistricts(List<Feature> features, DistrictService districtService) {
        features.forEach(feature -> {
            Geometry geometry = feature.getGeometry();
            Properties properties = feature.getProperties();
            Container newContainer = new Container(geometry.getCoordinates().get(0), geometry.getCoordinates().get(1),
                    properties.getDistrict(), properties.getStreet(), properties.getPaperWaste(),
                    properties.getOrganicWaste(), properties.getMetalWaste(), properties.getGlassWaste(),
                    properties.getPlasticWaste(), properties.getStreetNumber());
            District foundDistrict = districtService.findByDistrictNumber(newContainer.getDistrictNumber());
            newContainer.setDistrict(foundDistrict);
            save(newContainer);
        });
    }
}
