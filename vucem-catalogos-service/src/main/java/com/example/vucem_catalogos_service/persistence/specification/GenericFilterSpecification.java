package com.example.vucem_catalogos_service.persistence.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.Map;

public class GenericFilterSpecification {
    public static <T> Specification<T> byFilters(
            Map<String, String> filters
    ) {
        return (root, query, cb) -> {
            if (filters == null || filters.isEmpty()) {
                return cb.conjunction();
            }

            return cb.and(
                    filters.entrySet().stream()
                            .map(e ->
                                    cb.equal(
                                            root.get(e.getKey()),
                                            e.getValue()
                                    )
                            )
                            .toArray(jakarta.persistence.criteria.Predicate[]::new)
            );
        };
    }
}
