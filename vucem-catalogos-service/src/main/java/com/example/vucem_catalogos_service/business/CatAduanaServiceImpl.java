package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatAduana;
import com.example.vucem_catalogos_service.repository.CatAduanaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatAduanaServiceImpl implements CatalogService<CatAduana, String> {

    private final CatAduanaRepository repository;

    public CatAduanaServiceImpl(CatAduanaRepository repository) {
        this.repository = repository;
    }

    @Override
    public String getCatalogKey() {
        return "cat-aduana";
    }

    @Override
    public Page<CatAduana> findAll(Pageable pageable) {
        return repository.findAll(pageable);
    }



    @Override
    public CatAduana save(CatAduana entity) {
        return repository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        repository.deleteById(id);
    }
}
