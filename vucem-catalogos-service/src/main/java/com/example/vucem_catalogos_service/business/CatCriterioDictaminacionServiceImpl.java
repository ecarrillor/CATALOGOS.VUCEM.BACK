package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatCriterioDictaminacion;
import com.example.vucem_catalogos_service.persistence.repo.ICatCriterioDictaminacionRepository;
import com.example.vucem_catalogos_service.persistence.specification.GenericDateRangeSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericFilterSpecification;
import com.example.vucem_catalogos_service.persistence.specification.GenericSearchSpecification;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatCriterioDictaminacionServiceImpl extends AbstractCatalogService<CatCriterioDictaminacion,Short>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",     "id",
            "nombre", "nombre"
    );

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

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre"));

        Specification<CatCriterioDictaminacion> spec =
                GenericSearchSpecification.<CatCriterioDictaminacion>searchInFields(
                        search,
                        List.of("nombre")
                ).and(
                        GenericFilterSpecification.<CatCriterioDictaminacion>byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catCriterioDictaminacionRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
