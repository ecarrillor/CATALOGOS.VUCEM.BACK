package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatAga;
import com.example.vucem_catalogos_service.model.entity.CatBanco;
import com.example.vucem_catalogos_service.persistence.repo.ICatAgaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatBancoRepository;
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
public class CatBancoServiceImpl extends AbstractCatalogService<CatBanco, String>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo, fecCaptura
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveBanco", "cveBanco",
            "nombre",   "nombre"
    );

    @Autowired
    private ICatBancoRepository catBancoRepository;

    @Override
    public String getCatalogKey() {
        return "cat-banco";
    }

    @Override
    public Class<CatBanco> getEntityClass() {
        return CatBanco.class;
    }

    @Override
    protected JpaRepository<CatBanco, String> getRepository() {
        return catBancoRepository;
    }

    @Override
    protected Class<String> getIdClass() {
        return String.class;
    }

    @Override
    public Page<CatBanco> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable
    ) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre"));

        Specification<CatBanco> spec =
                GenericSearchSpecification.<CatBanco>searchInFields(
                        search,
                        List.of("cveBanco", "nombre")
                ).and(
                        GenericFilterSpecification.<CatBanco>byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                ).and(
                        (root, query, cb) -> cb.equal(root.get("blnActivo"), true)
                );

        return catBancoRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
