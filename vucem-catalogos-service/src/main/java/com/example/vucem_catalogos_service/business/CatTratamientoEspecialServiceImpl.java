package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTratamientoEspecialService;
import com.example.vucem_catalogos_service.model.dto.CatTratamientoEspecialDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTratamientoEspecial;
import com.example.vucem_catalogos_service.persistence.repo.ICatTratamientoEspecialRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatTratamientoEspecialServiceImpl implements ICatTratamientoEspecialService {

    @Autowired
    private ICatTratamientoEspecialRepository catTratamientoEspecialRepository;

    @Override
    public PageResponseDTO<CatTratamientoEspecialDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatTratamientoEspecialDTO> page = catTratamientoEspecialRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatTratamientoEspecialDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTratamientoEspecialDTO findById(Short id) {
        return catTratamientoEspecialRepository.findByTratamientoEspecialDTO(id)
                .orElseThrow(() -> new RuntimeException("CatTratamientoEspecial no encontrado con id: " + id));
    }

    @Override
    public CatTratamientoEspecialDTO create(CatTratamientoEspecialDTO dto) {
        CatTratamientoEspecial entity = new CatTratamientoEspecial();
        entity.setNombre(dto.getNombre());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeClasifTratamiento(dto.getIdeClasifTratamiento());

        CatTratamientoEspecial saved = catTratamientoEspecialRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTratamientoEspecialDTO update(Short id, CatTratamientoEspecialDTO dto) {
        CatTratamientoEspecial entity = catTratamientoEspecialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatTratamientoEspecial no encontrado con id: " + id));

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
        if (dto.getIdeClasifTratamiento() != null) {
            entity.setIdeClasifTratamiento(dto.getIdeClasifTratamiento());
        }

        CatTratamientoEspecial saved = catTratamientoEspecialRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatTratamientoEspecialDTO mapToDTO(CatTratamientoEspecial entity) {
        return CatTratamientoEspecialDTO.builder()
                .id(entity.getId())
                .nombre(entity.getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideClasifTratamiento(entity.getIdeClasifTratamiento())
                .build();
    }
}
