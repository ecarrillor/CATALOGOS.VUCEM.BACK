package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPaisRestriccionTtraService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatPaisRestriccionTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPaisRestriccionTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPaisRestriccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

@Service
@Transactional
public class CatPaisRestriccionTtraServiceImpl implements ICatPaisRestriccionTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id",
            "nombreTipoTramite", "tt.descModalidad",
            "cvePais", "p.cvePais",
            "ideTipoRestriccionPaisTtra", "e.ideTipoRestriccionPaisTtra"
    );

    @Autowired
    private ICatPaisRestriccionTtraRepository catPaisRestriccionTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Autowired
    private ICatPaisRepository catPaisRepository;

    @Override
    public PageResponseDTO<CatPaisRestriccionTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        Page<CatPaisRestriccionTtraDTO> page = catPaisRestriccionTtraRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatPaisRestriccionTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatPaisRestriccionTtraDTO findById(Integer id) {
        return catPaisRestriccionTtraRepository.findByPaisRestriccionTtraDTO(id);
    }

    @Override
    public CatPaisRestriccionTtraDTO create(CatPaisRestriccionTtraDTO dto) {
        if (catPaisRestriccionTtraRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }
        CatPaisRestriccionTtra entity = new CatPaisRestriccionTtra();

        entity.setId(dto.getId());
        entity.setIdeTipoRestriccionPaisTtra(dto.getIdeTipoRestriccionPaisTtra());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo() != null ? dto.getBlnActivo() : true);

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        if (dto.getCvePais() != null) {
            entity.setCvePais(
                    catPaisRepository.findById(dto.getCvePais())
                            .orElseThrow(() -> new RuntimeException("País no encontrado: " + dto.getCvePais()))
            );
        }

        CatPaisRestriccionTtra saved = catPaisRestriccionTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatPaisRestriccionTtraDTO update(Integer id, CatPaisRestriccionTtraDTO dto) {
        CatPaisRestriccionTtra entity = catPaisRestriccionTtraRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("País restricción trámite no encontrado: " + id));

        if (dto.getIdeTipoRestriccionPaisTtra() != null) {
            entity.setIdeTipoRestriccionPaisTtra(dto.getIdeTipoRestriccionPaisTtra());
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
                            .orElseThrow(() ->
                                    new RuntimeException("Tipo de trámite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        if (dto.getCvePais() != null) {
            entity.setCvePais(
                    catPaisRepository.findById(dto.getCvePais())
                            .orElseThrow(() ->
                                    new RuntimeException("País no encontrado: " + dto.getCvePais()))
            );
        }

        CatPaisRestriccionTtra updated = catPaisRestriccionTtraRepository.save(entity);
        return mapToDTO(updated);
    }

    private CatPaisRestriccionTtraDTO mapToDTO(CatPaisRestriccionTtra entity) {
        return CatPaisRestriccionTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite().getId())
                .nombreTipoTramite(entity.getIdTipoTramite().getDescModalidad())
                .cvePais(entity.getCvePais() != null ? entity.getCvePais().getCvePais() : null)
                .nombrePais(entity.getCvePais() != null ? entity.getCvePais().getNombre() : null)
                .ideTipoRestriccionPaisTtra(entity.getIdeTipoRestriccionPaisTtra())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
