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
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

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

        List<CatTarifaDTO> contentTransformado =
                page.getContent().stream()
                        .peek(dto -> dto.setCveTipoTarifa(
                                obtenerDescripcionTarifa(dto.getCveTipoTarifa())
                        ))
                        .toList();

        return PageResponseDTO.<CatTarifaDTO>builder()
                .content(contentTransformado)
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTarifaDTO findById(Long idTipoTramite, LocalDate fecIniVigencia, String ideTipoTarifa) {
        CatTarifaDTO dto = catTarifaRepository
                .findByTarifaDTO(
                        idTipoTramite.intValue(),
                        fecIniVigencia,
                        ideTipoTarifa
                )
                .orElseThrow(() -> new RuntimeException("CatTarifa no encontrada"));

        dto.setCveTipoTarifa(
                obtenerDescripcionTarifa(dto.getCveTipoTarifa())
        );

        return dto;
    }

    @Override
    public CatTarifaDTO create(CatTarifaDTO dto) {
        CatTarifaId id = new CatTarifaId();
        id.setIdTipoTramite(dto.getIdTipoTramite());
        id.setFecIniVigencia(dto.getFecIniVigencia());
        id.setIdeTipoTarifa(dto.getIdTipoTarifa());

        if (catTarifaRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

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
    public CatTarifaDTO update(Long idTipoTramite, LocalDate fecIniVigencia, String ideTipoTarifa, CatTarifaDTO dto) {
        CatTarifaId id = new CatTarifaId();
        id.setIdTipoTramite(idTipoTramite);
        id.setFecIniVigencia(fecIniVigencia);
        id.setIdeTipoTarifa(ideTipoTarifa);

        CatTarifa entity = catTarifaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                       HttpStatus.BAD_REQUEST, "CatTarifa no encontrada con idTipoTramite: " + idTipoTramite
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
        String idTipoTarifa = entity.getId().getIdeTipoTarifa();
        return CatTarifaDTO.builder()
                .idTipoTramite(entity.getId().getIdTipoTramite())
                .fecIniVigencia(entity.getId().getFecIniVigencia())
                .idTipoTarifa(idTipoTarifa)
                .cveTipoTarifa(obtenerDescripcionTarifa(idTipoTarifa))
                .cveTipoTramite(entity.getIdTipoTramite().getDescSubservicio())
                .fecFinVigencia(entity.getFecFinVigencia())
                .tarifa(entity.getTarifa())
                .blnActivo(entity.getBlnActivo())
                .build();

    }

    private String obtenerDescripcionTarifa(String id) {

        if (id == null) {
            return null;
        }

        String clave = id.trim();

        Map<String, String> TIPO_TARIFA_MAP = Map.of(
                "TITAR.CC", "Copia Certificada",
                "TITAR.TR", "Trámite"
        );

        return TIPO_TARIFA_MAP.getOrDefault(clave, clave);
    }
}
