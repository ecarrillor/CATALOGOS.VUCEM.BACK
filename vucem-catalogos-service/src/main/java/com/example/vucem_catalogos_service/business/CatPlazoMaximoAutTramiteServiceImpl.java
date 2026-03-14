package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPlazoMaximoAutTramiteService;
import com.example.vucem_catalogos_service.model.dto.CatPlazoMaximoAutTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPlazoMaximoAutTramite;
import com.example.vucem_catalogos_service.model.entity.CatPlazoMaximoAutTramiteId;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatPlazoMaximoAutTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;

@Service
@Transactional
public class CatPlazoMaximoAutTramiteServiceImpl implements ICatPlazoMaximoAutTramiteService {

    @Autowired
    private ICatPlazoMaximoAutTramiteRepository catPlazoMaximoAutTramiteRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatPlazoMaximoAutTramiteDTO> list(String search, Pageable pageable) {
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
        Page<CatPlazoMaximoAutTramiteDTO> page =
                catPlazoMaximoAutTramiteRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatPlazoMaximoAutTramiteDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatPlazoMaximoAutTramiteDTO findById(Long idTipoTramite, LocalDate fecIniVigencia) {
        return catPlazoMaximoAutTramiteRepository.findByPlazoMaximoAutTramiteDTO(idTipoTramite, fecIniVigencia);
    }

    @Override
    public CatPlazoMaximoAutTramiteDTO create(CatPlazoMaximoAutTramiteDTO dto) {
        CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite().longValue())
                .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado"));

        CatPlazoMaximoAutTramiteId id = new CatPlazoMaximoAutTramiteId();
        id.setIdTipoTramite(dto.getIdTipoTramite());
        id.setFecIniVigencia(dto.getFecIniVigencia());

        CatPlazoMaximoAutTramite entity = new CatPlazoMaximoAutTramite();
        entity.setId(id);
        entity.setIdTipoTramite(tipoTramite);
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setPlazoAnios(dto.getPlazoAnios());
        entity.setIdePlazoMeses(dto.getIdePlazoMeses());
        entity.setBlnIlimitado(dto.getBlnIlimitado());
        entity.setPlazo(dto.getPlazo());
        entity.setBlnAsignacionFechaFin(dto.getBlnAsignacionFechaFin());
        entity.setBlnActivo(dto.getBlnActivo());

        CatPlazoMaximoAutTramite saved = catPlazoMaximoAutTramiteRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatPlazoMaximoAutTramiteDTO update(Long idTipoTramite, LocalDate fecIniVigencia, CatPlazoMaximoAutTramiteDTO dto) {
        CatPlazoMaximoAutTramiteId id = new CatPlazoMaximoAutTramiteId();
        id.setIdTipoTramite(idTipoTramite);
        id.setFecIniVigencia(fecIniVigencia);

        CatPlazoMaximoAutTramite entity = catPlazoMaximoAutTramiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Plazo máximo aut. trámite no encontrado"));

        if (dto.getFecFinVigencia() != null)
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getPlazoAnios() != null)
            entity.setPlazoAnios(dto.getPlazoAnios());
        if (dto.getIdePlazoMeses() != null)
            entity.setIdePlazoMeses(dto.getIdePlazoMeses());
        if (dto.getBlnIlimitado() != null)
            entity.setBlnIlimitado(dto.getBlnIlimitado());
        if (dto.getPlazo() != null)
            entity.setPlazo(dto.getPlazo());
        if (dto.getBlnAsignacionFechaFin() != null)
            entity.setBlnAsignacionFechaFin(dto.getBlnAsignacionFechaFin());
        if (dto.getBlnActivo() != null)
            entity.setBlnActivo(dto.getBlnActivo());

        CatPlazoMaximoAutTramite updated = catPlazoMaximoAutTramiteRepository.save(entity);
        return mapToDTO(updated);
    }

    private CatPlazoMaximoAutTramiteDTO mapToDTO(CatPlazoMaximoAutTramite entity) {
        return CatPlazoMaximoAutTramiteDTO.builder()
                .idTipoTramite(entity.getId().getIdTipoTramite())
                .fecIniVigencia(entity.getId().getFecIniVigencia())
                .descServicio(entity.getIdTipoTramite().getDescServicio())
                .fecFinVigencia(entity.getFecFinVigencia())
                .plazoAnios(entity.getPlazoAnios())
                .idePlazoMeses(entity.getIdePlazoMeses())
                .blnIlimitado(entity.getBlnIlimitado())
                .plazo(entity.getPlazo())
                .blnAsignacionFechaFin(entity.getBlnAsignacionFechaFin())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
