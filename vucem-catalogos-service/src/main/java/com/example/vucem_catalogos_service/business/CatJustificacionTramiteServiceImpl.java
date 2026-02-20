package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatJustificacionTramiteService;
import com.example.vucem_catalogos_service.model.dto.FundamentoDictamen.CatFundamentoDictamenResponseDTO;
import com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatFundamentoDictamen;
import com.example.vucem_catalogos_service.model.entity.CatJustificacionTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatJustificacionTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CatJustificacionTramiteServiceImpl implements ICatJustificacionTramiteService {

   @Autowired
   private ICatJustificacionTramiteRepository iCatJustificacionTramiteRepository;

   @Autowired
   private ICatTipoTramiteRepository iCatTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatJustificacionTramiteResponseDTO> listarJustificacionTramite(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {

            String s = search.trim().toLowerCase();

            if (s.equals("activo")) {
                activo = true;
            }
            else if (s.equals("inactivo")) {
                activo = false;
            }
            else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Page<CatJustificacionTramiteResponseDTO> page =
                iCatJustificacionTramiteRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatJustificacionTramiteResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatJustificacionTramiteResponseDTO crearJustificacionTramite(CatJustificacionTramiteRequestDTO dto) {
        if (iCatJustificacionTramiteRepository.existsById(dto.getIdJustificacionTramite())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatJustificacionTtra entity = new CatJustificacionTtra();

        entity.setIdTipoTramite(
                iCatTipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo tramite no encontrado"))
        );


        entity.setId(dto.getIdJustificacionTramite());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescJustificacion(dto.getDescripcionJustificacion());
        entity.setDescContenidoJustificacion(dto.getDescripcionContenido());
        entity.setBlnActivo(dto.getBlnActivo());

        CatJustificacionTtra saved = iCatJustificacionTramiteRepository.save(entity);

        return CatJustificacionTramiteResponseDTO.builder()
                .idJustificacionTramite(saved.getId())
                .idTipoTramite(saved.getIdTipoTramite().getId())
                .nombreTramite(saved.getIdTipoTramite().getDescServicio())
                .descripcionJustificacion(saved.getDescJustificacion())
                .descripcionContenido(saved.getDescContenidoJustificacion())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatJustificacionTramiteResponseDTO findById(Long id) {
        CatJustificacionTtra entity = iCatJustificacionTramiteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatJustificacionTramiteResponseDTO.builder()
                .idJustificacionTramite(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite().getId())
                .nombreTramite(entity.getIdTipoTramite().getDescServicio())
                .descripcionJustificacion(entity.getDescJustificacion())
                .descripcionContenido(entity.getDescContenidoJustificacion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatJustificacionTramiteResponseDTO update(Long id, CatJustificacionTramiteRequestDTO dto) {
        CatJustificacionTtra entity = iCatJustificacionTramiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setIdTipoTramite(
                iCatTipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo tramite no encontrado"))
        );

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescJustificacion(dto.getDescripcionJustificacion());
        entity.setDescContenidoJustificacion(dto.getDescripcionContenido());
        entity.setBlnActivo(dto.getBlnActivo());

        CatJustificacionTtra updated = iCatJustificacionTramiteRepository.save(entity);

        return CatJustificacionTramiteResponseDTO.builder()
                .idJustificacionTramite(updated.getId())
                .idTipoTramite(updated.getIdTipoTramite().getId())
                .nombreTramite(updated.getIdTipoTramite().getDescServicio())
                .descripcionJustificacion(updated.getDescJustificacion())
                .descripcionContenido(updated.getDescContenidoJustificacion())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }
}
