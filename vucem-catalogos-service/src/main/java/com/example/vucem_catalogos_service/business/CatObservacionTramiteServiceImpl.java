package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatObservacionTramiteService;
import com.example.vucem_catalogos_service.model.dto.CategoriaTextil.CatCategoriaTextilResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatObservacionTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatObservacionTtraRepository;
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
public class CatObservacionTramiteServiceImpl implements ICatObservacionTramiteService {

    @Autowired
    private ICatObservacionTtraRepository observacionTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository iCatTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatObservacionTramiteResponseDTO> listarObservacionTramite(String search, Pageable pageable) {
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

        Page<CatObservacionTramiteResponseDTO> page =
                observacionTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatObservacionTramiteResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatObservacionTramiteResponseDTO crearObservacionTramite(CatObservacionTramiteRequestDTO dto) {
        if (observacionTtraRepository.existsById(dto.getNumObservacionTramite())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe una observación para el tipo de trámite con id: "
                            + dto.getNumObservacionTramite()
            );
        }

        CatObservacionTtra entity = new CatObservacionTtra();

        entity.setIdTipoTramite(
                iCatTipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo de tramite no encontrada"))
        );

        entity.setId(dto.getNumObservacionTramite());
        entity.setDescObservacionDict(dto.getDescripcionObservacion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatObservacionTtra saved = observacionTtraRepository.save(entity);

        return CatObservacionTramiteResponseDTO.builder()
                .descripcionObservacion(saved.getDescObservacionDict())
                .tipoTramite(saved.getIdTipoTramite().getDescModalidad())
                .numObservacionTramite(saved.getId())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatObservacionTramiteResponseDTO findById(Long id) {
        CatObservacionTtra entity = observacionTtraRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatObservacionTramiteResponseDTO.builder()
                .descripcionObservacion(entity.getDescObservacionDict())
                .tipoTramite(entity.getIdTipoTramite().getDescModalidad())
                .numObservacionTramite(entity.getId())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatObservacionTramiteResponseDTO update(Long id, CatObservacionTramiteRequestDTO dto) {
        CatObservacionTtra entity = observacionTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    iCatTipoTramiteRepository
                            .findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("Tipo de tramite no encontrada"))
            );
        }

        if(dto.getDescripcionObservacion()!= null){
        entity.setDescObservacionDict(dto.getDescripcionObservacion());
    }
        if(dto.getFecIniVigencia()!= null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if(dto.getFecIniVigencia()!= null){
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }

        if (dto.getBlnActivo()!=null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }
        CatObservacionTtra updated = observacionTtraRepository.save(entity);

        return CatObservacionTramiteResponseDTO.builder()
                .descripcionObservacion(updated.getDescObservacionDict())
                .tipoTramite(updated.getIdTipoTramite().getDescModalidad())
                .numObservacionTramite(updated.getId())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }
}
