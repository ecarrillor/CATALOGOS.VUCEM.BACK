package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatServicioImmexService;
import com.example.vucem_catalogos_service.model.dto.CatServicioImmexDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatServicioImmex;
import com.example.vucem_catalogos_service.persistence.repo.ICatServicioImmexRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatServicioImmexServiceImpl implements ICatServicioImmexService {

    @Autowired
    private ICatServicioImmexRepository catServicioImmexRepository;

    @Override
    public PageResponseDTO<CatServicioImmexDTO> list(String search, Pageable pageable) {
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
        Page<CatServicioImmexDTO> page = catServicioImmexRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatServicioImmexDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatServicioImmexDTO findById(Short id) {
        return catServicioImmexRepository.findByServicioImmexDTO(id);
    }

    @Override
    public CatServicioImmexDTO create(CatServicioImmexDTO dto) {
        CatServicioImmex entity = new CatServicioImmex();
        entity.setId(dto.getId());
        entity.setNombre(dto.getNombre());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setIdeTipoServicioImmex(dto.getIdeTipoServicioImmex());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatServicioImmex saved = catServicioImmexRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatServicioImmexDTO update(Short id, CatServicioImmexDTO dto) {
        CatServicioImmex entity = catServicioImmexRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Servicio IMMEX no encontrado"));

        if (dto.getNombre() != null)
            entity.setNombre(dto.getNombre());
        if (dto.getFecIniVigencia() != null)
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getIdeTipoServicioImmex() != null)
            entity.setIdeTipoServicioImmex(dto.getIdeTipoServicioImmex());
        if (dto.getFecFinVigencia() != null)
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null)
            entity.setBlnActivo(dto.getBlnActivo());

        CatServicioImmex updated = catServicioImmexRepository.save(entity);
        return mapToDTO(updated);
    }

    private CatServicioImmexDTO mapToDTO(CatServicioImmex entity) {
        return CatServicioImmexDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .ideTipoServicioImmex(entity.getIdeTipoServicioImmex())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
