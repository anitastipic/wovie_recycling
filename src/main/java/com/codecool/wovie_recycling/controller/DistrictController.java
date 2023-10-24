package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.service.DistrictService;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("district")
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

    @GetMapping("/number/{number}")
    public District findByDistrictNumber(@RequestParam int number) {return districtService.findByDistrictNumber(number);}
}
