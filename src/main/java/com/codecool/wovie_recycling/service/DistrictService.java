package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.repository.DistrictRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DistrictService {
    private final DistrictRepository districtRepository;

    public DistrictService(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Transactional
    public District save(District district) {
        return districtRepository.save(district);
    }
}
