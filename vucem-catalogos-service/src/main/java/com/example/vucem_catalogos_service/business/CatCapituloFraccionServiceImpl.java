package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCapituloFraccion;
import com.example.vucem_catalogos_service.persistence.repo.ICatCapituloFraccionRepository;
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

@Service
@Transactional
public class CatCapituloFraccionServiceImpl extends AbstractCatalogService<CatCapituloFraccion, String> {
    @Autowired
    private ICatCapituloFraccionRepository catCapituloFraccionRepository;


    @Override
    public String getCatalogKey() {
        return "cat-capFraccion";
    }

    @Override
    public Class<CatCapituloFraccion> getEntityClass() {
        return CatCapituloFraccion.class;
    }

    @Override
    protected JpaRepository<CatCapituloFraccion, String> getRepository() {
        return catCapituloFraccionRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatCapituloFraccion> findAll(
            String search,
            Map<String, String> filters,
    boolean includeSubcatalogs,
    Pageable pageable) {

        Specification<CatCapituloFraccion> spec =
                GenericSearchSpecification.<CatCapituloFraccion>searchInFields(
                        search,
                        List.of("nombre", "fecIniVigencia")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catCapituloFraccionRepository.findAll(spec, pageable);
    }
}
