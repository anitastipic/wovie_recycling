package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.service.DistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("district")
@CrossOrigin(origins = "http://localhost:5173")
public class DistrictController {
    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public List<District> findAll() {
        return districtService.findAll();
    }

    @PostMapping
    @Transactional
    public District save(District district) {return districtService.save(district);}

    @GetMapping("/name/{number}")
    public District findByDistrictNumber(@RequestParam int number) {return districtService.findByDistrictNumber(number);}


}
