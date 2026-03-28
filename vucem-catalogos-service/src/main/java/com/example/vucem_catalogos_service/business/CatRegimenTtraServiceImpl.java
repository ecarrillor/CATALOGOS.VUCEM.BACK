package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatRegimenTtraService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatRegimenTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatRegimen;
import com.example.vucem_catalogos_service.model.entity.CatRegimenTtra;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatRegimenRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatRegimenTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatRegimenTtraServiceImpl implements ICatRegimenTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id",
            "cveRegimen", "cveRegimen.cveRegimen",
            "nombreRegimen", "cveRegimen.nombre",
            "nombreTipoTramite", "e.idTipoTramite.descSubservicio"
    );

    @Autowired
    private ICatRegimenTtraRepository catRegimenTtraRepository;

    @Autowired
    private ICatRegimenRepository catRegimenRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatRegimenTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();

            if (s.equals("activo")) {
                activo = true;
            }
            else if (s.equals("inactivo")) {
                activo = false;
            }
            else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        Page<CatRegimenTtraDTO> page = catRegimenTtraRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatRegimenTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatRegimenTtraDTO findById(Short id) {
        return catRegimenTtraRepository.findByRegimenTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatRegimenTtra no encontrado con id: " + id));
    }

    @Override
    public CatRegimenTtraDTO create(CatRegimenTtraDTO dto) {
        CatRegimenTtra entity = new CatRegimenTtra();
        entity.setId(dto.getId());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCveRegimen() != null) {
            CatRegimen regimen = catRegimenRepository.findById(dto.getCveRegimen())
                    .orElseThrow(() -> new RuntimeException(
                            "CatRegimen no encontrado: " + dto.getCveRegimen()));
            entity.setCveRegimen(regimen);
        }

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTipoTramite no encontrado con id: " + dto.getIdTipoTramite()));
            entity.setIdTipoTramite(tipoTramite);
        }

        CatRegimenTtra saved = catRegimenTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatRegimenTtraDTO update(Short id, CatRegimenTtraDTO dto) {
        CatRegimenTtra entity = catRegimenTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatRegimenTtra no encontrado con id: " + id));

        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCveRegimen() != null) {
            CatRegimen regimen = catRegimenRepository.findById(dto.getCveRegimen())
                    .orElseThrow(() -> new RuntimeException(
                            "CatRegimen no encontrado: " + dto.getCveRegimen()));
            entity.setCveRegimen(regimen);
        }

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTipoTramite no encontrado con id: " + dto.getIdTipoTramite()));
            entity.setIdTipoTramite(tipoTramite);
        }

        CatRegimenTtra saved = catRegimenTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<SelectDTO>  listTipoTramite() {
        return catTipoTramiteRepository.findByBlnActivoTrue()
                .stream()
                .map(entity -> {
                    SelectDTO dto = new SelectDTO();
                    dto.setId(entity.getId().longValue());
                    dto.setNombre(entity.getDescSubservicio());
                    return dto;
                })
                .toList();
    }

    @Override
    public List<SelectDTO> listRegimen() {
        return catRegimenRepository.findByBlnActivoTrue()
                .stream()
                .map(entity -> {
                    SelectDTO dto = new SelectDTO();
                    dto.setCve(entity.getCveRegimen());
                    dto.setNombre(entity.getNombre());
                    return dto;
                })
                .toList();
    }

    private CatRegimenTtraDTO mapToDTO(CatRegimenTtra entity) {
        return CatRegimenTtraDTO.builder()
                .id(entity.getId())
                .cveRegimen(entity.getCveRegimen() != null ? entity.getCveRegimen().getCveRegimen() : null)
                .nombreRegimen(entity.getCveRegimen() != null ? entity.getCveRegimen().getNombre() : null)
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
