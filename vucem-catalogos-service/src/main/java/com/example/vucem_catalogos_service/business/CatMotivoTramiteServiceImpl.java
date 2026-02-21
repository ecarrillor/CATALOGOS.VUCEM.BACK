package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatMotivoTramiteService;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
import com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatMotivoTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatMotivoTramiteRepository;
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
public class CatMotivoTramiteServiceImpl implements ICatMotivoTramiteService {

    @Autowired
    private ICatMotivoTramiteRepository iCatMotivoTramiteRepository;

    @Autowired
    private ICatTipoTramiteRepository iCatTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatMotivoTramiteResponseDTO> listarMotivoTramite(String search, Pageable pageable) {
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

        Page<CatMotivoTramiteResponseDTO> page =
                iCatMotivoTramiteRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatMotivoTramiteResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatMotivoTramiteResponseDTO crearCategoriaTextil(CatMotivoTramiteRequestDTO dto) {
        if (iCatMotivoTramiteRepository.existsById(dto.getIdMotivoTramite())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatMotivoTtra entity = new CatMotivoTtra();

        entity.setId(dto.getIdMotivoTramite());
        entity.setIdTipoTramite(
                iCatTipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo de Tramite no encontrado"))
        );

        entity.setIdeTipoMotivoTtra(dto.getTipoMotivo());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescMotivo(dto.getDescripcionMotivo());
        entity.setDescContenidoMotivo(dto.getDescripcionContenido());
        entity.setBlnActivo(dto.getBlnActivo());

        CatMotivoTtra saved = iCatMotivoTramiteRepository.save(entity);

        return CatMotivoTramiteResponseDTO.builder()
                .idMotivoTramite(saved.getId())
                .idTipoTramite(saved.getIdTipoTramite().getId())
                .tipoTramite(saved.getIdTipoTramite().getDescModalidad())
                .tipoMotivo(saved.getIdeTipoMotivoTtra())
                .descripcionMotivo(saved.getDescMotivo())
                .descripcionContenido(saved.getDescContenidoMotivo())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatMotivoTramiteResponseDTO findById(Long id) {
        CatMotivoTtra entity = iCatMotivoTramiteRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatMotivoTramiteResponseDTO.builder()
                .idMotivoTramite(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite().getId())
                .tipoTramite(entity.getIdTipoTramite().getDescModalidad())
                .tipoMotivo(entity.getIdeTipoMotivoTtra())
                .descripcionMotivo(entity.getDescMotivo())
                .descripcionContenido(entity.getDescContenidoMotivo())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatMotivoTramiteResponseDTO update(Long id, CatMotivoTramiteRequestDTO dto) {
        CatMotivoTtra entity = iCatMotivoTramiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setIdTipoTramite(
                iCatTipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo de Tramite no encontrado"))
        );

        entity.setIdeTipoMotivoTtra(dto.getTipoMotivo());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setDescMotivo(dto.getDescripcionMotivo());
        entity.setDescContenidoMotivo(dto.getDescripcionContenido());
        entity.setBlnActivo(dto.getBlnActivo());

        CatMotivoTtra updated = iCatMotivoTramiteRepository.save(entity);

        return CatMotivoTramiteResponseDTO.builder()
                .idMotivoTramite(updated.getId())
                .idTipoTramite(updated.getIdTipoTramite().getId())
                .tipoTramite(updated.getIdTipoTramite().getDescModalidad())
                .tipoMotivo(updated.getIdeTipoMotivoTtra())
                .descripcionMotivo(updated.getDescMotivo())
                .descripcionContenido(updated.getDescContenidoMotivo())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }
}
