package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTarifaService;
import com.example.vucem_catalogos_service.model.dto.CatTarifaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTarifa;
import com.example.vucem_catalogos_service.model.entity.CatTarifaId;
import com.example.vucem_catalogos_service.persistence.repo.ICatTarifaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class CatTarifaServiceImpl implements ICatTarifaService {

    @Autowired
    private ICatTarifaRepository catTarifaRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatTarifaDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatTarifaDTO> page = catTarifaRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatTarifaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTarifaDTO findById(Long idTipoTramite, Instant fecIniVigencia, String ideTipoTarifa) {
        return catTarifaRepository.findByTarifaDTO(idTipoTramite.intValue(), fecIniVigencia, ideTipoTarifa)
                .orElseThrow(() -> new RuntimeException(
                        "CatTarifa no encontrada con idTipoTramite: " + idTipoTramite
                                + ", fecIniVigencia: " + fecIniVigencia
                                + ", ideTipoTarifa: " + ideTipoTarifa));
    }

    @Override
    public CatTarifaDTO create(CatTarifaDTO dto) {
        CatTarifaId id = new CatTarifaId();
        id.setIdTipoTramite(dto.getIdTipoTramite().intValue());
        id.setFecIniVigencia(dto.getFecIniVigencia());
        id.setIdeTipoTarifa(dto.getIdeTipoTarifa());

        CatTarifa entity = new CatTarifa();
        entity.setId(id);
        entity.setIdTipoTramite(
                catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
        );
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setTarifa(dto.getTarifa());
        entity.setBlnActivo(dto.getBlnActivo());

        CatTarifa saved = catTarifaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTarifaDTO update(Long idTipoTramite, Instant fecIniVigencia, String ideTipoTarifa, CatTarifaDTO dto) {
        CatTarifaId id = new CatTarifaId();
        id.setIdTipoTramite(idTipoTramite.intValue());
        id.setFecIniVigencia(fecIniVigencia);
        id.setIdeTipoTarifa(ideTipoTarifa);

        CatTarifa entity = catTarifaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatTarifa no encontrada con idTipoTramite: " + idTipoTramite
                                + ", fecIniVigencia: " + fecIniVigencia
                                + ", ideTipoTarifa: " + ideTipoTarifa));

        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getTarifa() != null) {
            entity.setTarifa(dto.getTarifa());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        CatTarifa saved = catTarifaRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatTarifaDTO mapToDTO(CatTarifa entity) {
        return CatTarifaDTO.builder()
                .idTipoTramite(entity.getId().getIdTipoTramite().longValue())
                .fecIniVigencia(entity.getId().getFecIniVigencia())
                .ideTipoTarifa(entity.getId().getIdeTipoTarifa())
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getNombre() : null)
                .fecFinVigencia(entity.getFecFinVigencia())
                .tarifa(entity.getTarifa())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
