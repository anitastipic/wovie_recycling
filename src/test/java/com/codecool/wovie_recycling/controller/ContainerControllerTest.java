package com.codecool.wovie_recycling.controller;

import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.service.ContainerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@Transactional
public class ContainerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private ContainerService containerService;

    @BeforeEach
    public void setUp() {
        Container newContainer = new Container(
                2.0, 3.0, 1, "street", true, false,
                false, false, false, "3");
        newContainer.setId(1L);

        Mockito.when(containerService.findById(1L)).thenReturn(Optional.of(newContainer));
        Mockito.when(containerService.findById(99L)).thenThrow(new EntityNotFoundException());

    }

    @Test
    public void findByAllReturnsAllContainers() throws Exception {
        mockMvc.perform(get("/container"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void findByIdWhenContainerExistsReturnContainer() throws Exception {
        mockMvc.perform(get("/container/id/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    public void findByIdWhenContainerDoesNotExistReturnNotFound() throws Exception {
        long nonExistentId = 99;

        mockMvc.perform(get("/container/id/" + nonExistentId))
                .andExpect(status().isNotFound())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof EntityNotFoundException));
    }
}
