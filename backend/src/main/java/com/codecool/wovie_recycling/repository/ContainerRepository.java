package com.codecool.wovie_recycling.repository;

import com.codecool.wovie_recycling.model.Container;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContainerRepository extends JpaRepository<Container, Long> {
    List<Container> findByDistrictNumber(int district);
    List<Container> findByDistrictName(String name);
}
