package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDelegMunService;
import com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO;
import com.example.vucem_catalogos_service.model.dto.CatDelegMunSaveDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDelegMun;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.persistence.repo.ICatDelegMunRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatEntidadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@Transactional
public class CatDelegMunServiceImpl implements ICatDelegMunService {

    @Autowired
    private ICatDelegMunRepository catDelegMunRepository;
    @Autowired
    private ICatEntidadRepository catEntidadRepository;

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
    public CatDelegMunSaveDTO create(CatDelegMunSaveDTO dto) {

        CatDelegMun entity = new CatDelegMun();

        entity.setCveDelegMun(dto.getCveDelegMun());
        entity.setNombre(dto.getNombre());

        // Fecha de captura = hoy
        entity.setFecCaptura(LocalDate.now());

        CatEntidad catEntidad = catEntidadRepository
                .findById(dto.getCveEntidad())
                .orElseThrow(() ->
                        new RuntimeException("Entidad no encontrada: " + dto.getCveEntidad()));

        entity.setCveEntidad(catEntidad);

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getSatMunicipality() != null) {
            entity.setSatMunicipality(dto.getSatMunicipality());
        } else {
            entity.setSatMunicipality(buildSatMunicipality(dto.getCveDelegMun()));
        }

        CatDelegMun saved = catDelegMunRepository.save(entity);

        return mapToDTO(saved);
    }


    @Override
    public CatDelegMunSaveDTO update(String cveDelegMun, CatDelegMunSaveDTO dto) {

        CatDelegMun entity = catDelegMunRepository.findById(cveDelegMun)
                .orElseThrow(() -> new RuntimeException(
                        "Municipio/Delegación no encontrado: " + cveDelegMun));

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

        if (dto.getCveEntidad() != null) {
            CatEntidad catEntidad = catEntidadRepository
                    .findById(dto.getCveEntidad())
                    .orElseThrow(() -> new RuntimeException(
                            "Entidad no encontrada: " + dto.getCveEntidad()));

            entity.setCveEntidad(catEntidad);
        }

        if (dto.getSatMunicipality() != null) {
            entity.setSatMunicipality(dto.getSatMunicipality());
        } else {
            // Recalcular solo si no viene
            entity.setSatMunicipality(
                    buildSatMunicipality(entity.getCveDelegMun()));
        }

        CatDelegMun updated = catDelegMunRepository.save(entity);

        return mapToDTO(updated);
    }

    private CatDelegMunSaveDTO mapToDTO(CatDelegMun entity) {
        return CatDelegMunSaveDTO.builder()
                .cveDelegMun(entity.getCveDelegMun())
                .nombre(entity.getNombre())
                .fecFinVigencia(entity.getFecFinVigencia())
                .fecIniVigencia(entity.getFecIniVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    private String buildSatMunicipality(String cveDelegMun) {
        if (cveDelegMun == null || cveDelegMun.isEmpty()) {
            return null;
        }

        String ultimosTres = cveDelegMun.length() >= 3
                ? cveDelegMun.substring(cveDelegMun.length() - 3)
                : cveDelegMun;
        return ultimosTres.replaceFirst("^0+(?!$)", "");
    }

}
