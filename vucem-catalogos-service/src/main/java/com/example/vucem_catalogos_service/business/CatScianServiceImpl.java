package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatScianService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatScianDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatScian;
import com.example.vucem_catalogos_service.persistence.repo.ICatScianRepository;
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
public class CatScianServiceImpl implements ICatScianService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveScian", "cveScian",
            "descScian", "descScian",
            "giro", "giro"
    );

    @Autowired
    private ICatScianRepository catScianRepository;

    @Override
    public PageResponseDTO<CatScianDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "cveScian"));

        Page<CatScianDTO> page = catScianRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatScianDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatScianDTO findById(String cveScian) {
        return catScianRepository.findByScianDTO(cveScian)
                .orElseThrow(() -> new RuntimeException("CatScian no encontrado con clave: " + cveScian));
    }

    @Override
    public CatScianDTO create(CatScianDTO dto) {
        if (catScianRepository.existsById(dto.getCveScian())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }
        CatScian entity = new CatScian();
        entity.setCveScian(dto.getCveScian());
        entity.setDescScian(dto.getDescScian());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setGiro(dto.getGiro());

        CatScian saved = catScianRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatScianDTO update(String cveScian, CatScianDTO dto) {
        CatScian entity = catScianRepository.findById(cveScian)
                .orElseThrow(() -> new RuntimeException("CatScian no encontrado con clave: " + cveScian));

        if (dto.getDescScian() != null) {
            entity.setDescScian(dto.getDescScian());
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
        if (dto.getGiro() != null) {
            entity.setGiro(dto.getGiro());
        }

        CatScian saved = catScianRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatScianDTO mapToDTO(CatScian entity) {
        return CatScianDTO.builder()
                .cveScian(entity.getCveScian())
                .descScian(entity.getDescScian())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .giro(entity.getGiro())
                .build();
    }
}
