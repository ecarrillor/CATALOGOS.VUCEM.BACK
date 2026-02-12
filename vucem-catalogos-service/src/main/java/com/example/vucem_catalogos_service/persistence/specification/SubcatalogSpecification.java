package com.example.vucem_catalogos_service.persistence.specification;

import jakarta.persistence.criteria.JoinType;
import org.springframework.data.jpa.domain.Specification;

public class SubcatalogSpecification {
    public static <T> Specification<T> fetchSubcatalog(
            String relationName
    ) {
        return (root, query, cb) -> {
            root.fetch(relationName, JoinType.LEFT);
            query.distinct(true);
            return cb.conjunction();
        };
    }

}
