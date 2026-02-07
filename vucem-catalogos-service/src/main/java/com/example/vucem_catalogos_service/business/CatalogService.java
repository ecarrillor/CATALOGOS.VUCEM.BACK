package com.example.vucem_catalogos_service.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;



public interface CatalogService<T, ID> {

    String getCatalogKey();

    Page<T> findAll(Pageable pageable);



    T save(T entity);

    void deleteById(ID id);
}