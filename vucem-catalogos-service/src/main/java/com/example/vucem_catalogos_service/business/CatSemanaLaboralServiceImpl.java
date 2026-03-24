package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatRegimen;
import com.example.vucem_catalogos_service.model.entity.CatSemanaLaboral;
import com.example.vucem_catalogos_service.persistence.repo.ICatRegimenRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatSemanaLaboralRepository;
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
public class CatSemanaLaboralServiceImpl extends AbstractCatalogService<CatSemanaLaboral,Integer>{

    // NO se permite ordenar por: blnActivo
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",          "id",
            "descripcion", "descripcion"
    );

    @Autowired
    private ICatSemanaLaboralRepository catSemanaLaboralRepository;

    @Override
    public String getCatalogKey() {
        return "cat_semana_laboral";
    }

    @Override
    public Class<CatSemanaLaboral> getEntityClass() {
        return CatSemanaLaboral.class;
    }

    @Override
    protected JpaRepository<CatSemanaLaboral, Integer> getRepository() {
        return catSemanaLaboralRepository;
    }

    @Override
    protected Class<Integer> getIdClass() {
        return Integer.class;
    }


    @Override
    public Page<CatSemanaLaboral> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "descripcion"));

        Specification<CatSemanaLaboral> spec =
                GenericSearchSpecification.<CatSemanaLaboral>searchInFields(
                        search,
                        List.of("descripcion")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                );



        return catSemanaLaboralRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
