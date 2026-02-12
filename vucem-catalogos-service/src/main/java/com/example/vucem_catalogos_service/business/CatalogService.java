package com.example.vucem_catalogos_service.business;

import com.fasterxml.jackson.databind.JsonMappingException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Map;


public interface CatalogService<T, ID> {

    String getCatalogKey();

    Page<T> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable
    );


    //T update(ID id, Object body) throws JsonMappingException;


    T save(Object body);

    Class<T> getEntityClass();



}