package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatRestricDescProdService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatDescripcionProd;
import com.example.vucem_catalogos_service.model.entity.CatRestricDescProd;
import com.example.vucem_catalogos_service.model.entity.CatRestriccionTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatDescripcionProdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatRestricDescProdRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatRestriccionTtraRepository;
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
public class CatRestricDescProdServiceImpl implements ICatRestricDescProdService {

    @Autowired
    private ICatRestricDescProdRepository repository;

    @Autowired
    private ICatRestriccionTtraRepository restriccionTtraRepository;

    @Autowired
    private ICatDescripcionProdRepository descripcionProdRepository;

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idRestricDescProd", "a.id",
            "descRestriccion", "a.idRestriccionTtra.descRestriccion",
            "descripcionProducto", "a.idDescripcionProd.descripcionProducto"
    );

    @Override
    public PageResponseDTO<CatRestricDescProdResponseDTO> listAll(String search, Long idTipoTramite, String sortBy, String sortDir, Pageable pageable) {
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

        Page<CatRestricDescProdResponseDTO> page =
                repository.search(texto, activo, idTipoTramite, pageableWithSort);

        return PageResponseDTO.<CatRestricDescProdResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatRestricDescProdResponseDTO crear(CatRestricDescProdRequestDTO dto) {

        if (repository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatRestricDescProd entity = new CatRestricDescProd();

        entity.setId(dto.getId());

        entity.setIdRestriccionTtra(
                restriccionTtraRepository
                        .findById(dto.getIdRestriccionTtra())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Restricción no encontrada"))
        );

        entity.setIdDescripcionProd(
                descripcionProdRepository
                        .findById(dto.getIdDescripcionProd())
                        .orElseThrow(() -> new ResponseStatusException(
                                HttpStatus.NOT_FOUND, "Descripción de producto no encontrada"))
        );

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);

        CatRestricDescProd saved = repository.save(entity);

        return toResponseDTO(saved);
    }

    @Override
    public CatRestricDescProdResponseDTO findById(Long id) {
        CatRestricDescProd entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Registro no encontrado con id: " + id
                ));

        return toResponseDTO(entity);
    }

    @Override
    public CatRestricDescProdResponseDTO update(Long id, CatRestricDescProdRequestDTO dto) {

        CatRestricDescProd entity = repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Registro no encontrado con id: " + id
                ));

        if (dto.getIdRestriccionTtra() != null) {
            entity.setIdRestriccionTtra(
                    restriccionTtraRepository
                            .findById(dto.getIdRestriccionTtra())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Restricción no encontrada"))
            );
        }

        if (dto.getIdDescripcionProd() != null) {
            entity.setIdDescripcionProd(
                    descripcionProdRepository
                            .findById(dto.getIdDescripcionProd())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Descripción de producto no encontrada"))
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

        CatRestricDescProd updated = repository.save(entity);

        return toResponseDTO(updated);
    }

    @Override
    public List<ClasifProductoTraDTO> listadoTipoTramite() {
        return repository.listadoTipoTramite();
    }

    @Override
    public List<ClasifProductoTraDTO> listadoRestriccionTtra(Long idTipoTramite) {
        List<CatRestriccionTtra> restricciones =
                restriccionTtraRepository.findByBlnActivoTrueAndIdTipoTramiteId(idTipoTramite);

        List<ClasifProductoTraDTO> resultado = new ArrayList<>();
        for (CatRestriccionTtra r : restricciones) {
            resultado.add(new ClasifProductoTraDTO(r.getId().longValue(), r.getDescRestriccion()));
        }
        return resultado;
    }

    @Override
    public List<SelectDTO> listadoDescripcionProd() {
        List<CatDescripcionProd> descripciones =
                descripcionProdRepository.findAllByBlnActivoTrueOrderByDescripcionProductoAsc();

        List<SelectDTO> resultado = new ArrayList<>();
        for (CatDescripcionProd d : descripciones) {
            SelectDTO dto = new SelectDTO();
            dto.setId(d.getId().longValue());
            dto.setNombre(d.getDescripcionProducto());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatRestricDescProdResponseDTO toResponseDTO(CatRestricDescProd entity) {
        return CatRestricDescProdResponseDTO.builder()
                .idRestricDescProd(entity.getId())
                .idRestriccionTtra(entity.getIdRestriccionTtra().getId())
                .descRestriccion(entity.getIdRestriccionTtra().getDescRestriccion())
                .idDescripcionProd(entity.getIdDescripcionProd().getId())
                .descripcionProducto(entity.getIdDescripcionProd().getDescripcionProducto())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public ClasifProductoTraDTO lastRestricDescProd() {
        return repository.findTopByOrderByIdDesc()
                .map(e -> new ClasifProductoTraDTO(e.getId(), e.getIdDescripcionProd().getDescripcionProducto()))
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "No existen registros en Restricciones Descripción Producto"));
    }
}
