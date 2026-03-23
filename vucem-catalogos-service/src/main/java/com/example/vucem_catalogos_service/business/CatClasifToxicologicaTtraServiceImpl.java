package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatClasifToxicologicaTtraService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatClasifToxicologicaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatClasifToxicologicaTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatClasifToxicologicaTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
@Transactional
public class CatClasifToxicologicaTtraServiceImpl implements ICatClasifToxicologicaTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "e.id",
            "idTipoTramite", "t.id",
            "nombreTipoTramite", "t.descModalidad",
            "descClasifToxicologica", "e.descClasifToxicologica"
    );

    @Autowired
    private ICatClasifToxicologicaTtraRepository catClasifToxicologicaTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatClasifToxicologicaTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatClasifToxicologicaTtraDTO> page = catClasifToxicologicaTtraRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatClasifToxicologicaTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatClasifToxicologicaTtraDTO findById(Short id) {
        return catClasifToxicologicaTtraRepository.findByClasifToxicologicaTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatClasifToxicologicaTtra no encontrada con id: " + id));
    }

    @Override
    public CatClasifToxicologicaTtraDTO create(CatClasifToxicologicaTtraDTO dto) {
        if (catClasifToxicologicaTtraRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }
        CatClasifToxicologicaTtra entity = new CatClasifToxicologicaTtra();
        entity.setId(dto.getId());
        entity.setDescClasifToxicologica(dto.getDescClasifToxicologica());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        CatClasifToxicologicaTtra saved = catClasifToxicologicaTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatClasifToxicologicaTtraDTO update(Short id, CatClasifToxicologicaTtraDTO dto) {
        CatClasifToxicologicaTtra entity = catClasifToxicologicaTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatClasifToxicologicaTtra no encontrada con id: " + id));

        if (dto.getDescClasifToxicologica() != null) {
            entity.setDescClasifToxicologica(dto.getDescClasifToxicologica());
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

        CatClasifToxicologicaTtra saved = catClasifToxicologicaTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatClasifToxicologicaTtraDTO mapToDTO(CatClasifToxicologicaTtra entity) {
        return CatClasifToxicologicaTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getDescModalidad() : null)
                .descClasifToxicologica(entity.getDescClasifToxicologica())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
