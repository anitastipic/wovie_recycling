package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.runner.FeatureDTO;
import com.codecool.wovie_recycling.runner.GeometryDTO;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.repository.ContainerRepository;
import com.codecool.wovie_recycling.runner.PropertiesDTO;
import com.codecool.wovie_recycling.specifications.ContainerSpecifications;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
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
                singleContainer.setDistrictId(foundDistrict.getId());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getOrganicWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, newContainer.getOrganicWaste(), false, false, false, newContainer.getStreetNumber());
                singleContainer.setDistrictId(foundDistrict.getId());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getMetalWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, false, newContainer.getMetalWaste(), false, false, newContainer.getStreetNumber());
                singleContainer.setDistrictId(foundDistrict.getId());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getGlassWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, false, false, newContainer.getGlassWaste(), false, newContainer.getStreetNumber());
                singleContainer.setDistrictId(foundDistrict.getId());
                singleContainer.setDistrictName(foundDistrict.getDistrictName());
                save(singleContainer);
            }
            if (newContainer.getPlasticWaste()) {
                Container singleContainer = new Container(
                        newContainer.getLatitude(), newContainer.getLongitude(), newContainer.getDistrictNumber(), newContainer.getStreet(),
                        false, false, false, false, newContainer.getPlasticWaste(), newContainer.getStreetNumber());
                singleContainer.setDistrictId(foundDistrict.getId());
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

    public List<Container> findByMetalWaste(boolean metalWaste) {
        return containerRepository.findByMetalWaste(metalWaste);
    }

    public List<Container> findByPaperWaste(boolean paperWaste) {
        return containerRepository.findByPaperWaste(paperWaste);
    }

    public List<Container> findByOrganicWaste(boolean organicWaste) {
        return containerRepository.findByOrganicWaste(organicWaste);
    }

    public List<Container> findByPlasticWaste(boolean plasticWaste) {
        return containerRepository.findByPlasticWaste(plasticWaste);
    }

    public List<Container> findByGlassWaste(boolean glassWaste) {
        return containerRepository.findByGlassWaste(glassWaste);
    }


    public List<Container> findByFilters(List<String> wasteTypes, String districtName) {
        Specification<Container> specification = (root, query, criteriaBuilder) -> criteriaBuilder.conjunction();

        if (districtName != null && !districtName.isEmpty()) {
            specification = specification.and(ContainerSpecifications.isLocatedInDistrict(districtName));
        }

        Specification<Container> wasteTypeSpec = null;
        if (wasteTypes != null && !wasteTypes.isEmpty()) {
            for (String wasteType : wasteTypes) {
                Specification<Container> currentSpec = ContainerSpecifications.hasWasteType(wasteType);
                wasteTypeSpec = (wasteTypeSpec == null) ? currentSpec : wasteTypeSpec.or(currentSpec);
            }
        }

        if (wasteTypeSpec != null) {
            specification = specification.and(wasteTypeSpec);
        }

        return containerRepository.findAll(specification);
    }

}
