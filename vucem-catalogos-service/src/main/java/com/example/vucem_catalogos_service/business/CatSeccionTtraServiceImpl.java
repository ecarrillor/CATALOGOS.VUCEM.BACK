package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatSeccionTtraService;
import com.example.vucem_catalogos_service.model.dto.CatSeccionTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatSeccionTtra;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatSeccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatSeccionTtraServiceImpl implements ICatSeccionTtraService {

    @Autowired
    private ICatSeccionTtraRepository catSeccionTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatSeccionTtraDTO> list(String search, Pageable pageable) {
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
        Page<CatSeccionTtraDTO> page = catSeccionTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatSeccionTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatSeccionTtraDTO findById(Integer id) {
        return catSeccionTtraRepository.findBySeccionTtraDTO(id);
    }

    @Override
    public CatSeccionTtraDTO create(CatSeccionTtraDTO dto) {
        CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite().longValue())
                .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado"));

        CatSeccionTtra entity = new CatSeccionTtra();
        entity.setId(dto.getId());
        entity.setIdTipoTramite(tipoTramite);
        entity.setDescSeccion(dto.getDescSeccion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatSeccionTtra saved = catSeccionTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatSeccionTtraDTO update(Integer id, CatSeccionTtraDTO dto) {
        CatSeccionTtra entity = catSeccionTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sección de trámite no encontrada"));

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite().longValue())
                    .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado"));
            entity.setIdTipoTramite(tipoTramite);
        }
        if (dto.getDescSeccion() != null)
            entity.setDescSeccion(dto.getDescSeccion());
        if (dto.getFecIniVigencia() != null)
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null)
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null)
            entity.setBlnActivo(dto.getBlnActivo());

        CatSeccionTtra updated = catSeccionTtraRepository.save(entity);
        return mapToDTO(updated);
    }

    private CatSeccionTtraDTO mapToDTO(CatSeccionTtra entity) {
        return CatSeccionTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(Long.valueOf(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId().intValue() : null))
                .descServicio(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getDescServicio() : null)
                .descSeccion(entity.getDescSeccion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
