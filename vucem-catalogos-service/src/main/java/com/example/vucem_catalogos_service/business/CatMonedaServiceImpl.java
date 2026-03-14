package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatMoneda;
import com.example.vucem_catalogos_service.persistence.repo.ICatMonedaRepository;
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
public class CatMonedaServiceImpl extends AbstractCatalogService<CatMoneda, String>{
    @Autowired
    private ICatMonedaRepository catMonedaRepository;

    @Override
    public String getCatalogKey() {
        return "cat_moneda";
    }

    @Override
    public Class<CatMoneda> getEntityClass() {
        return CatMoneda.class;
    }

    @Override
    protected JpaRepository<CatMoneda, String> getRepository() {
        return catMonedaRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }


    @Override
    public Page<CatMoneda> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Specification<CatMoneda> spec =
                GenericSearchSpecification.<CatMoneda>searchInFields(
                        search,
                        List.of("cveMoneda","nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catMonedaRepository.findAll(spec, pageable);
    }
}
