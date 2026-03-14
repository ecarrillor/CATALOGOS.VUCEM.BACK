package com.example.vucem_catalogos_service.persistence.specification;

import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GenericDateRangeSpecification {

    public static <T> Specification<T> byDateRange(
            Map<String, String> filters,
            String dateField
    ) {
        return (root, query, cb) -> {
            if (filters == null || filters.isEmpty()) {
                return cb.conjunction();
            }

            String fecDesde = filters.get("fecDesde");
            String fecHasta = filters.get("fecHasta");

            if (fecDesde == null && fecHasta == null) {
                return cb.conjunction();
            }

            List<Predicate> predicates = new ArrayList<>();
            Class<?> type = root.get(dateField).getJavaType();

            if (Instant.class.equals(type)) {
                if (fecDesde != null) {
                    Instant from = LocalDate.parse(fecDesde).atStartOfDay(ZoneOffset.UTC).toInstant();
                    predicates.add(cb.greaterThanOrEqualTo(root.get(dateField), from));
                }
                if (fecHasta != null) {
                    Instant to = LocalDate.parse(fecHasta).atTime(23, 59, 59).atOffset(ZoneOffset.UTC).toInstant();
                    predicates.add(cb.lessThanOrEqualTo(root.get(dateField), to));
                }
            } else if (LocalDate.class.equals(type)) {
                if (fecDesde != null) {
                    LocalDate from = LocalDate.parse(fecDesde);
                    predicates.add(cb.greaterThanOrEqualTo(root.get(dateField), from));
                }
                if (fecHasta != null) {
                    LocalDate to = LocalDate.parse(fecHasta);
                    predicates.add(cb.lessThanOrEqualTo(root.get(dateField), to));
                }
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
