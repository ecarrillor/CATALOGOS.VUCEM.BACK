package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatUsoMercanciaTtraService;
import com.example.vucem_catalogos_service.model.dto.CatUsoMercanciaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatUsoMercanciaTtra;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUsoMercanciaTtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class CatUsoMercanciaTtraServiceImpl implements ICatUsoMercanciaTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id",
            "idTipoTramite", "idTipoTramite.id",
            "nombreTipoTramite", "idTipoTramite.descModalidad",
            "descUsoMercancia", "descUsoMercancia"
    );

    @Autowired
    private ICatUsoMercanciaTtraRepository catUsoMercanciaTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatUsoMercanciaTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = "%" + s + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        Page<CatUsoMercanciaTtraDTO> page = catUsoMercanciaTtraRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatUsoMercanciaTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatUsoMercanciaTtraDTO findById(Short id) {
        return catUsoMercanciaTtraRepository.findByUsoMercanciaTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatUsoMercanciaTtra no encontrado con id: " + id));
    }

    @Override
    public CatUsoMercanciaTtraDTO create(CatUsoMercanciaTtraDTO dto) {
        CatUsoMercanciaTtra entity = new CatUsoMercanciaTtra();
        entity.setId(dto.getId());
        entity.setDescUsoMercancia(dto.getDescUsoMercancia());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        CatUsoMercanciaTtra saved = catUsoMercanciaTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatUsoMercanciaTtraDTO update(Short id, CatUsoMercanciaTtraDTO dto) {
        CatUsoMercanciaTtra entity = catUsoMercanciaTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatUsoMercanciaTtra no encontrado con id: " + id));

        if (dto.getDescUsoMercancia() != null) {
            entity.setDescUsoMercancia(dto.getDescUsoMercancia());
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
        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        CatUsoMercanciaTtra saved = catUsoMercanciaTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatUsoMercanciaTtraDTO mapToDTO(CatUsoMercanciaTtra entity) {
        return CatUsoMercanciaTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getNombre() : null)
                .descUsoMercancia(entity.getDescUsoMercancia())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
