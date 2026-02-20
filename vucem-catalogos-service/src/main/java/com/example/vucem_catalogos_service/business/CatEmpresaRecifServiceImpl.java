package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatEmpresaRecifService;
import com.example.vucem_catalogos_service.model.dto.CatEmpresaRecifDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatEmpresaRecif;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdministrativa;
import com.example.vucem_catalogos_service.persistence.repo.ICatEmpresaRecifRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdministrativaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatEmpresaRecifServiceImpl implements ICatEmpresaRecifService {

    @Autowired
    private ICatEmpresaRecifRepository iCatEmpresaRecifRepository;

    @Autowired
    private ICatUnidadAdministrativaRepository iCatUnidadAdministrativaRepository;

    @Override
    public PageResponseDTO<CatEmpresaRecifDTO> list(String search, Pageable pageable) {
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

        Page<CatEmpresaRecifDTO> page =
                iCatEmpresaRecifRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatEmpresaRecifDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatEmpresaRecifDTO findById(String recif) {
        return iCatEmpresaRecifRepository.findByEmpresaRecifDTO(recif);
    }

    @Override
    public CatEmpresaRecifDTO create(CatEmpresaRecifDTO dto) {
        CatEmpresaRecif entity = new CatEmpresaRecif();
        entity.setRecif(dto.getRecif());
        entity.setRfc(dto.getRfc());
        entity.setRazonSocial(dto.getRazonSocial());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());

        if (dto.getCveUnidadAdministrativa() != null) {
            entity.setCveUnidadAdministrativa(
                    iCatUnidadAdministrativaRepository.findById(dto.getCveUnidadAdministrativa())
                            .orElseThrow(() -> new RuntimeException(
                                    "Unidad administrativa no encontrada: " + dto.getCveUnidadAdministrativa()))
            );
        }

        CatEmpresaRecif saved = iCatEmpresaRecifRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatEmpresaRecifDTO update(String recif, CatEmpresaRecifDTO dto) {
        CatEmpresaRecif entity = iCatEmpresaRecifRepository.findById(recif)
                .orElseThrow(() -> new RuntimeException(
                        "CatEmpresaRecif no encontrada con recif: " + recif));

        if (dto.getRfc() != null) {
            entity.setRfc(dto.getRfc());
        }

        if (dto.getRazonSocial() != null) {
            entity.setRazonSocial(dto.getRazonSocial());
        }

        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }

        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }

        if (dto.getCveUnidadAdministrativa() != null) {
            entity.setCveUnidadAdministrativa(
                    iCatUnidadAdministrativaRepository.findById(dto.getCveUnidadAdministrativa())
                            .orElseThrow(() -> new RuntimeException(
                                    "Unidad administrativa no encontrada: " + dto.getCveUnidadAdministrativa()))
            );
        }

        CatEmpresaRecif updated = iCatEmpresaRecifRepository.save(entity);

        return mapToDTO(updated);
    }

    @Override
    public List<SelectDTO> listadoUnidadesAdmin() {
        List<CatUnidadAdministrativa> unidades =
                iCatUnidadAdministrativaRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatUnidadAdministrativa u : unidades) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(u.getCveUnidadAdministrativa());
            dto.setNombre(u.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatEmpresaRecifDTO mapToDTO(CatEmpresaRecif e) {
        return CatEmpresaRecifDTO.builder()
                .recif(e.getRecif())
                .rfc(e.getRfc())
                .razonSocial(e.getRazonSocial())
                .blnActivo(e.getBlnActivo())
                .fecIniVigencia(e.getFecIniVigencia())
                .fecFinVigencia(e.getFecFinVigencia())
                .cveUnidadAdministrativa(e.getCveUnidadAdministrativa() != null
                        ? e.getCveUnidadAdministrativa().getCveUnidadAdministrativa() : null)
                .nombreUnidadAdministrativa(e.getCveUnidadAdministrativa() != null
                        ? e.getCveUnidadAdministrativa().getNombre() : null)
                .build();
    }
}
