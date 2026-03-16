package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.model.entity.CatFraccionTtraDescProd;
import com.example.vucem_catalogos_service.business.Interface.ICatFraccionTtraDescProdService;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatDescripcionProd;
import com.example.vucem_catalogos_service.persistence.repo.ICatDescripcionProdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionTtraDescProdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionTtraRepository;
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
public class CatFraccionTtraDescProdServiceImpl implements ICatFraccionTtraDescProdService {

    @Autowired
    private ICatFraccionTtraDescProdRepository repository;

    @Autowired
    private ICatDescripcionProdRepository descripcionProdRepository;

    @Autowired
    private ICatFraccionTtraRepository fraccionTtraRepository;

    @Override
    public PageResponseDTO<CatFraccionTtraDescProdResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable) {
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

        Page<CatFraccionTtraDescProdResponseDTO> page =
                repository.search(texto, activo, idTipoTramite, pageable);

        return PageResponseDTO.<CatFraccionTtraDescProdResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatFraccionTtraDescProdResponseDTO crear(CatFraccionTtraDescProdRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatFraccionTtraDescProd entity = new CatFraccionTtraDescProd();

        entity.setId(dto.getId());

        entity.setIdDescripcionProd(
                descripcionProdRepository
                        .findById(dto.getIdDescripcionProd())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Descripción de producto no encontrada"))
        );

        entity.setIdFraccionGob(
                fraccionTtraRepository
                        .findById(dto.getIdFraccionGob())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Fracción GOB no encontrada"))
        );

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);

        CatFraccionTtraDescProd saved = repository.save(entity);

        return toResponseDTO(saved);
    }

    @Override
    public CatFraccionTtraDescProdResponseDTO findById(Long id) {
        CatFraccionTtraDescProd entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));

        return toResponseDTO(entity);
    }

    @Override
    public CatFraccionTtraDescProdResponseDTO update(Long id, CatFraccionTtraDescProdRequestDTO dto) {

        CatFraccionTtraDescProd entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getIdDescripcionProd() != null) {
            entity.setIdDescripcionProd(
                    descripcionProdRepository
                            .findById(dto.getIdDescripcionProd())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Descripción de producto no encontrada"))
            );
        }

        if (dto.getIdFraccionGob() != null) {
            entity.setIdFraccionGob(
                    fraccionTtraRepository
                            .findById(dto.getIdFraccionGob())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Fracción GOB no encontrada"))
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

        CatFraccionTtraDescProd updated = repository.save(entity);

        return toResponseDTO(updated);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }

    @Override
    public List<ClasifProductoTraDTO> listadoFraccionTtra(Long idTipoTramite) {
        return fraccionTtraRepository.listadoFraccionTtraByTipoTramite(idTipoTramite);
    }

    @Override
    public List<SelectDTO> listadoDescripcionProd() {
        List<CatDescripcionProd> descripciones =
                descripcionProdRepository.findAllByBlnActivoTrueOrderByDescripcionProductoAsc();

        List<SelectDTO> resultado = new ArrayList<>();
        for (CatDescripcionProd d : descripciones) {
            SelectDTO selectDTO = new SelectDTO();
            selectDTO.setId(d.getId().longValue());
            selectDTO.setNombre(d.getDescripcionProducto());
            resultado.add(selectDTO);
        }
        return resultado;
    }

    private CatFraccionTtraDescProdResponseDTO toResponseDTO(CatFraccionTtraDescProd entity) {
        return CatFraccionTtraDescProdResponseDTO.builder()
                .idFraccionTtraDescProd(entity.getId())
                .idDescripcionProd(entity.getIdDescripcionProd().getId())
                .descripcionProducto(entity.getIdDescripcionProd().getDescripcionProducto())
                .idFraccionGob(entity.getIdFraccionGob().getId())
                .cveFraccion(entity.getIdFraccionGob().getCveFraccion().getCveFraccion())
                .descripcionFraccion(entity.getIdFraccionGob().getCveFraccion().getDescripcion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public ClasifProductoTraDTO lastFraccionTtraDescProd() {
        return repository.findTopByOrderByIdDesc()
                .map(e -> new ClasifProductoTraDTO(e.getId(), e.getIdDescripcionProd().getDescripcionProducto()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existen registros en Fracción Trámite Descripción Producto"));
    }
}
