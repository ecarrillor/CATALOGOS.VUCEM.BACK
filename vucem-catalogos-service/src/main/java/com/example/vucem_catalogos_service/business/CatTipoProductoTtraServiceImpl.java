package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatTipoProductoTtraService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatTipoProductoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoProductoTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoCertificado;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoProductoTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatTipoProductoTtraServiceImpl implements ICatTipoProductoTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id",
            "descTipoProducto", "descTipoProducto",
            "nombreTipoTramite", "idTipoTramite.descSubservicio"
    );

    @Autowired
    private ICatTipoProductoTtraRepository catTipoProductoTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Autowired
    private ICatTipoCertificado catTipoCertificado;

    @Override
    public PageResponseDTO<CatTipoProductoTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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

        Page<CatTipoProductoTtraDTO> page = catTipoProductoTtraRepository.search(texto, activo, sortedPageable);

        // 🔥 Transformar IDs a descripción
        List<CatTipoProductoTtraDTO> contentTransformado =
                page.getContent().stream()
                        .peek(dto -> dto.setIdeTipoCertificadoMerc(
                                obtenerDescripcion(dto.getIdeTipoCertificadoMerc())
                        ))
                        .toList();

        return PageResponseDTO.<CatTipoProductoTtraDTO>builder()
                .content(contentTransformado) // 👈 IMPORTANTE usar la lista transformada
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatTipoProductoTtraDTO findById(Short id) {
        return catTipoProductoTtraRepository.findByTipoProductoTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatTipoProductoTtra no encontrado con id: " + id));
    }

    @Override
    public CatTipoProductoTtraDTO create(CatTipoProductoTtraDTO dto) {
        if (catTipoProductoTtraRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatTipoProductoTtra entity = new CatTipoProductoTtra();
        entity.setIdTipoTramite(
                catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new ResponseStatusException(HttpStatus.BAD_REQUEST,"CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
        );
        entity.setId(dto.getId());
        entity.setDescTipoProducto(dto.getDescTipoProducto());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());

        CatTipoProductoTtra saved = catTipoProductoTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatTipoProductoTtraDTO update(Short id, CatTipoProductoTtraDTO dto) {
        CatTipoProductoTtra entity = catTipoProductoTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatTipoProductoTtra no encontrado con id: " + id));

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }
        if (dto.getDescTipoProducto() != null) {
            entity.setDescTipoProducto(dto.getDescTipoProducto());
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
        if (dto.getIdeTipoCertificadoMerc() != null) {
            entity.setIdeTipoCertificadoMerc(dto.getIdeTipoCertificadoMerc());
        }

        CatTipoProductoTtra saved = catTipoProductoTtraRepository.save(entity);
        return mapToDTO(saved);
    }



    private CatTipoProductoTtraDTO mapToDTO(CatTipoProductoTtra entity) {
        return CatTipoProductoTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null
                        ? entity.getIdTipoTramite().getId()
                        : null)
                .nombreTipoTramite(entity.getIdTipoTramite().getDescSubservicio())
                .descTipoProducto(entity.getDescTipoProducto())
                .ideTipoCertificadoMerc(
                        obtenerDescripcion(entity.getIdeTipoCertificadoMerc())
                )
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    private String obtenerDescripcion(String id) {

        if (id == null) {
            return null;
        }

        return switch (id) {
            case "TICERM.AN" -> "Animal";
            case "TICERM.SOA" -> "Producto o subproducto de origen animal";
            case "TICERM.QFBA" -> "QFBA (Productos Químicos, Farmacéuticos, Biológicos y Alimenticios)";
            default -> id;
        };
    }
}
