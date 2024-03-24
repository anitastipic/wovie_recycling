package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.Container;
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

    @GetMapping(value = "/metalWaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> findByContainerByMetalWaste() {return containerService.findByMetalWaste(true);}

    @GetMapping(value = "/paperWaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> findByContainerByPaperWaste() {return containerService.findByPaperWaste(true);}

    @GetMapping(value = "/organicWaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> findByContainerByOrganicWaste() {return containerService.findByOrganicWaste(true);}

    @GetMapping(value = "/plasticWaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> findByContainerByPlasticWaste() {return containerService.findByPlasticWaste(true);}

    @GetMapping(value = "/glassWaste", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> findByContainerByGlassWaste() {return containerService.findByGlassWaste(true);}

    @GetMapping(value = "/filter", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Container> filterContainers(
            @RequestParam(required = false) List<String> wasteTypes,
            @RequestParam(required = false) String districtName) {
        return containerService.findByFilters(wasteTypes, districtName);
    }
    @PostMapping
    @Transactional
    public Container save(@RequestBody Container container) {
        return containerService.save(container);
    }
}
