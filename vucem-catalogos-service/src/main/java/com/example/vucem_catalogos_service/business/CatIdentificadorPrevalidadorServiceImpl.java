package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatIdentificadorPrevalidador;
import com.example.vucem_catalogos_service.persistence.repo.ICatIdentificadorPrevalidadorRepository;
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

@Transactional
@Service
public class CatIdentificadorPrevalidadorServiceImpl extends AbstractCatalogService<CatIdentificadorPrevalidador, Long>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo, blnUtilizado
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",                      "id",
            "caracterIdentificacion",  "caracterIdentificacion"
    );

    @Autowired
    private ICatIdentificadorPrevalidadorRepository catIdentificadorPrevalidadorRepository;

    @Override
    public String getCatalogKey() {
        return "cat_identificador_prevalidor";
    }

    @Override
    public Class<CatIdentificadorPrevalidador> getEntityClass() {
        return CatIdentificadorPrevalidador.class;
    }

    @Override
    protected JpaRepository<CatIdentificadorPrevalidador, Long> getRepository() {
        return catIdentificadorPrevalidadorRepository;
    }

    @Override
    protected Class<Long> getIdClass() {
        return Long.class;
    }


    @Override
    public Page<CatIdentificadorPrevalidador> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "caracterIdentificacion"));

        Specification<CatIdentificadorPrevalidador> spec =
                GenericSearchSpecification.<CatIdentificadorPrevalidador>searchInFields(
                        search,
                        List.of("caracterIdentificacion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catIdentificadorPrevalidadorRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
