package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.exception.ContainerNotFoundException;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.repository.ContainerRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("container")
public class ContainerController {
    private final ContainerRepository containerRepository;

    public ContainerController(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }
    @GetMapping
    List<Container> findAll() {
        return containerRepository.findAll();
    }

    @GetMapping("/id/{id}")
    Container findById(@PathVariable long id) throws Throwable {
        return containerRepository.findById(id)
                .orElseThrow(ContainerNotFoundException::new);
    }

    @PostMapping
    @Transactional
    public Container save(@RequestBody Container container) {
        return containerRepository.save(container);
    }


}
