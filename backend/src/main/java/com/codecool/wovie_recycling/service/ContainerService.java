package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.runner.FeatureDTO;
import com.codecool.wovie_recycling.runner.GeometryDTO;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.repository.ContainerRepository;
import com.codecool.wovie_recycling.runner.PropertiesDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

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

    public Optional<Container> findById(long id) {
        return containerRepository.findById(id);
    }

    @Transactional
    public void saveContainersWithDistricts(List<FeatureDTO> features, DistrictService districtService) {
        features.forEach(feature -> {
            GeometryDTO geometry = feature.getGeometry();
            PropertiesDTO properties = feature.getProperties();
            Container newContainer = new Container(geometry.getCoordinates().get(0), geometry.getCoordinates().get(1),
                    properties.getDistrict(), properties.getStreet(), properties.getPaperWaste(),
                    properties.getOrganicWaste(), properties.getMetalWaste(), properties.getGlassWaste(),
                    properties.getPlasticWaste(), properties.getStreetNumber());
            District foundDistrict = districtService.findByDistrictNumber(newContainer.getDistrictNumber());
            newContainer.setDistrictId(foundDistrict.getId());
            newContainer.setDistrictName(foundDistrict.getDistrictName());
            if (newContainer.getPaperWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        newContainer.getPaperWaste(), false, false, false, false, newContainer.getStreetNumber());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getOrganicWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, newContainer.getOrganicWaste(), false, false, false, newContainer.getStreetNumber());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getMetalWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, false, newContainer.getMetalWaste(), false, false, newContainer.getStreetNumber());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getGlassWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, false, false, newContainer.getGlassWaste(), false, newContainer.getStreetNumber());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getPlasticWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, false, false, false, newContainer.getPlasticWaste(), newContainer.getStreetNumber());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }


        });
    }

    public List<Container> findByDistrictNumber(int district) {
        return containerRepository.findByDistrictNumber(district);
    }

    public List<Container> findByDistrictName(String name) {
        return containerRepository.findByDistrictName(name);
    }
}
