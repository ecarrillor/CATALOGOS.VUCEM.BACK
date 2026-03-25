package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatSubpartidaFraccionService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectCatPartidaFraccion;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatCapituloFraccionRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatSubpartidaFraccionRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatPartidaFraccionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatSubpartidaFraccionServiceImpl implements ICatSubpartidaFraccionService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveSubpartidaFraccion", "id.cveSubpartidaFraccion",
            "nombrePartidaFraccion", "nombre"
    );

    @Autowired
    private ICatSubpartidaFraccionRepository catSubpartidaFraccionRepository;

    @Autowired
    private ICatPartidaFraccionRepository catPartidaFraccionRepository;

    @Autowired
    private ICatCapituloFraccionRepository catCapituloFraccionRepository;

    @Override
    public PageResponseDTO<CatSubpartidaFraccionDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if (search != null && !search.isBlank()) {
            String s = search.trim().toLowerCase();
            if (s.equals("activo")) {
                activo = true;
            } else if (s.equals("inactivo")) {
                activo = false;
            } else {
                texto = "%" + s + "%";
            }
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id.cveSubpartidaFraccion"));

        Page<CatSubpartidaFraccionDTO> page = catSubpartidaFraccionRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatSubpartidaFraccionDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatSubpartidaFraccionDTO findById(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion) {
        return catSubpartidaFraccionRepository.findBySubpartidaFraccionDTO(cveSubpartidaFraccion, cveCapituloFraccion, cvePartidaFraccion)
                .orElseThrow(() -> new RuntimeException(
                        "CatSubpartidaFraccion no encontrada con cveSubpartidaFraccion: " + cveSubpartidaFraccion
                                + ", cveCapituloFraccion: " + cveCapituloFraccion
                                + ", cvePartidaFraccion: " + cvePartidaFraccion));
    }

    @Override
    public CatSubpartidaFraccionDTO create(CatSubpartidaFraccionDTO dto) {
        CatSubpartidaFraccionId id = new CatSubpartidaFraccionId();
        id.setCveSubpartidaFraccion(dto.getCveSubpartidaFraccion());
        id.setCveCapituloFraccion(dto.getCveCapituloFraccion());
        id.setCvePartidaFraccion(dto.getCvePartidaFraccion());

        if (catSubpartidaFraccionRepository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"La Subpartida ya existe.");
        }

        CatPartidaFraccionId partidaId = new CatPartidaFraccionId();
        partidaId.setCveCapituloFraccion(dto.getCveCapituloFraccion());
        partidaId.setCvePartidaFraccion(dto.getCvePartidaFraccion());

        if (!catPartidaFraccionRepository.existsById(partidaId)) {
            throw new RuntimeException(
                    "CatPartidaFraccion no encontrada con cveCapituloFraccion: "
                            + dto.getCveCapituloFraccion()
                            + " y cvePartidaFraccion: "
                            + dto.getCvePartidaFraccion()
            );
        }

        CatSubpartidaFraccion entity = new CatSubpartidaFraccion();
        entity.setId(id);
        entity.setNombre(dto.getNombrePartidaFraccion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatSubpartidaFraccion saved = catSubpartidaFraccionRepository.save(entity);

        return mapToDTO(saved);
    }

    @Override
    public CatSubpartidaFraccionDTO update(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion, CatSubpartidaFraccionDTO dto) {
        CatSubpartidaFraccionId id = new CatSubpartidaFraccionId();
        id.setCveSubpartidaFraccion(cveSubpartidaFraccion);
        id.setCveCapituloFraccion(cveCapituloFraccion);
        id.setCvePartidaFraccion(cvePartidaFraccion);

        CatSubpartidaFraccion entity = catSubpartidaFraccionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatSubpartidaFraccion no encontrada con cveSubpartidaFraccion: " + cveSubpartidaFraccion
                                + ", cveCapituloFraccion: " + cveCapituloFraccion
                                + ", cvePartidaFraccion: " + cvePartidaFraccion));

        if (dto.getNombrePartidaFraccion() != null) {
            entity.setNombre(dto.getNombrePartidaFraccion());
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

        CatSubpartidaFraccion saved = catSubpartidaFraccionRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public List<SelectDTO> listadoCapituloFraccion() {
        List<CatCapituloFraccion> entidades = catCapituloFraccionRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatCapituloFraccion e : entidades) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(e.getCveCapituloFraccion());
            dto.setNombre(e.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    @Override
    public List<SelectCatPartidaFraccion> listadoPartidaFracciobn(String capitulo) {
        List<SelectCatPartidaFraccion> entidades = catPartidaFraccionRepository.findByBlnActivoTrue(capitulo);
        List<SelectCatPartidaFraccion> resultado = new ArrayList<>();

        for (SelectCatPartidaFraccion e : entidades) {
            SelectCatPartidaFraccion dto = new SelectCatPartidaFraccion();
            dto.setCvePartidaFraccion(e.getCvePartidaFraccion());
            dto.setNombre(e.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatSubpartidaFraccionDTO mapToDTO(CatSubpartidaFraccion entity) {
        return CatSubpartidaFraccionDTO.builder()
                .cveSubpartidaFraccion(entity.getId().getCveSubpartidaFraccion())
                .cveCapituloFraccion(entity.getId().getCveCapituloFraccion())
                .cvePartidaFraccion(entity.getId().getCvePartidaFraccion())
                .nombrePartidaFraccion(entity.getNombre())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
