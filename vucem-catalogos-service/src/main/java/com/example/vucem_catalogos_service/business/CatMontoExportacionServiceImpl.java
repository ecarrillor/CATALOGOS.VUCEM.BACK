package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatMontoExportacionService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO;
import com.example.vucem_catalogos_service.model.dto.LeyendaTexto.CatLeyendaTextoResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatMonedaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatMontoExportacionRepository;
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

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class CatMontoExportacionServiceImpl extends AbstractCatalogService<CatMontoExportacion, CatMontoExportacionId> implements ICatMontoExportacionService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "razonSocial", "razonSocial",
            "monto", "monto"
    );

    @Autowired
    private ICatMontoExportacionRepository catMontoExportacionRepository;

    @Override
    public String getCatalogKey() {
        return "cat_monto_exportacion";
    }

    @Override
    public Class<CatMontoExportacion> getEntityClass() {
        return CatMontoExportacion.class;
    }


    @Override
    protected JpaRepository<CatMontoExportacion, CatMontoExportacionId> getRepository() {
        return catMontoExportacionRepository;
    }

    @Override
    protected Class<CatMontoExportacionId> getIdClass() {
        return CatMontoExportacionId.class;
    }


    @Override
    public PageResponseDTO<CatMontoExportacionDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = search.trim();
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "razonSocial"));

        Page<CatMontoExportacionDTO> page = catMontoExportacionRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatMontoExportacionDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public Page<CatMontoExportacion> findAll(
            String search,
            Map<String, String> filters,
            boolean includeSubcatalogs,
            Pageable pageable) {

        Sort validatedSort = buildValidatedSortFindAll(pageable.getSort());
        Pageable sortedPageable = validatedSort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), validatedSort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "razonSocial"));

        Specification<CatMontoExportacion> spec =
                GenericSearchSpecification.<CatMontoExportacion>searchInFields(
                        search,
                        List.of("razonSocial")
                ).and(
                        GenericFilterSpecification.byFilters(filters)
                ).and(
                        GenericDateRangeSpecification.byDateRange(filters, "fecIniVigencia")
                );

        return catMontoExportacionRepository.findAll(spec, sortedPageable);
    }

    private Sort buildValidatedSortFindAll(Sort incoming) {
        if (incoming == null || incoming.isUnsorted()) return Sort.unsorted();
        Sort.Order order = incoming.stream().findFirst().orElse(null);
        if (order == null) return Sort.unsorted();
        return SortValidator.buildSort(order.getProperty(), order.getDirection().name(), ALLOWED_SORT_COLUMNS);
    }

    @Override
    public CatMontoExportacionDTO findMontoExportacion(String id, LocalDate fecha) {
        return catMontoExportacionRepository.findByMontoExportacion(id, fecha)
                .orElseThrow(() -> new RuntimeException(
                        "CatMontoExportacion no encontrado para fecha=" + fecha + ", RFC=" + id));
    }

    @Override
    public CatMontoExportacionDTO update(String id, LocalDate fecha, CatMontoExportacionDTO dto) {
        CatMontoExportacionId pk = new CatMontoExportacionId();
        pk.setFecMontoExportacion(fecha);
        pk.setRfcExportador(id);

        CatMontoExportacion entity = catMontoExportacionRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException(
                        "CatMontoExportacion no encontrado para fecha=" + fecha + ", RFC=" + id));

        if (dto.getRazonSocial() != null) {
            entity.setRazonSocial(dto.getRazonSocial());
        }
        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }
        if (dto.getMonto() != null) {
            entity.setMonto(dto.getMonto());
        }

        CatMontoExportacion saved = catMontoExportacionRepository.save(entity);
        return CatMontoExportacionDTO.builder()
                .monto(saved.getMonto())
                .razonSocial(saved.getRazonSocial())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

}
