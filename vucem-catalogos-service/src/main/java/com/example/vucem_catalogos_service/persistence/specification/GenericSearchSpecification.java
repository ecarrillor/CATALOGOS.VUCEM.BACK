package com.example.vucem_catalogos_service.persistence.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
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
            List<Predicate> predicates = new ArrayList<>();

            for (String field : fields) {

                Class<?> type = root.get(field).getJavaType();

                // 🔹 Campos String → LIKE
                if (String.class.equals(type)) {
                    predicates.add(
                            cb.like(
                                    cb.lower(root.get(field)),
                                    like
                            )
                    );
                }

                // 🔹 Campos numéricos → EQUAL
                else if (Number.class.isAssignableFrom(type)) {
                    try {
                        if (type.equals(Short.class)) {
                            predicates.add(
                                    cb.equal(root.get(field), Short.valueOf(search))
                            );
                        } else if (type.equals(Integer.class)) {
                            predicates.add(
                                    cb.equal(root.get(field), Integer.valueOf(search))
                            );
                        } else if (type.equals(Long.class)) {
                            predicates.add(
                                    cb.equal(root.get(field), Long.valueOf(search))
                            );
                        }
                    } catch (NumberFormatException ignored) {
                        // Si el search no es número, simplemente no agrega predicate
                    }
                }
            }

            return cb.or(predicates.toArray(new Predicate[0]));
        };
    }
}
