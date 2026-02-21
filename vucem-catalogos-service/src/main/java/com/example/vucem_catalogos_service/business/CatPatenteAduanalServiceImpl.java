package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPatenteAduanalService;
import com.example.vucem_catalogos_service.model.dto.CatPatenteAduanalDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPatenteAduanal;
import com.example.vucem_catalogos_service.persistence.repo.ICatPatenteAduanalRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatPatenteAduanalServiceImpl implements ICatPatenteAduanalService {

    @Autowired
    private ICatPatenteAduanalRepository catPatenteAduanalRepository;

    @Override
    public PageResponseDTO<CatPatenteAduanalDTO> list(String search, Pageable pageable) {
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

        Page<CatPatenteAduanalDTO> page = catPatenteAduanalRepository.search(texto, activo, pageable);

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
                .orElseThrow(() -> new RuntimeException("Patente aduanal no encontrada: " + cvePatenteAduanal));

        entity.setRfc(dto.getRfc());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeEstPatenteAut(dto.getIdeEstPatenteAut());

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
