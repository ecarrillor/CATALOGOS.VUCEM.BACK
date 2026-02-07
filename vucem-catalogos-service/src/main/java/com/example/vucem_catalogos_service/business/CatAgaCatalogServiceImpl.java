package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatAga;
import com.example.vucem_catalogos_service.repository.CatAgaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatAgaCatalogServiceImpl implements CatalogService<CatAga, String> {

    private final CatAgaRepository repository;

    public CatAgaCatalogServiceImpl(CatAgaRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getCatalogKey() {
        return "cat-aga";
    }

    @Override
    public Page<CatAga> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }



    @Override
    public CatAga save(CatAga entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}