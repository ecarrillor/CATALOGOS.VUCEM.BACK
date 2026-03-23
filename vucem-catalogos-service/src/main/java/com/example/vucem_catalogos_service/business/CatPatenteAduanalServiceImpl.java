package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPatenteAduanalService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatPatenteAduanalDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPatenteAduanal;
import com.example.vucem_catalogos_service.persistence.repo.ICatPatenteAduanalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@Transactional
public class CatPatenteAduanalServiceImpl implements ICatPatenteAduanalService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cvePatenteAduanal", "cvePatenteAduanal",
            "rfc", "rfc"
    );

    @Autowired
    private ICatPatenteAduanalRepository catPatenteAduanalRepository;

    @Override
    public PageResponseDTO<CatPatenteAduanalDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = search;
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "cvePatenteAduanal"));

        Page<CatPatenteAduanalDTO> page = catPatenteAduanalRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatPatenteAduanalDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatPatenteAduanalDTO findById(String cvePatenteAduanal) {
        return catPatenteAduanalRepository.findByPatenteAduanalDTO(cvePatenteAduanal);
    }

    @Override
    public CatPatenteAduanalDTO create(CatPatenteAduanalDTO dto) {
        CatPatenteAduanal entity = new CatPatenteAduanal();

        entity.setCvePatenteAduanal(dto.getCvePatenteAduanal());
        entity.setRfc(dto.getRfc());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo() != null ? dto.getBlnActivo() : true);
        entity.setIdeEstPatenteAut(dto.getIdeEstPatenteAut());

        CatPatenteAduanal saved = catPatenteAduanalRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatPatenteAduanalDTO update(String cvePatenteAduanal, CatPatenteAduanalDTO dto) {
        CatPatenteAduanal entity = catPatenteAduanalRepository.findById(cvePatenteAduanal)
                .orElseThrow(() -> new RuntimeException(
                        "Patente aduanal no encontrada: " + cvePatenteAduanal));

        if (dto.getRfc() != null) {
            entity.setRfc(dto.getRfc());
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

        if (dto.getIdeEstPatenteAut() != null) {
            entity.setIdeEstPatenteAut(dto.getIdeEstPatenteAut());
        }

        CatPatenteAduanal updated = catPatenteAduanalRepository.save(entity);
        return mapToDTO(updated);
    }

    private CatPatenteAduanalDTO mapToDTO(CatPatenteAduanal entity) {
        return CatPatenteAduanalDTO.builder()
                .cvePatenteAduanal(entity.getCvePatenteAduanal())
                .rfc(entity.getRfc())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideEstPatenteAut(entity.getIdeEstPatenteAut())
                .build();
    }
}
