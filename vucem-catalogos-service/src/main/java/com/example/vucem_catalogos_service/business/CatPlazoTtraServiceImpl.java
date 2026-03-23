package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPlazoTtraService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPlazoTtra;
import com.example.vucem_catalogos_service.model.entity.CatPlazoTtraId;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatPlazoTtraRepository;
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

import java.util.Map;

@Service
@Transactional
public class CatPlazoTtraServiceImpl implements ICatPlazoTtraService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idePlazoVigencia", "id.idePlazoVigencia",
            "nombreTipoTramite", "tipoTramite.descModalidad"
    );

    @Autowired
    private ICatPlazoTtraRepository catPlazoTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatPlazoTtraDTO> list(String search, int page, int size, String sortBy, String sortDir) {
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
                ? PageRequest.of(page, size, sort)
                : PageRequest.of(page, size, Sort.by(Sort.Direction.ASC, "id.idePlazoVigencia"));

        Page<CatPlazoTtraDTO> pageResult = catPlazoTtraRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatPlazoTtraDTO>builder()
                .content(pageResult.getContent())
                .page(pageResult.getNumber())
                .size(pageResult.getSize())
                .totalElements(pageResult.getTotalElements())
                .totalPages(pageResult.getTotalPages())
                .last(pageResult.isLast())
                .build();
    }

    @Override
    public CatPlazoTtraDTO findById(Long idTipoTramite, String idePlazoVigencia) {
        return catPlazoTtraRepository.findByPlazoTtraDTO(idTipoTramite, idePlazoVigencia)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.BAD_REQUEST, "CatPlazoTtra no encontrado para idTipoTramite=" + idTipoTramite + ", idePlazoVigencia=" + idePlazoVigencia));
    }

    @Override
    public CatPlazoTtraDTO create(CatPlazoTtraDTO dto) {
        CatPlazoTtraId id = new CatPlazoTtraId();
        id.setIdTipoTramite(dto.getIdTipoTramite());
        id.setIdePlazoVigencia(dto.getIdePlazoVigencia());

        if (catPlazoTtraRepository.existsById(id)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ya existe un registro con ese tipo de trámite y plazo de vigencia"
            );
        }


        CatPlazoTtra entity = new CatPlazoTtra();
        entity.setId(id);
        CatTipoTramite tramite =
                catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                        .orElseThrow(() -> new RuntimeException("Tipo Tramite no existe"));

        entity.setTipoTramite(tramite);
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatPlazoTtra saved = catPlazoTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatPlazoTtraDTO update(Long idTipoTramite, String idePlazoVigencia, CatPlazoTtraDTO dto) {
        CatPlazoTtraId pk = new CatPlazoTtraId();
        pk.setIdTipoTramite(idTipoTramite);
        pk.setIdePlazoVigencia(idePlazoVigencia);

        CatPlazoTtra entity = catPlazoTtraRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException(
                        "CatPlazoTtra no encontrado para idTipoTramite=" + idTipoTramite + ", idePlazoVigencia=" + idePlazoVigencia));

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatPlazoTtra saved = catPlazoTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatPlazoTtraDTO mapToDTO(CatPlazoTtra entity) {
        return CatPlazoTtraDTO.builder()
                .idTipoTramite(entity.getId().getIdTipoTramite())
                .idePlazoVigencia(entity.getId().getIdePlazoVigencia())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .nombreTipoTramite(entity.getTipoTramite().getId() != null
                        ? entity.getTipoTramite().getDescModalidad(): null)
                .build();
    }
}
