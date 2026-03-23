package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoTramiteService;
import com.example.vucem_catalogos_service.model.dto.CatTipoTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.persistence.repo.ICatDependenciaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.Map;

@Service
@Transactional
public class CatTipoTramiteServiceImpl implements ICatTipoTramiteService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id",
            "cveServicio", "cveServicio",
            "descServicio", "descServicio",
            "cveSubservicio", "cveSubservicio",
            "descSubservicio", "descSubservicio",
            "cveModalidad", "cveModalidad",
            "descModalidad", "descModalidad",
            "nombre", "nombre",
            "cveModulo", "cveModulo",
            "nombreDependencia", "idDependencia.nombre"
    );

    @Autowired
    private ICatTipoTramiteRepo catTipoTramiteRepo;

    @Autowired
    private ICatDependenciaRepository catDependenciaRepository;

    @Override
    public PageResponseDTO<CatTipoTramiteDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        Page<CatTipoTramiteDTO> page = catTipoTramiteRepo.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatTipoTramiteDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTipoTramiteDTO findById(Long id) {
        return catTipoTramiteRepo.findByTipoTramiteDTO(id)
                .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado con id: " + id));
    }

    @Override
    public CatTipoTramiteDTO create(CatTipoTramiteDTO dto) {

        if (catTipoTramiteRepo.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }
        CatTipoTramite entity = new CatTipoTramite();
        entity.setId(dto.getId());
        entity.setCveServicio(dto.getCveServicio());
        entity.setDescServicio(dto.getDescServicio());
        entity.setCveSubservicio(dto.getCveSubservicio());
        entity.setDescSubservicio(dto.getDescSubservicio());
        entity.setCveModalidad(dto.getCveModalidad());
        entity.setDescModalidad(dto.getDescModalidad());
        entity.setFecCaptura(LocalDate.now());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setCveModulo(dto.getCveModulo());

        if (dto.getIdDependencia() != null) {
            entity.setIdDependencia(
                    catDependenciaRepository.findById(dto.getIdDependencia())
                            .orElseThrow(() -> new RuntimeException("CatDependencia no encontrada: " + dto.getIdDependencia()))
            );
        }

        CatTipoTramite saved = catTipoTramiteRepo.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTipoTramiteDTO update(Long id, CatTipoTramiteDTO dto) {
        CatTipoTramite entity = catTipoTramiteRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado con id: " + id));

        if (dto.getCveServicio() != null) {
            entity.setCveServicio(dto.getCveServicio());
        }
        if (dto.getDescServicio() != null) {
            entity.setDescServicio(dto.getDescServicio());
        }
        if (dto.getCveSubservicio() != null) {
            entity.setCveSubservicio(dto.getCveSubservicio());
        }
        if (dto.getDescSubservicio() != null) {
            entity.setDescSubservicio(dto.getDescSubservicio());
        }
        if (dto.getCveModalidad() != null) {
            entity.setCveModalidad(dto.getCveModalidad());
        }
        if (dto.getDescModalidad() != null) {
            entity.setDescModalidad(dto.getDescModalidad());
        }
        if (dto.getCveFlujo() != null) {
            entity.setCveFlujo(dto.getCveFlujo());
        }
        if (dto.getDescFlujo() != null) {
            entity.setDescFlujo(dto.getDescFlujo());
        }
        if (dto.getNivelServicio() != null) {
            entity.setNivelServicio(dto.getNivelServicio());
        }
        if (dto.getNomServAxway() != null) {
            entity.setNomServAxway(dto.getNomServAxway());
        }
        if (dto.getNomMensajeAxway() != null) {
            entity.setNomMensajeAxway(dto.getNomMensajeAxway());
        }
        if (dto.getUrlAxway() != null) {
            entity.setUrlAxway(dto.getUrlAxway());
        }
        if (dto.getFecCaptura() != null) {
            entity.setFecCaptura(dto.getFecCaptura());
        }
        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }
        if (dto.getBlnReplicaInfo() != null) {
            entity.setBlnReplicaInfo(dto.getBlnReplicaInfo());
        }
        if (dto.getBlnAutomatico() != null) {
            entity.setBlnAutomatico(dto.getBlnAutomatico());
        }
        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }
        if (dto.getBlnAsignacion() != null) {
            entity.setBlnAsignacion(dto.getBlnAsignacion());
        }
        if (dto.getCveModulo() != null) {
            entity.setCveModulo(dto.getCveModulo());
        }
        if (dto.getIdDependencia() != null) {
            entity.setIdDependencia(
                    catDependenciaRepository.findById(dto.getIdDependencia())
                            .orElseThrow(() -> new RuntimeException("CatDependencia no encontrada: " + dto.getIdDependencia()))
            );
        }

        CatTipoTramite saved = catTipoTramiteRepo.save(entity);
        return mapToDTO(saved);
    }

    private CatTipoTramiteDTO mapToDTO(CatTipoTramite entity) {
        return CatTipoTramiteDTO.builder()
                .id(entity.getId())
                .cveServicio(entity.getCveServicio())
                .descServicio(entity.getDescServicio())
                .cveSubservicio(entity.getCveSubservicio())
                .descSubservicio(entity.getDescSubservicio())
                .cveModalidad(entity.getCveModalidad())
                .descModalidad(entity.getDescModalidad())
                .cveFlujo(entity.getCveFlujo())
                .descFlujo(entity.getDescFlujo())
                .nivelServicio(entity.getNivelServicio())
                .idDependencia(entity.getIdDependencia() != null ? entity.getIdDependencia().getId() : null)
                .nombreDependencia(entity.getIdDependencia() != null ? entity.getIdDependencia().getNombre() : null)
                .nomServAxway(entity.getNomServAxway())
                .nomMensajeAxway(entity.getNomMensajeAxway())
                .urlAxway(entity.getUrlAxway())
                .fecCaptura(entity.getFecCaptura())
                .fecFinVigencia(entity.getFecFinVigencia())
                .nombre(entity.getNombre())
                .blnReplicaInfo(entity.getBlnReplicaInfo())
                .blnAutomatico(entity.getBlnAutomatico())
                .fecIniVigencia(entity.getFecIniVigencia())
                .blnActivo(entity.getBlnActivo())
                .blnAsignacion(entity.getBlnAsignacion())
                .cveModulo(entity.getCveModulo())
                .build();
    }
}
