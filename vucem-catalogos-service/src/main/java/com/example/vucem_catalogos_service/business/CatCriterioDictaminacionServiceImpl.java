package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatCriterioDictaminacion;
import com.example.vucem_catalogos_service.persistence.repo.ICatCriterioDictaminacionRepository;
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
public class CatCriterioDictaminacionServiceImpl extends AbstractCatalogService<CatCriterioDictaminacion,Short>{
    @Autowired
    private ICatCriterioDictaminacionRepository catCriterioDictaminacionRepository;

    @Override
    public String getCatalogKey() {
        return "cat_criterio_dictaminacion";
    }

    @Override
    public Class<CatCriterioDictaminacion> getEntityClass() {
        return CatCriterioDictaminacion.class;
    }

    @Override
    protected JpaRepository<CatCriterioDictaminacion, Short> getRepository() {
        return catCriterioDictaminacionRepository;
    }

    @Override
    protected Class<Short> getIdClass() {
        return Short.class;
    }

    @Override
    public Page<CatCriterioDictaminacion> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable
    ) {

        Specification<CatCriterioDictaminacion> spec =
                GenericSearchSpecification.<CatCriterioDictaminacion>searchInFields(
                        search,
                        List.of("nombre")
                ).and(
                        GenericFilterSpecification.<CatCriterioDictaminacion>byFilters(filters)
                ).and(
                        (root, query, cb) -> cb.equal(root.get("blnActivo"), true)
                );

        return catCriterioDictaminacionRepository.findAll(spec, pageable);
    }
}
