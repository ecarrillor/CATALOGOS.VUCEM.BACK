package com.example.vucem_catalogos_service.persistence.specification;

import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public class GenericSearchSpecification {
    public static <T> Specification<T> searchInFields(
            String search,
            List<String> fields
    ) {
        return (root, query, cb) -> {
            if (search == null || search.isBlank()) {
                return cb.conjunction();
            }

            String like = "%" + search.toLowerCase() + "%";

            return cb.or(
                    fields.stream()
                            .map(field ->
                                    cb.like(
                                            cb.lower(root.get(field)),
                                            like
                                    )
                            )
                            .toArray(jakarta.persistence.criteria.Predicate[]::new)
            );
        };
    }
}
