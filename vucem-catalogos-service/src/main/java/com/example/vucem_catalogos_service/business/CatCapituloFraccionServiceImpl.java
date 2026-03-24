package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatCapituloFraccion;
import com.example.vucem_catalogos_service.persistence.repo.ICatCapituloFraccionRepository;
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
public class CatCapituloFraccionServiceImpl extends AbstractCatalogService<CatCapituloFraccion, String> {

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveCapituloFraccion", "cveCapituloFraccion",
            "nombre",              "nombre"
    );

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

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre"));

        Specification<CatCapituloFraccion> spec =
                GenericSearchSpecification.<CatCapituloFraccion>searchInFields(
                        search,
                        List.of("cveCapituloFraccion", "nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catCapituloFraccionRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
