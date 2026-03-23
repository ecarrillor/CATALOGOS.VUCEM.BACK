package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatRestriccionTtraService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatRestriccionTtra;
import com.example.vucem_catalogos_service.model.entity.CatUnidadMedida;
import com.example.vucem_catalogos_service.persistence.repo.ICatCategoriaTextilRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatRestriccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatRestriccionTtraServiceImpl implements ICatRestriccionTtraService {

    @Autowired
    private ICatRestriccionTtraRepository repository;

    @Autowired
    private ICatTipoTramiteRepository tipoTramiteRepository;

    @Autowired
    ICatCategoriaTextilRepository catCategoriaTextilRepository;

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idRestriccionTtra", "rt.id",
            "descTipoTramite", "rt.idTipoTramite.descModalidad",
            "descRestriccion", "rt.descRestriccion",
            "descContenidoRestriccion", "rt.descContenidoRestriccion",
            "ideSentDictamen", "rt.ideSentDictamen",
            "ideTipoRestriccionTtra", "rt.ideTipoRestriccionTtra",
            "ideMotivoRechazoDict", "rt.ideMotivoRechazoDict"
    );

    @Override
    public PageResponseDTO<CatRestriccionTtraResponseDTO> listAll(String search, Long idTipoTramite, String sortBy, String sortDir, Pageable pageable) {
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

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable pageableWithSort = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatRestriccionTtraResponseDTO> page =
                repository.search(texto, activo, idTipoTramite, pageableWithSort);

        return PageResponseDTO.<CatRestriccionTtraResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatRestriccionTtraResponseDTO crear(CatRestriccionTtraRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatRestriccionTtra entity = new CatRestriccionTtra();

        entity.setId(dto.getId());

        entity.setIdTipoTramite(
                tipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Tipo de trámite no encontrado"))
        );

        entity.setDescRestriccion(dto.getDescRestriccion());
        entity.setDescContenidoRestriccion(dto.getDescContenidoRestriccion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);
        entity.setIdeSentDictamen(dto.getIdeSentDictamen());

        CatRestriccionTtra saved = repository.save(entity);

        return toResponseDTO(saved);
    }

    @Override
    public CatRestriccionTtraResponseDTO findById(Short id) {
        CatRestriccionTtra entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));

        return toResponseDTO(entity);
    }

    @Override
    public CatRestriccionTtraResponseDTO update(Short id, CatRestriccionTtraRequestDTO dto) {

        CatRestriccionTtra entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    tipoTramiteRepository
                            .findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Tipo de trámite no encontrado"))
            );
        }

        if (dto.getDescRestriccion() != null) {
            entity.setDescRestriccion(dto.getDescRestriccion());
        }

        if (dto.getDescContenidoRestriccion() != null) {
            entity.setDescContenidoRestriccion(dto.getDescContenidoRestriccion());
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

        if (dto.getIdeSentDictamen() != null) {
            entity.setIdeSentDictamen(dto.getIdeSentDictamen());
        }

        if (dto.getIdeTipoRestriccionTtra() != null) {
            entity.setIdeTipoRestriccionTtra(dto.getIdeTipoRestriccionTtra());
        }

        if (dto.getIdeMotivoRechazoDict() != null) {
            entity.setIdeMotivoRechazoDict(dto.getIdeMotivoRechazoDict());
        }

        CatRestriccionTtra updated = repository.save(entity);

        return toResponseDTO(updated);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }


    private CatRestriccionTtraResponseDTO toResponseDTO(CatRestriccionTtra entity) {
        String descTipoTramite = entity.getIdTipoTramite().getId() + " " +
                (entity.getIdTipoTramite().getDescModalidad() != null
                        ? entity.getIdTipoTramite().getDescModalidad()
                        : entity.getIdTipoTramite().getDescSubservicio());

        return CatRestriccionTtraResponseDTO.builder()
                .idRestriccionTtra(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite().getId())
                .descTipoTramite(descTipoTramite)
                .descRestriccion(entity.getDescRestriccion())
                .descContenidoRestriccion(entity.getDescContenidoRestriccion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .ideSentDictamen(entity.getIdeSentDictamen())
                .ideTipoRestriccionTtra(entity.getIdeTipoRestriccionTtra())
                .ideMotivoRechazoDict(entity.getIdeMotivoRechazoDict())
                .build();
    }

    @Override
    public ClasifProductoTraDTO lastRestriccionTtra() {
        return repository.findTopByOrderByIdDesc()
                .map(e -> new ClasifProductoTraDTO(e.getId().longValue(), e.getDescRestriccion()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existen registros en Restricción Trámite"));
    }
}
