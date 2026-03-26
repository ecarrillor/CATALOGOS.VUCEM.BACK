package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdminVecinaService;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminVecina;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdminVecinaId;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdministrativa;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.persistence.repo.ICatEntidadRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdminVecinaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdministrativaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatUnidadAdminVecinaServiceImpl implements ICatUnidadAdminVecinaService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveUnidadAdministrativa", "id.cveUnidadAdministrativa",
            "cveEntidad", "id.cveEntidad",
            "nombreUnidadAdministrativa", "cveUnidadAdministrativa.nombre",
            "nombreEntidad", "cveEntidad.nombre"
    );

    @Autowired
    private ICatUnidadAdminVecinaRepository catUnidadAdminVecinaRepository;

    @Autowired
    private ICatUnidadAdministrativaRepository catUnidadAdministrativaRepository;

    @Autowired
    private ICatEntidadRepository catEntidadRepository;

    @Override
    public PageResponseDTO<CatUnidadAdminVecinaDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "cveUnidadAdministrativa.nombre"));

        Page<CatUnidadAdminVecinaDTO> page = catUnidadAdminVecinaRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatUnidadAdminVecinaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatUnidadAdminVecinaDTO findById(String cveUnidadAdministrativa, String cveEntidad) {
        return catUnidadAdminVecinaRepository
                .findByUnidadAdminVecinaDTO(cveUnidadAdministrativa, cveEntidad)
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdminVecina no encontrada con cveUnidadAdministrativa: "
                                + cveUnidadAdministrativa + " y cveEntidad: " + cveEntidad));
    }

    @Override
    public CatUnidadAdminVecinaDTO create(CatUnidadAdminVecinaDTO dto) {
        CatUnidadAdministrativa unidadAdmin = catUnidadAdministrativaRepository
                .findById(dto.getCveUnidadAdministrativa())
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdministrativa no encontrada: " + dto.getCveUnidadAdministrativa()));

        CatEntidad entidad = catEntidadRepository.findById(dto.getCveEntidad())
                .orElseThrow(() -> new RuntimeException(
                        "CatEntidad no encontrada: " + dto.getCveEntidad()));

        CatUnidadAdminVecinaId id = new CatUnidadAdminVecinaId();
        id.setCveUnidadAdministrativa(dto.getCveUnidadAdministrativa());
        id.setCveEntidad(dto.getCveEntidad());

        if (catUnidadAdminVecinaRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La Unidad Admin Vecina ya existe.");
        }

        CatUnidadAdminVecina entity = new CatUnidadAdminVecina();
        entity.setId(id);
        entity.setCveUnidadAdministrativa(unidadAdmin);
        entity.setCveEntidad(entidad);
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatUnidadAdminVecina saved = catUnidadAdminVecinaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatUnidadAdminVecinaDTO update(String cveUnidadAdministrativa, String cveEntidad,
                                          CatUnidadAdminVecinaDTO dto) {
        CatUnidadAdminVecinaId id = new CatUnidadAdminVecinaId();
        id.setCveUnidadAdministrativa(cveUnidadAdministrativa);
        id.setCveEntidad(cveEntidad);

        CatUnidadAdminVecina entity = catUnidadAdminVecinaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdminVecina no encontrada con cveUnidadAdministrativa: "
                                + cveUnidadAdministrativa + " y cveEntidad: " + cveEntidad));

        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());

        CatUnidadAdminVecina saved = catUnidadAdminVecinaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<SelectDTO> listUnidadAdministrativa() {
        return catUnidadAdministrativaRepository.findByBlnActivoTrue()
                .stream()
                .map(entity -> {
                    SelectDTO dto = new SelectDTO();
                    dto.setCve(entity.getCveUnidadAdministrativa());
                    dto.setNombre(entity.getNombre());
                    return dto;
                })
                .toList();
    }

    private CatUnidadAdminVecinaDTO mapToDTO(CatUnidadAdminVecina entity) {
        return CatUnidadAdminVecinaDTO.builder()
                .cveUnidadAdministrativa(entity.getId().getCveUnidadAdministrativa())
                .cveEntidad(entity.getId().getCveEntidad())
                .nombreUnidadAdministrativa(entity.getCveUnidadAdministrativa() != null
                        ? entity.getCveUnidadAdministrativa().getNombre() : null)
                .nombreEntidad(entity.getCveEntidad() != null
                        ? entity.getCveEntidad().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
