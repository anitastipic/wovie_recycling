package com.codecool.wovie_recycling.service;

import com.codecool.wovie_recycling.runner.ContainerRunner;
import com.codecool.wovie_recycling.runner.FeatureDTO;
import com.codecool.wovie_recycling.runner.GeometryDTO;
import com.codecool.wovie_recycling.model.Container;
import com.codecool.wovie_recycling.model.District;
import com.codecool.wovie_recycling.repository.ContainerRepository;
import com.codecool.wovie_recycling.runner.PropertiesDTO;
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
        GeometryDTO mockGeometry = new GeometryDTO();
        mockGeometry.setCoordinates(List.of(1.0, 2.0));
        PropertiesDTO mockProperties = new PropertiesDTO(2, "Main Street", "3", true, false, false, false, false);
        FeatureDTO mockFeature = new FeatureDTO();
        mockFeature.setGeometry(mockGeometry);
        mockFeature.setProperties(mockProperties);
        List<FeatureDTO> features = List.of(mockFeature);
        District mockDistrict = new District("second", 2);
        when(districtService.findByDistrictNumber(anyInt())).thenReturn(mockDistrict);

        containerService.saveContainersWithDistricts(features, districtService);

        verify(containerRepository, times(1)).save(any(Container.class));
        verify(districtService, times(1)).findByDistrictNumber(anyInt());

    }


}
