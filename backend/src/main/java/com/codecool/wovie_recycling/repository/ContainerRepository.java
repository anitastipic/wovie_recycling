package com.codecool.wovie_recycling.repository;

import com.codecool.wovie_recycling.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long>, JpaSpecificationExecutor<Container>{
    List<Container> findByDistrictNumber(int district);
    List<Container> findByDistrictName(String name);
    List<Container> findByMetalWaste(boolean metalWaste);
    List<Container> findByPaperWaste(boolean paperWaste);
    List<Container> findByOrganicWaste(boolean organicWaste);
    List<Container> findByPlasticWaste(boolean plasticWaste);
    List<Container> findByGlassWaste(boolean glassWaste);
}
