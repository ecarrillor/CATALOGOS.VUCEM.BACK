package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatMedioTransporteTtraService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatMedioTransporteTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatMedioTransporteTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatMedioTransporteTtraRepository;
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
public class CatMedioTransporteTtraServiceImpl implements ICatMedioTransporteTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id"
    );

    @Autowired
    private ICatMedioTransporteTtraRepository catMedioTransporteTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatMedioTransporteTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        Page<CatMedioTransporteTtraDTO> page = catMedioTransporteTtraRepository.search(texto, activo, sortedPageable);

        List<CatMedioTransporteTtraDTO> contentTransformado =
                page.getContent().stream()
                        .peek(dto -> dto.setIdeMedioTransporteGob(
                                obtenerDescripcion(dto.getIdeMedioTransporteGob())
                        ))
                        .toList();

        return PageResponseDTO.<CatMedioTransporteTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatMedioTransporteTtraDTO findById(Integer id) {
        CatMedioTransporteTtraDTO dto = catMedioTransporteTtraRepository
                .findByMedioTransporteTtraDTO(id)
                .orElseThrow(() ->
                        new RuntimeException("CatMedioTransporteTtra no encontrado con id: " + id)
                );

        dto.setIdeMedioTransporteGob(
                obtenerDescripcion(dto.getIdeMedioTransporteGob())
        );

        return dto;
    }

    @Override
    public CatMedioTransporteTtraDTO create(CatMedioTransporteTtraDTO dto) {

        if (catMedioTransporteTtraRepository.existsById(dto.getId())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }
        CatMedioTransporteTtra entity = new CatMedioTransporteTtra();
        entity.setId(dto.getId());
        entity.setIdeMedioTransporteGob(dto.getIdeMedioTransporteGob());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        CatMedioTransporteTtra saved = catMedioTransporteTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatMedioTransporteTtraDTO update(Integer id, CatMedioTransporteTtraDTO dto) {
        CatMedioTransporteTtra entity = catMedioTransporteTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatMedioTransporteTtra no encontrado con id: " + id));

        if (dto.getIdeMedioTransporteGob() != null) {
            entity.setIdeMedioTransporteGob(dto.getIdeMedioTransporteGob());
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
        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
            );
        }

        CatMedioTransporteTtra saved = catMedioTransporteTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatMedioTransporteTtraDTO mapToDTO(CatMedioTransporteTtra entity) {
        return CatMedioTransporteTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null
                        ? entity.getIdTipoTramite().getId()
                        : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null
                        ? entity.getIdTipoTramite().getDescModalidad()
                        : null)
                .ideMedioTransporteGob(
                        obtenerDescripcion(entity.getIdeMedioTransporteGob())
                )
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    private String obtenerDescripcion(String id) {
        return switch (id) {
            case "MEDTRG.AE" -> "Aéreo";
            case "MEDTRG.CA" -> "Cables";
            case "MEDTRG.CR" -> "Carretero";
            case "MEDTRG.DU" -> "Ductos";
            case "MEDTRG.FR" -> "Ferroviario";
            case "MEDTRG.MR" -> "Marítimo";
            case "MEDTRG.OTR" -> "Otros";
            case "MEDTRG.PO" -> "Postal";
            case "MEDTRG.TE" -> "Terrestre";
            case "MEDTRG.TU" -> "Tubería";
            default -> id;
        };
    }
}
