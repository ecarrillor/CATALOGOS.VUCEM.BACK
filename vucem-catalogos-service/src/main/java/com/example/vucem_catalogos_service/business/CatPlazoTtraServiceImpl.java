package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatPlazoTtraService;
import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatPlazoTtra;
import com.example.vucem_catalogos_service.model.entity.CatPlazoTtraId;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatPlazoTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

@Service
@Transactional
public class CatPlazoTtraServiceImpl implements ICatPlazoTtraService {

    @Autowired
    private ICatPlazoTtraRepository catPlazoTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatPlazoTtraDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatPlazoTtraDTO> page = catPlazoTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatPlazoTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
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
