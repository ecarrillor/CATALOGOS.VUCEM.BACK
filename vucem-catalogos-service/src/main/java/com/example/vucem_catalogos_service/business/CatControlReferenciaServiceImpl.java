package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatCondicionUso;
import com.example.vucem_catalogos_service.model.entity.CatControlReferencia;
import com.example.vucem_catalogos_service.persistence.repo.ICatControlReferenciaRepository;
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

@Service
@Transactional
public class CatControlReferenciaServiceImpl extends AbstractCatalogService<CatControlReferencia,Integer>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",                     "id",
            "ideTipoPresentacion",    "ideTipoPresentacion",
            "ideSubtipoPresentacion", "ideSubtipoPresentacion",
            "minimo",                 "minimo",
            "maximo",                 "maximo",
            "cantidadPresentacion",   "cantidadPresentacion",
            "tamanioMuestra",         "tamanioMuestra"
    );

    private static final String DEFAULT_SORT_KEY = "id";

    private static final Set<String> TEXT_COLUMNS = Set.of(

    );

    @Autowired
    private ICatControlReferenciaRepository catControlReferenciaRepository;

    @Override
    public String getCatalogKey() {
        return "cat-ctrlReferencia";
    }

    @Override
    public Class<CatControlReferencia> getEntityClass() {
        return CatControlReferencia.class;
    }

    @Override
    protected JpaRepository<CatControlReferencia, Integer> getRepository() {
        return catControlReferenciaRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatControlReferencia> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "ideTipoPresentacion"));

        Specification<CatControlReferencia> spec =
                GenericSearchSpecification.<CatControlReferencia>searchInFields(
                        search,
                        List.of("ideTipoPresentacion", "ideSubtipoPresentacion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catControlReferenciaRepository.findAll(spec, sortedPageable);
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
