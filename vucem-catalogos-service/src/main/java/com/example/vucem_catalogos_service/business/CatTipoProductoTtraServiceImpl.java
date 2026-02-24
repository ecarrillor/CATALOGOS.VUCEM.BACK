package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoProductoTtraService;
import com.example.vucem_catalogos_service.model.dto.CatTipoProductoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoProductoTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoProductoTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatTipoProductoTtraServiceImpl implements ICatTipoProductoTtraService {

    @Autowired
    private ICatTipoProductoTtraRepository catTipoProductoTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatTipoProductoTtraDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatTipoProductoTtraDTO> page = catTipoProductoTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatTipoProductoTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTipoProductoTtraDTO findById(Short id) {
        return catTipoProductoTtraRepository.findByTipoProductoTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatTipoProductoTtra no encontrado con id: " + id));
    }

    @Override
    public CatTipoProductoTtraDTO create(CatTipoProductoTtraDTO dto) {
        CatTipoProductoTtra entity = new CatTipoProductoTtra();
        entity.setIdTipoTramite(
                catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
        );
        entity.setDescTipoProducto(dto.getDescTipoProducto());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());

        CatTipoProductoTtra saved = catTipoProductoTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTipoProductoTtraDTO update(Short id, CatTipoProductoTtraDTO dto) {
        CatTipoProductoTtra entity = catTipoProductoTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatTipoProductoTtra no encontrado con id: " + id));

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }
        if (dto.getDescTipoProducto() != null) {
            entity.setDescTipoProducto(dto.getDescTipoProducto());
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
        if (dto.getIdeTipoCertificadoMerc() != null) {
            entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());
        }

        CatTipoProductoTtra saved = catTipoProductoTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatTipoProductoTtraDTO mapToDTO(CatTipoProductoTtra entity) {
        return CatTipoProductoTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getNombre() : null)
                .descTipoProducto(entity.getDescTipoProducto())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideTipoCertificadoMerc(entity.getIdeTipoCertificadoMerc())
                .build();
    }
}
