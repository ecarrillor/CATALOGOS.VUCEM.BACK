package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatMedioTransporteTtraService;
import com.example.vucem_catalogos_service.model.dto.CatMedioTransporteTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatMedioTransporteTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatMedioTransporteTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatMedioTransporteTtraServiceImpl implements ICatMedioTransporteTtraService {

    @Autowired
    private ICatMedioTransporteTtraRepository catMedioTransporteTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatMedioTransporteTtraDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatMedioTransporteTtraDTO> page = catMedioTransporteTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatMedioTransporteTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatMedioTransporteTtraDTO findById(Integer id) {
        return catMedioTransporteTtraRepository.findByMedioTransporteTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatMedioTransporteTtra no encontrado con id: " + id));
    }

    @Override
    public CatMedioTransporteTtraDTO create(CatMedioTransporteTtraDTO dto) {
        CatMedioTransporteTtra entity = new CatMedioTransporteTtra();
        entity.setIdeMedioTransporteGob(dto.getIdeMedioTransporteGob());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        CatMedioTransporteTtra saved = catMedioTransporteTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatMedioTransporteTtraDTO update(Integer id, CatMedioTransporteTtraDTO dto) {
        CatMedioTransporteTtra entity = catMedioTransporteTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatMedioTransporteTtra no encontrado con id: " + id));

        if (dto.getIdeMedioTransporteGob() != null) {
            entity.setIdeMedioTransporteGob(dto.getIdeMedioTransporteGob());
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
        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        CatMedioTransporteTtra saved = catMedioTransporteTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatMedioTransporteTtraDTO mapToDTO(CatMedioTransporteTtra entity) {
        return CatMedioTransporteTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getNombre() : null)
                .ideMedioTransporteGob(entity.getIdeMedioTransporteGob())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
