package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatSectorProsecService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatSectorProsecDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatSectorProsec;
import com.example.vucem_catalogos_service.persistence.repo.ICatSectorProsecRepository;
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
public class CatSectorProsecServiceImpl implements ICatSectorProsecService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveSectorProsec", "cveSectorProsec",
            "nombre", "nombre",
            "blnProductorIndirecto", "blnProductorIndirecto",
            "blnAmpliacionMercancias", "blnAmpliacionMercancias"
    );

    @Autowired
    private ICatSectorProsecRepository catSectorProsecRepository;

    @Override
    public PageResponseDTO<CatSectorProsecDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "nombre"));

        Page<CatSectorProsecDTO> page = catSectorProsecRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatSectorProsecDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatSectorProsecDTO findById(String cveSectorProsec) {
        return catSectorProsecRepository.findBySectorProsecDTO(cveSectorProsec)
                .orElseThrow(() -> new RuntimeException("CatSectorProsec no encontrado con clave: " + cveSectorProsec));
    }

    @Override
    public CatSectorProsecDTO create(CatSectorProsecDTO dto) {

        if (catSectorProsecRepository.existsById(dto.getCveSectorProsec())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }
        CatSectorProsec entity = new CatSectorProsec();
        entity.setCveSectorProsec(dto.getCveSectorProsec());
        entity.setNombre(dto.getNombre());
        entity.setBlnProductorIndirecto(dto.getBlnProductorIndirecto());
        entity.setBlnAmpliacionMercancias(dto.getBlnAmpliacionMercancias());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatSectorProsec saved = catSectorProsecRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatSectorProsecDTO update(String cveSectorProsec, CatSectorProsecDTO dto) {
        CatSectorProsec entity = catSectorProsecRepository.findById(cveSectorProsec)
                .orElseThrow(() -> new RuntimeException("CatSectorProsec no encontrado con clave: " + cveSectorProsec));

        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }
        if (dto.getBlnProductorIndirecto() != null) {
            entity.setBlnProductorIndirecto(dto.getBlnProductorIndirecto());
        }
        if (dto.getBlnAmpliacionMercancias() != null) {
            entity.setBlnAmpliacionMercancias(dto.getBlnAmpliacionMercancias());
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

        CatSectorProsec saved = catSectorProsecRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatSectorProsecDTO mapToDTO(CatSectorProsec entity) {
        return CatSectorProsecDTO.builder()
                .cveSectorProsec(entity.getCveSectorProsec())
                .nombre(entity.getNombre())
                .blnProductorIndirecto(entity.getBlnProductorIndirecto())
                .blnAmpliacionMercancias(entity.getBlnAmpliacionMercancias())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
