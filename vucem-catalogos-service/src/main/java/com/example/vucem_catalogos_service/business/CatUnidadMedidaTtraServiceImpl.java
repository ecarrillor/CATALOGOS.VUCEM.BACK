package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadMedidaTtraService;
import com.example.vucem_catalogos_service.model.dto.CatUnidadMedidaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatUnidadMedidaTtra;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadMedidaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadMedidaTtraRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

@Service
@Transactional
public class CatUnidadMedidaTtraServiceImpl implements ICatUnidadMedidaTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "id", "id",
            "cveUnidadMedida", "cveUnidadMedida.cveUnidadMedida",
            "descripcionUnidadMedida", "cveUnidadMedida.descripcion",
            "idTipoTramite", "idTipoTramite.id",
            "nombreTipoTramite", "idTipoTramite.descModalidad"
    );

    @Autowired
    private ICatUnidadMedidaTtraRepository catUnidadMedidaTtraRepository;

    @Autowired
    private ICatUnidadMedidaRepository catUnidadMedidaRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatUnidadMedidaTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "id"));

        Page<CatUnidadMedidaTtraDTO> page = catUnidadMedidaTtraRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatUnidadMedidaTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatUnidadMedidaTtraDTO findById(Integer id) {
        return catUnidadMedidaTtraRepository.findByUnidadMedidaTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatUnidadMedidaTtra no encontrada con id: " + id));
    }

    @Override
    public CatUnidadMedidaTtraDTO create(CatUnidadMedidaTtraDTO dto) {
        CatUnidadMedidaTtra entity = new CatUnidadMedidaTtra();
        entity.setId(dto.getId());
        entity.setCveUnidadMedida(
                catUnidadMedidaRepository.findById(dto.getCveUnidadMedida())
                        .orElseThrow(() -> new RuntimeException("CatUnidadMedida no encontrada: " + dto.getCveUnidadMedida()))
        );
        entity.setIdTipoTramite(
                catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
        );
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);

        CatUnidadMedidaTtra saved = catUnidadMedidaTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatUnidadMedidaTtraDTO update(Integer id, CatUnidadMedidaTtraDTO dto) {
        CatUnidadMedidaTtra entity = catUnidadMedidaTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatUnidadMedidaTtra no encontrada con id: " + id));

        if (dto.getCveUnidadMedida() != null) {
            entity.setCveUnidadMedida(
                    catUnidadMedidaRepository.findById(dto.getCveUnidadMedida())
                            .orElseThrow(() -> new RuntimeException("CatUnidadMedida no encontrada: " + dto.getCveUnidadMedida()))
            );
        }
        if (dto.getIdTipoTramite() != null) {
            entity.setIdTipoTramite(
                    catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                            .orElseThrow(() -> new RuntimeException("CatTipoTramite no encontrado: " + dto.getIdTipoTramite()))
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

        CatUnidadMedidaTtra saved = catUnidadMedidaTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatUnidadMedidaTtraDTO mapToDTO(CatUnidadMedidaTtra entity) {
        return CatUnidadMedidaTtraDTO.builder()
                .id(entity.getId())
                .cveUnidadMedida(entity.getCveUnidadMedida() != null ? entity.getCveUnidadMedida().getCveUnidadMedida() : null)
                .descripcionUnidadMedida(entity.getCveUnidadMedida() != null ? entity.getCveUnidadMedida().getDescripcion() : null)
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getNombre() : null)
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
