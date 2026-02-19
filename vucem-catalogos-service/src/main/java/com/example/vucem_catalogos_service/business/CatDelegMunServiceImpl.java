package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDelegMunService;
import com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDelegMun;
import com.example.vucem_catalogos_service.persistence.repo.ICatDelegMunRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatDelegMunServiceImpl implements ICatDelegMunService {

    @Autowired
    private ICatDelegMunRepository catDelegMunRepository;

    @Override
    public PageResponseDTO<CatDelegMunDTO> list(String search, Pageable pageable) {
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

        Page<CatDelegMunDTO> page = catDelegMunRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatDelegMunDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatDelegMunDTO findById(String cveDelegMun) {
        return catDelegMunRepository.findByDelegMunDTO(cveDelegMun);
    }

    @Override
    public CatDelegMunDTO create(CatDelegMunDTO dto) {
        CatDelegMun entity = new CatDelegMun();
        entity.setCveDelegMun(dto.getCveDelegMun());
        entity.setNombre(dto.getNombre());
        entity.setFecCaptura(dto.getFecCaptura());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setSatMunicipality(dto.getSatMunicipality());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatDelegMun saved = catDelegMunRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatDelegMunDTO update(String cveDelegMun, CatDelegMunDTO dto) {
        CatDelegMun entity = catDelegMunRepository.findById(cveDelegMun)
                .orElseThrow(() -> new RuntimeException("Municipio/Delegación no encontrado: " + cveDelegMun));

        entity.setNombre(dto.getNombre());
        entity.setFecCaptura(dto.getFecCaptura());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setSatMunicipality(dto.getSatMunicipality());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatDelegMun updated = catDelegMunRepository.save(entity);
        return mapToDTO(updated);
    }

    private CatDelegMunDTO mapToDTO(CatDelegMun entity) {
        return CatDelegMunDTO.builder()
                .cveDelegMun(entity.getCveDelegMun())
                .nombre(entity.getNombre())
                .fecCaptura(entity.getFecCaptura())
                .fecFinVigencia(entity.getFecFinVigencia())
                .satMunicipality(entity.getSatMunicipality())
                .fecIniVigencia(entity.getFecIniVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
