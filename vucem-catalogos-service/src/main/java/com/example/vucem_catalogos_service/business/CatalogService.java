package com.example.vucem_catalogos_service.business;

import aj.org.objectweb.asm.commons.Remapper;
import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;
import java.util.Optional;


public interface CatalogService<T, ID> {

    String getCatalogKey();

    Page<T> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable
    );

    T save(Object body);

    Optional<T> findById(String id);

    T update(String id, Object body) throws JsonMappingException;

    Class<T> getEntityClass();
}