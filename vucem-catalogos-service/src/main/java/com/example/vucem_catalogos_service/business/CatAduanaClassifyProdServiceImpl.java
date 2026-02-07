package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatAduanaClasifProd;
import com.example.vucem_catalogos_service.repository.CatAduanaClasifProdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CatAduanaClassifyProdServiceImpl implements CatalogService<CatAduanaClasifProd, String> {
    @Autowired
    private CatAduanaClasifProdRepository catAduanaClasifProdRepository;

    @Override
    public String getCatalogKey() {
        return "cat-aduana-clasif-prod";
    }

    @Override
    public Page<CatAduanaClasifProd> findAll(Pageable pageable) {
        return catAduanaClasifProdRepository.findAll(pageable);
    }



    @Override
    public CatAduanaClasifProd save(CatAduanaClasifProd entity) {
        return catAduanaClasifProdRepository.save(entity);
    }

    @Override
    public void deleteById(String id) {
        catAduanaClasifProdRepository.deleteById(Long.valueOf(id));
    }
}
