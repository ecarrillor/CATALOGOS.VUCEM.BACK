package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdminAduanaService;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminAduanaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminAduana;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminAduanaId;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.persistence.repo.ICatAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdminAduanaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdministrativaRepository;
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
public class CatUnidadAdminAduanaServiceImpl implements ICatUnidadAdminAduanaService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveUnidadAdministrativa", "id.cveUnidadAdministrativa",
            "cveAduana", "id.cveAduana",
            "nombreUnidadAdministrativa", "cveUnidadAdministrativa.nombre",
            "nombreAduana", "cveAduana.nombre"
    );

    @Autowired
    private ICatUnidadAdminAduanaRepository catUnidadAdminAduanaRepository;

    @Autowired
    private ICatUnidadAdministrativaRepository catUnidadAdministrativaRepository;

    @Autowired
    private ICatAduanaRepository catAduanaRepository;

    @Override
    public PageResponseDTO<CatUnidadAdminAduanaDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id.cveUnidadAdministrativa"));

        Page<CatUnidadAdminAduanaDTO> page = catUnidadAdminAduanaRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatUnidadAdminAduanaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatUnidadAdminAduanaDTO findById(String cveUnidadAdministrativa, String cveAduana) {
        return catUnidadAdminAduanaRepository.findByUnidadAdminAduanaDTO(cveUnidadAdministrativa, cveAduana)
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdminAduana no encontrada con cveUnidadAdministrativa: "
                                + cveUnidadAdministrativa + " y cveAduana: " + cveAduana));
    }

    @Override
    public CatUnidadAdminAduanaDTO create(CatUnidadAdminAduanaDTO dto) {
        CatUnidadAdminAduanaId id = new CatUnidadAdminAduanaId();
        id.setCveUnidadAdministrativa(dto.getCveUnidadAdministrativa());
        id.setCveAduana(dto.getCveAduana());

        CatUnidadAdminAduana entity = new CatUnidadAdminAduana();
        entity.setId(id);
        entity.setCveUnidadAdministrativa(
                catUnidadAdministrativaRepository.findById(dto.getCveUnidadAdministrativa())
                        .orElseThrow(() -> new RuntimeException(
                                "CatUnidadAdministrativa no encontrada: " + dto.getCveUnidadAdministrativa()))
        );
        entity.setCveAduana(
                catAduanaRepository.findById(dto.getCveAduana())
                        .orElseThrow(() -> new RuntimeException("CatAduana no encontrada: " + dto.getCveAduana()))
        );
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatUnidadAdminAduana saved = catUnidadAdminAduanaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatUnidadAdminAduanaDTO update(String cveUnidadAdministrativa, String cveAduana, CatUnidadAdminAduanaDTO dto) {
        CatUnidadAdminAduanaId id = new CatUnidadAdminAduanaId();
        id.setCveUnidadAdministrativa(cveUnidadAdministrativa);
        id.setCveAduana(cveAduana);

        CatUnidadAdminAduana entity = catUnidadAdminAduanaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdminAduana no encontrada con cveUnidadAdministrativa: "
                                + cveUnidadAdministrativa + " y cveAduana: " + cveAduana));

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        CatUnidadAdminAduana saved = catUnidadAdminAduanaRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatUnidadAdminAduanaDTO mapToDTO(CatUnidadAdminAduana entity) {
        return CatUnidadAdminAduanaDTO.builder()
                .cveUnidadAdministrativa(entity.getId().getCveUnidadAdministrativa())
                .cveAduana(entity.getId().getCveAduana())
                .nombreUnidadAdministrativa(entity.getCveUnidadAdministrativa() != null
                        ? entity.getCveUnidadAdministrativa().getNombre() : null)
                .nombreAduana(entity.getCveAduana() != null
                        ? entity.getCveAduana().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
