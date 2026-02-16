package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatLenguaje;
import com.example.vucem_catalogos_service.persistence.repo.ICatLenguajeRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CatLenguajeServiceImpl extends AbstractCatalogService<CatLenguaje,String>{
    @Autowired
    private ICatLenguajeRepository catLenguajeRepository;

    @Override
    public String getCatalogKey() {
        return "cat_lenguaje";
    }

    @Override
    public Class<CatLenguaje> getEntityClass() {
        return CatLenguaje.class;
    }

    @Override
    protected JpaRepository<CatLenguaje, String> getRepository() {
        return catLenguajeRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatLenguaje> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatLenguaje> spec =
                GenericSearchSpecification.<CatLenguaje>searchInFields(
                        search,
                        List.of("nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catLenguajeRepository.findAll(spec, pageable);
    }
}
