package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatTipoDictamen;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoDictamenRepository;
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
public class CatTipoDictamanServiceImpl extends AbstractCatalogService<CatTipoDictamen,Long>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",     "id",
            "nombre", "nombre",
            "orden",  "orden"
    );

    @Autowired
    private ICatTipoDictamenRepository catTipoDictamanRepository;

    @Override
    public String getCatalogKey() {
        return "cat_tipo_dictamen";
    }

    @Override
    public Class<CatTipoDictamen> getEntityClass() {
        return CatTipoDictamen.class;
    }

    @Override
    protected JpaRepository<CatTipoDictamen, Long> getRepository() {
        return catTipoDictamanRepository;
    }

    @Override
    protected Class<Long> getIdClass() {
        return Long.class;
    }


    @Override
    public Page<CatTipoDictamen> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre"));

        Specification<CatTipoDictamen> spec =
                GenericSearchSpecification.<CatTipoDictamen>searchInFields(
                        search,
                        List.of("nombre")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catTipoDictamanRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
