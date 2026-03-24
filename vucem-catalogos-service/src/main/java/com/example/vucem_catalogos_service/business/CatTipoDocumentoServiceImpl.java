package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.entity.CatTipoDocumento;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoDocumentoRepository;
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
public class CatTipoDocumentoServiceImpl extends AbstractCatalogService<CatTipoDocumento,Short>{

    // NO se permite ordenar por: fecIniVigencia, fecFinVigencia, blnActivo, fecCaptura
    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id",                       "id",
            "nombre",                   "nombre",
            "ideRangoResolucionImagen", "ideRangoResolucionImagen",
            "tamanioMaximo",            "tamanioMaximo"
    );

    @Autowired
    private ICatTipoDocumentoRepository catTipoDocumentoRepository;

    @Override
    public String getCatalogKey() {
        return "cat-tipoDocumento";
    }

    @Override
    public Class<CatTipoDocumento> getEntityClass() {
        return CatTipoDocumento.class;
    }

    @Override
    protected JpaRepository<CatTipoDocumento, Short> getRepository() {
        return catTipoDocumentoRepository;
    }

    @Override
    protected Class<Short> getIdClass() {
        return Short.class;
    }


    @Override
    public Page<CatTipoDocumento> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSort(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre"));

        Specification<CatTipoDocumento> spec =
                GenericSearchSpecification.<CatTipoDocumento>searchInFields(
                        search,
                        List.of("nombre", "ideRangoResolucionImagen")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catTipoDocumentoRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSort(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }
}
