package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatSubdivisionFraccionService;
import com.example.vucem_catalogos_service.model.dto.CatSubdivisionFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatSubdivisionFraccion;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionArancelariaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatSubdivisionFraccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatSubdivisionFraccionServiceImpl implements ICatSubdivisionFraccionService {

    @Autowired
    private ICatSubdivisionFraccionRepository catSubdivisionFraccionRepository;

    @Autowired
    private ICatFraccionArancelariaRepository catFraccionArancelariaRepository;

    @Override
    public PageResponseDTO<CatSubdivisionFraccionDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatSubdivisionFraccionDTO> page = catSubdivisionFraccionRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatSubdivisionFraccionDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatSubdivisionFraccionDTO findById(String cveSubdivision) {
        return catSubdivisionFraccionRepository.findBySubdivisionFraccionDTO(cveSubdivision)
                .orElseThrow(() -> new RuntimeException("CatSubdivisionFraccion no encontrada con cveSubdivision: " + cveSubdivision));
    }

    @Override
    public CatSubdivisionFraccionDTO create(CatSubdivisionFraccionDTO dto) {
        CatSubdivisionFraccion entity = new CatSubdivisionFraccion();
        entity.setCveSubdivision(dto.getCveSubdivision());
        entity.setCveFraccion(
                catFraccionArancelariaRepository.findById(dto.getCveFraccion())
                        .orElseThrow(() -> new RuntimeException("CatFraccionArancelaria no encontrada: " + dto.getCveFraccion()))
        );
        entity.setCodSubdivision(dto.getCodSubdivision());
        entity.setDescripcion(dto.getDescripcion());
        entity.setPrecioEstimado(dto.getPrecioEstimado());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatSubdivisionFraccion saved = catSubdivisionFraccionRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatSubdivisionFraccionDTO update(String cveSubdivision, CatSubdivisionFraccionDTO dto) {
        CatSubdivisionFraccion entity = catSubdivisionFraccionRepository.findById(cveSubdivision)
                .orElseThrow(() -> new RuntimeException("CatSubdivisionFraccion no encontrada con cveSubdivision: " + cveSubdivision));

        if (dto.getCveFraccion() != null) {
            entity.setCveFraccion(
                    catFraccionArancelariaRepository.findById(dto.getCveFraccion())
                            .orElseThrow(() -> new RuntimeException("CatFraccionArancelaria no encontrada: " + dto.getCveFraccion()))
            );
        }
        if (dto.getCodSubdivision() != null) {
            entity.setCodSubdivision(dto.getCodSubdivision());
        }
        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
        }
        if (dto.getPrecioEstimado() != null) {
            entity.setPrecioEstimado(dto.getPrecioEstimado());
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

        CatSubdivisionFraccion saved = catSubdivisionFraccionRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatSubdivisionFraccionDTO mapToDTO(CatSubdivisionFraccion entity) {
        return CatSubdivisionFraccionDTO.builder()
                .cveSubdivision(entity.getCveSubdivision())
                .cveFraccion(entity.getCveFraccion() != null ? entity.getCveFraccion().getCveFraccion() : null)
                .descripcionFraccion(entity.getCveFraccion() != null ? entity.getCveFraccion().getDescripcion() : null)
                .codSubdivision(entity.getCodSubdivision())
                .descripcion(entity.getDescripcion())
                .precioEstimado(entity.getPrecioEstimado())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
