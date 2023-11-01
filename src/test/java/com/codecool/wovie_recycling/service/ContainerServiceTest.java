package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.dto.Feature;
import com.codecool.wovie_recycling.dto.Geometry;
import com.codecool.wovie_recycling.dto.Properties;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.repository.ContainerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ContainerServiceTest {
    @Mock
    private ContainerRepository containerRepository;

    @Mock
    private DistrictService districtService;

    @InjectMocks
    private ContainerService containerService;

    @Test
    public void saveContainersWithDistricts() {
        Geometry mockGeometry = new Geometry();
        mockGeometry.setCoordinates(List.of(1.0, 2.0));
        Properties mockProperties = new Properties(2, "Main Street", "3", true, false, false, false, false);
        Feature mockFeature = new Feature();
        mockFeature.setGeometry(mockGeometry);
        mockFeature.setProperties(mockProperties);
        List<Feature> features = List.of(mockFeature);
        District mockDistrict = new District("second", 2);
        when(districtService.findByDistrictNumber(anyInt())).thenReturn(mockDistrict);

        containerService.saveContainersWithDistricts(features, districtService);

        verify(containerRepository, times(1)).save(any(Container.class));
        verify(districtService, times(1)).findByDistrictNumber(anyInt());

    }


}
