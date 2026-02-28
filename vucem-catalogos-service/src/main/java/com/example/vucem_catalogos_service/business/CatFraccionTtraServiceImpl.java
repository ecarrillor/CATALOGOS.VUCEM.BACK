package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionTtraService;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatFraccionTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatCategoriaTextilRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionArancelariaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatFraccionTtraServiceImpl implements ICatFraccionTtraService {

    @Autowired
    private ICatFraccionTtraRepository repository;

    @Autowired
    private ICatFraccionArancelariaRepository fraccionArancelariaRepository;

    @Autowired
    private ICatTipoTramiteRepository tipoTramiteRepository;

    @Autowired
    private ICatCategoriaTextilRepository categoriaTextilRepository;

    @Override
    public PageResponseDTO<CatFraccionTtraResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable) {
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

        Page<CatFraccionTtraResponseDTO> page =
                repository.search(texto, activo, idTipoTramite, pageable);

        return PageResponseDTO.<CatFraccionTtraResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatFraccionTtraResponseDTO crear(CatFraccionTtraRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatFraccionTtra entity = new CatFraccionTtra();

        entity.setId(dto.getId());

        entity.setCveFraccion(
                fraccionArancelariaRepository
                        .findById(dto.getCveFraccion())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Fracción arancelaria no encontrada"))
        );

        entity.setIdTipoTramite(
                tipoTramiteRepository
                        .findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Tipo de trámite no encontrado"))
        );

        if (dto.getIdCategoriaTextil() != null) {
            entity.setIdCategoriaTextil(
                    categoriaTextilRepository
                            .findById(dto.getIdCategoriaTextil())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Categoría textil no encontrada"))
            );
        }

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);
        entity.setDescFraccionAlt(dto.getDescFraccionAlt());
        entity.setIdeClasifPartida(dto.getIdeClasifPartida());
        entity.setBlnFraccionControlada(dto.getBlnFraccionControlada());
        entity.setFactorConversion(dto.getFactorConversion());
        entity.setValorEquivalencia(dto.getValorEquivalencia());
        entity.setCveUnidadMedida(dto.getCveUnidadMedida());
        entity.setReglaAplicable(dto.getReglaAplicable());

        CatFraccionTtra saved = repository.save(entity);

        return toResponseDTO(saved);
    }

    @Override
    public CatFraccionTtraResponseDTO findById(Long id) {
        CatFraccionTtra entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));

        return toResponseDTO(entity);
    }

    @Override
    public CatFraccionTtraResponseDTO update(Long id, CatFraccionTtraRequestDTO dto) {

        CatFraccionTtra entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getCveFraccion() != null) {
            entity.setCveFraccion(
                    fraccionArancelariaRepository
                            .findById(dto.getCveFraccion())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Fracción arancelaria no encontrada"))
            );
        }

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    tipoTramiteRepository
                            .findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Tipo de trámite no encontrado"))
            );
        }

        if (dto.getIdCategoriaTextil() != null) {
            entity.setIdCategoriaTextil(
                    categoriaTextilRepository
                            .findById(dto.getIdCategoriaTextil())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Categoría textil no encontrada"))
            );
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

        if (dto.getDescFraccionAlt() != null) {
            entity.setDescFraccionAlt(dto.getDescFraccionAlt());
        }

        if (dto.getIdeClasifPartida() != null) {
            entity.setIdeClasifPartida(dto.getIdeClasifPartida());
        }

        if (dto.getBlnFraccionControlada() != null) {
            entity.setBlnFraccionControlada(dto.getBlnFraccionControlada());
        }

        if (dto.getFactorConversion() != null) {
            entity.setFactorConversion(dto.getFactorConversion());
        }

        if (dto.getValorEquivalencia() != null) {
            entity.setValorEquivalencia(dto.getValorEquivalencia());
        }

        if (dto.getCveUnidadMedida() != null) {
            entity.setCveUnidadMedida(dto.getCveUnidadMedida());
        }

        if (dto.getReglaAplicable() != null) {
            entity.setReglaAplicable(dto.getReglaAplicable());
        }

        CatFraccionTtra updated = repository.save(entity);

        return toResponseDTO(updated);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }

    @Override
    public List<SelectDTO> listadoCategoriaTextil() {
        List<CatCategoriaTextil> categorias =
                categoriaTextilRepository.findAllByBlnActivoTrueOrderByDescripcionAsc();

        List<SelectDTO> resultado = new ArrayList<>();
        for (CatCategoriaTextil c : categorias) {
            SelectDTO dto = new SelectDTO();
            dto.setId(c.getId());
            dto.setNombre(c.getDescripcion());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatFraccionTtraResponseDTO toResponseDTO(CatFraccionTtra entity) {
        Long idCatTextil = entity.getIdCategoriaTextil() != null
                ? entity.getIdCategoriaTextil().getId() : null;
        String descCatTextil = entity.getIdCategoriaTextil() != null
                ? entity.getIdCategoriaTextil().getDescripcion() : null;

        String descTipoTramite = entity.getIdTipoTramite().getId() + " " +
                (entity.getIdTipoTramite().getDescModalidad() != null
                        ? entity.getIdTipoTramite().getDescModalidad()
                        : entity.getIdTipoTramite().getDescSubservicio());

        return CatFraccionTtraResponseDTO.builder()
                .id(entity.getId())
                .cveFraccion(entity.getCveFraccion().getCveFraccion())
                .descripcionFraccion(entity.getCveFraccion().getDescripcion())
                .idTipoTramite(entity.getIdTipoTramite().getId())
                .descTipoTramite(descTipoTramite)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .descFraccionAlt(entity.getDescFraccionAlt())
                .ideClasifPartida(entity.getIdeClasifPartida())
                .blnFraccionControlada(entity.getBlnFraccionControlada())
                .idCategoriaTextil(idCatTextil)
                .descripcionCategoriaTextil(descCatTextil)
                .factorConversion(entity.getFactorConversion())
                .valorEquivalencia(entity.getValorEquivalencia())
                .cveUnidadMedida(entity.getCveUnidadMedida())
                .reglaAplicable(entity.getReglaAplicable())
                .build();
    }
}
