package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.service.ContainerService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.MediaType;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "container")
@CrossOrigin(origins = "http://localhost:5173")
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }

    @GetMapping
    List<Container> findAll() {
        return containerService.findAll();
    }

    @RequestMapping(value = "/id/{id}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    Container findById(@PathVariable long id) throws EntityNotFoundException {
        return containerService.findById(id)
                .orElseThrow(EntityNotFoundException::new);
    }

    @RequestMapping(value = "/district/{district}", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    List<Container> findByDistrictNumber(@PathVariable int district) {
        return containerService.findByDistrictNumber(district);
    }

    @GetMapping("/district/name/{name}")
    public  List<Container> findByDistrictName(@PathVariable String name) {return containerService.findByDistrictName(name);}

    @PostMapping
    @Transactional
    public Container save(@RequestBody Container container) {
        return containerService.save(container);
    }
}
