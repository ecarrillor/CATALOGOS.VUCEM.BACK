package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatFraccionTtraService;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatCategoriaTextil;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import com.example.vucem_catalogos_service.model.entity.CatFraccionTtra;
import com.example.vucem_catalogos_service.model.entity.CatUnidadMedida;
import com.example.vucem_catalogos_service.persistence.repo.ICatCategoriaTextilRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionArancelariaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import com.example.vucem_catalogos_service.core.util.SortValidator;
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
public class CatFraccionTtraServiceImpl implements ICatFraccionTtraService {

    @Autowired
    private ICatFraccionTtraRepository repository;

    @Autowired
    private ICatFraccionArancelariaRepository fraccionArancelariaRepository;

    @Autowired
    private ICatTipoTramiteRepository tipoTramiteRepository;

    @Autowired
    private ICatCategoriaTextilRepository categoriaTextilRepository;

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "a.id",
            "cveFraccion", "fra.cveFraccion",
            "idTipoTramite", "tt.id",
            "descTipoTramite", "tt.descSubservicio",
            "descripcionFraccion", "fra.descripcion",
            "factorConversion", "a.factorConversion",
            "valorEquivalencia", "a.valorEquivalencia",
            "cveUnidadMedida", "a.cveUnidadMedida",
            "descripcionCategoriaTextil", "cat.descripcion",
            "descFraccionAlt", "a.descFraccionAlt"
    );

    @Override
    public PageResponseDTO<CatFraccionTtraResponseDTO> listAll(String search, Long idTipoTramite, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = "%" + search.trim().toLowerCase() + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isUnsorted() ? pageable
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort);

        Page<CatFraccionTtraResponseDTO> page =
                repository.search(texto, activo, idTipoTramite, sortedPageable);

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

        // Opcional
        if (dto.getIdCategoriaTextil() != null) {
            entity.setIdCategoriaTextil(
                    categoriaTextilRepository
                            .findById(dto.getIdCategoriaTextil())
                            .orElseThrow(() -> new ResponseStatusException(
                                    HttpStatus.NOT_FOUND, "Categoría textil no encontrada"))
            );
        }

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setBlnActivo(dto.getBlnActivo() != null ? dto.getBlnActivo() : true);

        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getDescFraccionAlt() != null) entity.setDescFraccionAlt(dto.getDescFraccionAlt());
        if (dto.getIdeClasifPartida() != null) entity.setIdeClasifPartida(dto.getIdeClasifPartida());
        if (dto.getBlnFraccionControlada() != null) entity.setBlnFraccionControlada(dto.getBlnFraccionControlada());
        if (dto.getFactorConversion() != null) entity.setFactorConversion(dto.getFactorConversion());
        if (dto.getValorEquivalencia() != null) entity.setValorEquivalencia(dto.getValorEquivalencia());
        if (dto.getCveUnidadMedida() != null) entity.setCveUnidadMedida(dto.getCveUnidadMedida());
        if (dto.getReglaAplicable() != null) entity.setReglaAplicable(dto.getReglaAplicable());

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
        return tipoTramiteRepository.listadoTipoTramite();
    }

    @Override
    public List<SelectDTO> listadoCategoriaTextil() {
        List<CatCategoriaTextil> categorias =
                categoriaTextilRepository.findAllByBlnActivoTrueOrderByDescripcionAsc();

        List<SelectDTO> resultado = new ArrayList<>();
        for (CatCategoriaTextil c : categorias) {
            if (c.getDescripcion() != null && !c.getDescripcion().trim().isEmpty()) {

                SelectDTO dto = new SelectDTO();
                dto.setId(c.getId());
                dto.setNombre(c.getDescripcion());
                resultado.add(dto);
            }
        }
        return resultado;
    }

    @Override
    public List<ClasifProductoTraDTO> selectCategoriaTextil(Long id) {
        List<ClasifProductoTraDTO> productos = categoriaTextilRepository.selectCategoriaTextil(id);
        List<ClasifProductoTraDTO> resultado = new ArrayList<>();

        for (ClasifProductoTraDTO producto : productos) {
            ClasifProductoTraDTO dto = new ClasifProductoTraDTO();
            dto.setNombre(producto.getNombre());
            dto.setId(producto.getId());
            resultado.add(dto);
        }
        return resultado;
    }

    @Override
    public ClasifProductoTraDTO lastFraccionTtra() {
        CatFraccionTtra ultimo = repository.findTopByOrderByIdDesc();
        ClasifProductoTraDTO clasifProductoTraDTO = new ClasifProductoTraDTO();
        clasifProductoTraDTO.setId(ultimo.getId());
        clasifProductoTraDTO.setNombre(ultimo.getDescFraccionAlt());
        return clasifProductoTraDTO;
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
