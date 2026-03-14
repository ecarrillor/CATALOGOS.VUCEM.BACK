package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPartidaFraccionService;
import com.example.vucem_catalogos_service.model.dto.CatPartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCapituloFraccion;
import com.example.vucem_catalogos_service.model.entity.CatPartidaFraccion;
import com.example.vucem_catalogos_service.model.entity.CatPartidaFraccionId;
import com.example.vucem_catalogos_service.persistence.repo.ICatCapituloFraccionRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPartidaFraccionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class CatPartidaFraccionServiceImpl implements ICatPartidaFraccionService {

    @Autowired
    private ICatPartidaFraccionRepository catPartidaFraccionRepository;

    @Autowired
    private ICatCapituloFraccionRepository catCapituloFraccionRepository;

    @Override
    public PageResponseDTO<CatPartidaFraccionDTO> list(String search, Pageable pageable) {
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

        Page<CatPartidaFraccionDTO> page = catPartidaFraccionRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatPartidaFraccionDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatPartidaFraccionDTO findById(String cveCapituloFraccion, String cvePartidaFraccion) {
        return catPartidaFraccionRepository.findByPartidaFraccionDTO(cveCapituloFraccion, cvePartidaFraccion);
    }

    @Override
    public CatPartidaFraccionDTO create(CatPartidaFraccionDTO dto) {
        CatPartidaFraccionId id = new CatPartidaFraccionId();
        id.setCveCapituloFraccion(dto.getCveCapituloFraccion());
        id.setCvePartidaFraccion(dto.getCvePartidaFraccion());

        CatPartidaFraccion entity = new CatPartidaFraccion();
        entity.setId(id);

        entity.setCveCapituloFraccion(
                catCapituloFraccionRepository.findById(dto.getCveCapituloFraccion())
                        .orElseThrow(() -> new RuntimeException("Capítulo fracción no encontrado: " + dto.getCveCapituloFraccion()))
        );

        entity.setNombre(dto.getNombre());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo() != null ? dto.getBlnActivo() : true);

        CatPartidaFraccion saved = catPartidaFraccionRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatPartidaFraccionDTO update(String cveCapituloFraccion, String cvePartidaFraccion, CatPartidaFraccionDTO dto) {
        CatPartidaFraccionId id = new CatPartidaFraccionId();
        id.setCveCapituloFraccion(cveCapituloFraccion);
        id.setCvePartidaFraccion(cvePartidaFraccion);

        CatPartidaFraccion entity = catPartidaFraccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "Partida fracción no encontrada: "
                                + cveCapituloFraccion + "/" + cvePartidaFraccion));


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

        if (dto.getCveCapituloFraccion() != null) {

            entity.setCveCapituloFraccion(
                    catCapituloFraccionRepository.findById(dto.getCveCapituloFraccion())
                            .orElseThrow(() -> new RuntimeException(
                                    "Capítulo fracción no encontrado: "
                                            + dto.getCveCapituloFraccion()))
            );

            entity.getId().setCveCapituloFraccion(dto.getCveCapituloFraccion());
        }

        CatPartidaFraccion updated = catPartidaFraccionRepository.save(entity);

        return mapToDTO(updated);
    }

    @Override
    public List<CatCapituloFraccion> listCapitulosFraccionActivos() {
        return catCapituloFraccionRepository.findAllByBlnActivoTrue();
    }

    private CatPartidaFraccionDTO mapToDTO(CatPartidaFraccion entity) {
        return CatPartidaFraccionDTO.builder()
                .cveCapituloFraccion(entity.getId().getCveCapituloFraccion())
                .cvePartidaFraccion(entity.getId().getCvePartidaFraccion())
                .nombreCapituloFraccion(
                        entity.getCveCapituloFraccion() != null
                                ? entity.getCveCapituloFraccion().getNombre()
                                : null)
                .nombre(entity.getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
