package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatUnidadAdministrativaService;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaDTO;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatDependencia;
import com.example.vucem_catalogos_service.model.entity.CatEntidad;
import com.example.vucem_catalogos_service.model.entity.CatUnidadAdministrativa;
import com.example.vucem_catalogos_service.persistence.repo.ICatAprobCertSeRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatDependenciaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatEntidadRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUnidadAdministrativaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatUnidadAdministrativaServiceImpl implements ICatUnidadAdministrativaService {

    @Autowired
    private ICatAprobCertSeRepository catAprobCertSeRepository;

    @Autowired
    private ICatUnidadAdministrativaRepository iCatUnidadAdministrativaRepository;

    @Autowired
    private ICatEntidadRepository iCatEntidadRepository;

    @Autowired
    private ICatDependenciaRepository iCatDependenciaRepository;

    @Override
    public List<CatUnidadAdministrativaNameDTO> findByAll() {
        return catAprobCertSeRepository.findByName();
    }

    @Override
    public PageResponseDTO<CatUnidadAdministrativaDTO> list(String search, Pageable pageable) {
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

        Page<CatUnidadAdministrativaDTO> page =
                iCatUnidadAdministrativaRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatUnidadAdministrativaDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatUnidadAdministrativaDTO findById(String cveUnidadAdministrativa) {
        return iCatUnidadAdministrativaRepository
                .findByUnidadAdministrativaDTO(cveUnidadAdministrativa);
    }

    @Override
    public CatUnidadAdministrativaDTO create(CatUnidadAdministrativaDTO dto) {
        CatUnidadAdministrativa entity = new CatUnidadAdministrativa();
        entity.setCveUnidadAdministrativa(dto.getCveUnidadAdministrativa());
        entity.setIdeTipoUnidadAdministrativa(dto.getIdeTipoUnidadAdministrativa());

        if (dto.getCveUnidadAdministrativaR() != null) {
            entity.setCveUnidadAdministrativaR(
                    iCatUnidadAdministrativaRepository.findById(dto.getCveUnidadAdministrativaR())
                            .orElseThrow(() -> new RuntimeException(
                                    "Unidad administrativa padre no encontrada: " + dto.getCveUnidadAdministrativaR()))
            );
        }

        entity.setNivel(dto.getNivel());
        entity.setAcronimo(dto.getAcronimo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        if (dto.getCveEntidad() != null) {
            entity.setCveEntidad(
                    iCatEntidadRepository.findById(dto.getCveEntidad())
                            .orElseThrow(() -> new RuntimeException(
                                    "Entidad no encontrada: " + dto.getCveEntidad()))
            );
        }

        entity.setIdDependencia(
                iCatDependenciaRepository.findById(dto.getIdDependencia())
                        .orElseThrow(() -> new RuntimeException(
                                "Dependencia no encontrada: " + dto.getIdDependencia()))
        );

        entity.setBlnFronteriza(dto.getBlnFronteriza());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatUnidadAdministrativa saved = iCatUnidadAdministrativaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatUnidadAdministrativaDTO update(String cveUnidadAdministrativa,
                                              CatUnidadAdministrativaDTO dto) {
        CatUnidadAdministrativa entity = iCatUnidadAdministrativaRepository
                .findById(cveUnidadAdministrativa)
                .orElseThrow(() -> new RuntimeException(
                        "CatUnidadAdministrativa no encontrada: " + cveUnidadAdministrativa));

        entity.setIdeTipoUnidadAdministrativa(dto.getIdeTipoUnidadAdministrativa());

        if (dto.getCveUnidadAdministrativaR() != null) {
            entity.setCveUnidadAdministrativaR(
                    iCatUnidadAdministrativaRepository.findById(dto.getCveUnidadAdministrativaR())
                            .orElseThrow(() -> new RuntimeException(
                                    "Unidad administrativa padre no encontrada: " + dto.getCveUnidadAdministrativaR()))
            );
        } else {
            entity.setCveUnidadAdministrativaR(null);
        }

        entity.setNivel(dto.getNivel());
        entity.setAcronimo(dto.getAcronimo());
        entity.setNombre(dto.getNombre());
        entity.setDescripcion(dto.getDescripcion());

        if (dto.getCveEntidad() != null) {
            entity.setCveEntidad(
                    iCatEntidadRepository.findById(dto.getCveEntidad())
                            .orElseThrow(() -> new RuntimeException(
                                    "Entidad no encontrada: " + dto.getCveEntidad()))
            );
        } else {
            entity.setCveEntidad(null);
        }

        entity.setIdDependencia(
                iCatDependenciaRepository.findById(dto.getIdDependencia())
                        .orElseThrow(() -> new RuntimeException(
                                "Dependencia no encontrada: " + dto.getIdDependencia()))
        );

        entity.setBlnFronteriza(dto.getBlnFronteriza());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatUnidadAdministrativa updated = iCatUnidadAdministrativaRepository.save(entity);
        return mapToDTO(updated);
    }

    @Override
    public List<SelectDTO> listadoEntidades() {
        List<CatEntidad> entidades = iCatEntidadRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatEntidad e : entidades) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(e.getCveEntidad());
            dto.setNombre(e.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    @Override
    public List<SelectDTO> listadoDependencias() {
        List<CatDependencia> dependencias = iCatDependenciaRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatDependencia d : dependencias) {
            SelectDTO dto = new SelectDTO();
            dto.setId(d.getId().longValue());
            dto.setNombre(d.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }

    private CatUnidadAdministrativaDTO mapToDTO(CatUnidadAdministrativa e) {
        return CatUnidadAdministrativaDTO.builder()
                .cveUnidadAdministrativa(e.getCveUnidadAdministrativa())
                .ideTipoUnidadAdministrativa(e.getIdeTipoUnidadAdministrativa())
                .cveUnidadAdministrativaR(e.getCveUnidadAdministrativaR() != null
                        ? e.getCveUnidadAdministrativaR().getCveUnidadAdministrativa() : null)
                .nivel(e.getNivel())
                .acronimo(e.getAcronimo())
                .nombre(e.getNombre())
                .descripcion(e.getDescripcion())
                .cveEntidad(e.getCveEntidad() != null ? e.getCveEntidad().getCveEntidad() : null)
                .nombreEntidad(e.getCveEntidad() != null ? e.getCveEntidad().getNombre() : null)
                .idDependencia(e.getIdDependencia().getId())
                .nombreDependencia(e.getIdDependencia().getNombre())
                .blnFronteriza(e.getBlnFronteriza())
                .fecIniVigencia(e.getFecIniVigencia())
                .fecFinVigencia(e.getFecFinVigencia())
                .blnActivo(e.getBlnActivo())
                .build();
    }
}
