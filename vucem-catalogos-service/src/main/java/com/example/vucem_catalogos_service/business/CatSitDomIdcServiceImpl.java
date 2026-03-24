package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatSitDomIdc;
import com.example.vucem_catalogos_service.persistence.repo.ICatSitDomIdcRepository;
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
public class CatSitDomIdcServiceImpl extends AbstractCatalogService<CatSitDomIdc,Long>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",          "id",
            "descripcion", "descripcion"
    );

    @Autowired
    private ICatSitDomIdcRepository catSitDomIdcRepository;

    @Override
    public String getCatalogKey() {
        return "cat_sit_dom_idc";
    }

    @Override
    public Class<CatSitDomIdc> getEntityClass() {
        return CatSitDomIdc.class;
    }

    @Override
    protected JpaRepository<CatSitDomIdc, Long> getRepository() {
        return catSitDomIdcRepository;
    }

    @Override
    protected Class<Long> getIdClass() {
        return Long.class;
    }


    @Override
    public Page<CatSitDomIdc> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descripcion"));

        Specification<CatSitDomIdc> spec =
                GenericSearchSpecification.<CatSitDomIdc>searchInFields(
                        search,
                        List.of( "descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catSitDomIdcRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
