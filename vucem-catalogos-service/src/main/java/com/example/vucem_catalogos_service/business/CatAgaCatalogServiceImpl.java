package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatAga;
import com.example.vucem_catalogos_service.persistence.repo.ICatAgaRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatAgaCatalogServiceImpl extends AbstractCatalogService<CatAga, String> {

    @Autowired
    private ICatAgaRepository catAgaRepository;

    @Override
    public String getCatalogKey() {
        return "cat-aga";
    }

    @Override
    public Class<CatAga> getEntityClass() {
        return CatAga.class;
    }

    @Override
    protected JpaRepository<CatAga, String> getRepository() {
        return catAgaRepository;
    }

    @Override
    public Page<CatAga> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable
    ) {

        Specification<CatAga> spec =
                GenericSearchSpecification.<CatAga>searchInFields(
                        search,
                        List.of("cveParametro", "descripcion", "valor")
                ).and(
                        GenericFilterSpecification.<CatAga>byFilters(filters)
                ).and(
                        (root, query, cb) -> cb.equal(root.get("blnActivo"), true)
                );

        return catAgaRepository.findAll(spec, pageable);
    }


}