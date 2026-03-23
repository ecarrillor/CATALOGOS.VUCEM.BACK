package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTratamientoEspecialService;
import com.example.vucem_catalogos_service.model.dto.CatTratamientoEspecialDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratamientoEspecial;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratamientoEspecialRepository;
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
public class CatTratamientoEspecialServiceImpl implements ICatTratamientoEspecialService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id",
            "nombre", "nombre",
            "ideClasifTratamiento", "ideClasifTratamiento"
    );

    @Autowired
    private ICatTratamientoEspecialRepository catTratamientoEspecialRepository;

    @Override
    public PageResponseDTO<CatTratamientoEspecialDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        Page<CatTratamientoEspecialDTO> page = catTratamientoEspecialRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatTratamientoEspecialDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTratamientoEspecialDTO findById(Short id) {
        return catTratamientoEspecialRepository.findByTratamientoEspecialDTO(id)
                .orElseThrow(() -> new RuntimeException("CatTratamientoEspecial no encontrado con id: " + id));
    }

    @Override
    public CatTratamientoEspecialDTO create(CatTratamientoEspecialDTO dto) {
        if (catTratamientoEspecialRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }
        CatTratamientoEspecial entity = new CatTratamientoEspecial();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeClasifTratamiento(dto.getIdeClasifTratamiento());

        CatTratamientoEspecial saved = catTratamientoEspecialRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTratamientoEspecialDTO update(Short id, CatTratamientoEspecialDTO dto) {
        CatTratamientoEspecial entity = catTratamientoEspecialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatTratamientoEspecial no encontrado con id: " + id));

        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
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
        if (dto.getIdeClasifTratamiento() != null) {
            entity.setIdeClasifTratamiento(dto.getIdeClasifTratamiento());
        }

        CatTratamientoEspecial saved = catTratamientoEspecialRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatTratamientoEspecialDTO mapToDTO(CatTratamientoEspecial entity) {
        return CatTratamientoEspecialDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideClasifTratamiento(entity.getIdeClasifTratamiento())
                .build();
    }
}
