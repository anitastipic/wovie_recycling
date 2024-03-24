package com.codecool.wovie_recycling.specifications;

import com.codecool.wovie_recycling.model.Container;
import org.springframework.data.jpa.domain.Specification;

public class ContainerSpecifications {
    public static Specification<Container> isLocatedInDistrict(String districtName) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("districtName"), districtName));
    }

    public static Specification<Container> hasWasteType(String wasteType) {
        return ((root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get(wasteType), true));
    }
}
