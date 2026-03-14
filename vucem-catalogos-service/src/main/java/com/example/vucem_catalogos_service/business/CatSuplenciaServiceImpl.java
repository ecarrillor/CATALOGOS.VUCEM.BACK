package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatSuplenciaService;
import com.example.vucem_catalogos_service.model.dto.CatSuplenciaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatDependencia;
import com.example.vucem_catalogos_service.model.entity.CatSuplencia;
import com.example.vucem_catalogos_service.persistence.repo.ICatDependenciaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatSuplenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatSuplenciaServiceImpl implements ICatSuplenciaService {

    @Autowired
    private ICatSuplenciaRepository catSuplenciaRepository;

    @Autowired
    private ICatDependenciaRepository catDependenciaRepository;

    @Override
    public PageResponseDTO<CatSuplenciaDTO> list(String search, Pageable pageable) {
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
        Page<CatSuplenciaDTO> page = catSuplenciaRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatSuplenciaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatSuplenciaDTO findById(Short id) {
        return catSuplenciaRepository.findBySuplenciaDTO(id);
    }

    @Override
    public CatSuplenciaDTO create(CatSuplenciaDTO dto) {
        CatDependencia dependencia = catDependenciaRepository.findById(dto.getIdDependencia())
                .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));

        CatSuplencia entity = new CatSuplencia();
        entity.setId(dto.getId());
        entity.setIdDependencia(dependencia);
        entity.setTexto(dto.getTexto());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatSuplencia saved = catSuplenciaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatSuplenciaDTO update(Short id, CatSuplenciaDTO dto) {
        CatSuplencia entity = catSuplenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Suplencia no encontrada"));

        if (dto.getIdDependencia() != null) {
            CatDependencia dependencia = catDependenciaRepository.findById(dto.getIdDependencia())
                    .orElseThrow(() -> new RuntimeException("Dependencia no encontrada"));
            entity.setIdDependencia(dependencia);
        }
        if (dto.getTexto() != null)
            entity.setTexto(dto.getTexto());
        if (dto.getFecIniVigencia() != null)
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null)
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null)
            entity.setBlnActivo(dto.getBlnActivo());

        CatSuplencia updated = catSuplenciaRepository.save(entity);
        return mapToDTO(updated);
    }

    @Override
    public List<SelectDTO> listadoDependencias() {
        List<CatDependencia> dependencias = catDependenciaRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();
        for (CatDependencia dep : dependencias) {
            SelectDTO dto = new SelectDTO();
            dto.setId(dep.getId());
            dto.setNombre(dep.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatSuplenciaDTO mapToDTO(CatSuplencia entity) {
        return CatSuplenciaDTO.builder()
                .id(entity.getId())
                .idDependencia(entity.getIdDependencia() != null ? entity.getIdDependencia().getId() : null)
                .nombreDependencia(entity.getIdDependencia() != null ? entity.getIdDependencia().getNombre() : null)
                .texto(entity.getTexto())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
