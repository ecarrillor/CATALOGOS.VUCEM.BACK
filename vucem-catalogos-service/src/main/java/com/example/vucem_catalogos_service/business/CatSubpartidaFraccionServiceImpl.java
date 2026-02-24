package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatSubpartidaFraccionService;
import com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatSubpartidaFraccion;
import com.example.vucem_catalogos_service.model.entity.CatSubpartidaFraccionId;
import com.example.vucem_catalogos_service.model.entity.CatPartidaFraccionId;
import com.example.vucem_catalogos_service.persistence.repo.ICatSubpartidaFraccionRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPartidaFraccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatSubpartidaFraccionServiceImpl implements ICatSubpartidaFraccionService {

    @Autowired
    private ICatSubpartidaFraccionRepository catSubpartidaFraccionRepository;

    @Autowired
    private ICatPartidaFraccionRepository catPartidaFraccionRepository;

    @Override
    public PageResponseDTO<CatSubpartidaFraccionDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatSubpartidaFraccionDTO> page = catSubpartidaFraccionRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatSubpartidaFraccionDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatSubpartidaFraccionDTO findById(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion) {
        return catSubpartidaFraccionRepository.findBySubpartidaFraccionDTO(cveSubpartidaFraccion, cveCapituloFraccion, cvePartidaFraccion)
                .orElseThrow(() -> new RuntimeException(
                        "CatSubpartidaFraccion no encontrada con cveSubpartidaFraccion: " + cveSubpartidaFraccion
                                + ", cveCapituloFraccion: " + cveCapituloFraccion
                                + ", cvePartidaFraccion: " + cvePartidaFraccion));
    }

    @Override
    public CatSubpartidaFraccionDTO create(CatSubpartidaFraccionDTO dto) {
        CatSubpartidaFraccionId id = new CatSubpartidaFraccionId();
        id.setCveSubpartidaFraccion(dto.getCveSubpartidaFraccion());
        id.setCveCapituloFraccion(dto.getCveCapituloFraccion());
        id.setCvePartidaFraccion(dto.getCvePartidaFraccion());

        if (catSubpartidaFraccionRepository.existsById(id)) {
            throw new RuntimeException("La Subpartida ya existe.");
        }

        CatPartidaFraccionId partidaId = new CatPartidaFraccionId();
        partidaId.setCveCapituloFraccion(dto.getCveCapituloFraccion());
        partidaId.setCvePartidaFraccion(dto.getCvePartidaFraccion());

        if (!catPartidaFraccionRepository.existsById(partidaId)) {
            throw new RuntimeException(
                    "CatPartidaFraccion no encontrada con cveCapituloFraccion: "
                            + dto.getCveCapituloFraccion()
                            + " y cvePartidaFraccion: "
                            + dto.getCvePartidaFraccion()
            );
        }

        CatSubpartidaFraccion entity = new CatSubpartidaFraccion();
        entity.setId(id);
        entity.setNombre(dto.getNombrePartidaFraccion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatSubpartidaFraccion saved = catSubpartidaFraccionRepository.save(entity);

        return mapToDTO(saved);
    }

    @Override
    public CatSubpartidaFraccionDTO update(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion, CatSubpartidaFraccionDTO dto) {
        CatSubpartidaFraccionId id = new CatSubpartidaFraccionId();
        id.setCveSubpartidaFraccion(cveSubpartidaFraccion);
        id.setCveCapituloFraccion(cveCapituloFraccion);
        id.setCvePartidaFraccion(cvePartidaFraccion);

        CatSubpartidaFraccion entity = catSubpartidaFraccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatSubpartidaFraccion no encontrada con cveSubpartidaFraccion: " + cveSubpartidaFraccion
                                + ", cveCapituloFraccion: " + cveCapituloFraccion
                                + ", cvePartidaFraccion: " + cvePartidaFraccion));

        if (dto.getNombrePartidaFraccion() != null) {
            entity.setNombre(dto.getNombrePartidaFraccion());
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

        CatSubpartidaFraccion saved = catSubpartidaFraccionRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatSubpartidaFraccionDTO mapToDTO(CatSubpartidaFraccion entity) {
        return CatSubpartidaFraccionDTO.builder()
                .cveSubpartidaFraccion(entity.getId().getCveSubpartidaFraccion())
                .cveCapituloFraccion(entity.getId().getCveCapituloFraccion())
                .cvePartidaFraccion(entity.getId().getCvePartidaFraccion())
                .nombrePartidaFraccion(entity.getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
