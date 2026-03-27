package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatLenguaje;
import com.example.vucem_catalogos_service.persistence.repo.ICatLenguajeRepository;
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
import java.util.Set;

@Transactional
@Service
public class CatLenguajeServiceImpl extends AbstractCatalogService<CatLenguaje,String>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveLenguaje", "cveLenguaje",
            "nombre",      "nombre"
    );

    private static final String DEFAULT_SORT_KEY = "cveLenguaje";

    private static final Set<String> TEXT_COLUMNS = Set.of(
    );

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

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre"));

        Specification<CatLenguaje> spec =
                GenericSearchSpecification.<CatLenguaje>searchInFields(
                        search,
                        List.of("cveLenguaje", "nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catLenguajeRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) {
            return Sort.by(Sort.Direction.ASC, ALLOWED_SORT_COLUMNS.get(DEFAULT_SORT_KEY));
        }

        Sort.Order order = incoming.stream().findFirst().orElse(null);

        if (order == null) {
            return Sort.by(Sort.Direction.ASC, ALLOWED_SORT_COLUMNS.get(DEFAULT_SORT_KEY));
        }

        return SortValidator.buildSort(
                order.getProperty(),
                order.getDirection().name(),
                ALLOWED_SORT_COLUMNS,
                TEXT_COLUMNS,
                DEFAULT_SORT_KEY
        );
    }
}
