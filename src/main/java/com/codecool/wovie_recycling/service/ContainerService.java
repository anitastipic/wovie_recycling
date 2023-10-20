package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.repository.ContainerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ContainerService {
    private final ContainerRepository containerRepository;
    private static final Logger logger = LoggerFactory.getLogger(ContainerService.class);
    public ContainerService(ContainerRepository containerRepository) {
        this.containerRepository = containerRepository;
    }
    @Transactional
    public Container save(Container container) {
        return containerRepository.save(container);
    }
}
