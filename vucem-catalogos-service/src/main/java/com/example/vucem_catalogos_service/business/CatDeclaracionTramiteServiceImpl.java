package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDeclaracionTramiteService;
import com.example.vucem_catalogos_service.model.dto.CatDeclaracionTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatDeclaracion;
import com.example.vucem_catalogos_service.model.entity.CatDeclaracionTramite;
import com.example.vucem_catalogos_service.model.entity.CatDeclaracionTramiteId;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatDeclaracionRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatDeclaracionTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatDeclaracionTramiteServiceImpl implements ICatDeclaracionTramiteService {

    @Autowired
    private ICatDeclaracionTramiteRepository iCatDeclaracionTramiteRepository;

    @Autowired
    private ICatDeclaracionRepository iCatDeclaracionRepository;

    @Autowired
    private ICatTipoTramiteRepository iCatTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatDeclaracionTramiteDTO> list(String search, Pageable pageable) {
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

        Page<CatDeclaracionTramiteDTO> page =
                iCatDeclaracionTramiteRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatDeclaracionTramiteDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatDeclaracionTramiteDTO findById(String cveDeclaracion, Integer idTipoTramite) {
        return iCatDeclaracionTramiteRepository
                .findByDeclaracionTramiteDTO(cveDeclaracion, idTipoTramite);
    }

    @Override
    public CatDeclaracionTramiteDTO create(CatDeclaracionTramiteDTO dto) {
        CatDeclaracionTramiteId id = new CatDeclaracionTramiteId();
        id.setCveDeclaracion(dto.getCveDeclaracion());
        id.setIdTipoTramite(dto.getIdTipoTramite());

        CatDeclaracion declaracion = iCatDeclaracionRepository.findById(dto.getCveDeclaracion())
                .orElseThrow(() -> new RuntimeException("Declaración no encontrada: " + dto.getCveDeclaracion()));

        CatDeclaracionTramite entity = new CatDeclaracionTramite();
        entity.setId(id);
        entity.setCveDeclaracion(declaracion);
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatDeclaracionTramite saved = iCatDeclaracionTramiteRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatDeclaracionTramiteDTO update(String cveDeclaracion, Integer idTipoTramite,
                                            CatDeclaracionTramiteDTO dto) {
        CatDeclaracionTramiteId id = new CatDeclaracionTramiteId();
        id.setCveDeclaracion(cveDeclaracion);
        id.setIdTipoTramite(idTipoTramite);

        CatDeclaracionTramite entity = iCatDeclaracionTramiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException(
                        "CatDeclaracionTramite no encontrado: "
                                + cveDeclaracion + " - " + idTipoTramite));

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }

        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }
        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        CatDeclaracionTramite updated = iCatDeclaracionTramiteRepository.save(entity);

        return mapToDTO(updated);
    }

    @Override
    public List<SelectDTO> listadoDeclaraciones() {
        List<CatDeclaracion> declaraciones = iCatDeclaracionRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatDeclaracion d : declaraciones) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(d.getCveDeclaracion());
            dto.setNombre(d.getDescDeclaracion());
            resultado.add(dto);
        }
        return resultado;
    }

    @Override
    public List<SelectDTO> listadoTiposTramite() {
        List<CatTipoTramite> tipos = iCatTipoTramiteRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatTipoTramite t : tipos) {
            SelectDTO dto = new SelectDTO();
            dto.setId(t.getId());
            dto.setNombre(t.getDescModalidad());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatDeclaracionTramiteDTO mapToDTO(CatDeclaracionTramite e) {
        return CatDeclaracionTramiteDTO.builder()
                .cveDeclaracion(e.getId().getCveDeclaracion())
                .idTipoTramite(e.getId().getIdTipoTramite())
                .descDeclaracion(e.getCveDeclaracion().getDescDeclaracion())
                .fecIniVigencia(e.getFecIniVigencia())
                .fecFinVigencia(e.getFecFinVigencia())
                .blnActivo(e.getBlnActivo())
                .build();
    }
}
