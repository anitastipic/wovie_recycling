package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.service.ContainerService;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("container")
public class ContainerController {
    private final ContainerService containerService;

    public ContainerController(ContainerService containerService) {
        this.containerService = containerService;
    }
    @GetMapping
    List<Container> findAll() {
        return containerService.findAll();
    }

    @GetMapping("/id/{id}")
    Container findById(@PathVariable long id) throws Throwable {
        return containerService.findById(id);
    }

    @PostMapping
    @Transactional
    public Container save(@RequestBody Container container) {
        return containerService.save(container);
    }


}
