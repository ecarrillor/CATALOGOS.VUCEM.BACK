package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatGenero;
import com.example.vucem_catalogos_service.persistence.repo.ICatGeneroRepository;
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
public class CatGeneroServiceImpl extends AbstractCatalogService<CatGenero,Integer>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",         "id",
            "descGenero", "descGenero"
    );

    @Autowired
    private ICatGeneroRepository catGeneroRepository;

    @Override
    public String getCatalogKey() {
        return "cat_genero";
    }

    @Override
    public Class<CatGenero> getEntityClass() {
        return CatGenero.class;
    }

    @Override
    protected JpaRepository<CatGenero, Integer> getRepository() {
        return catGeneroRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatGenero> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descGenero"));

        Specification<CatGenero> spec =
                GenericSearchSpecification.<CatGenero>searchInFields(
                        search,
                        List.of("descGenero")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catGeneroRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
