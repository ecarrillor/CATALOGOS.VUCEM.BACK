package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDependenciaService;
import com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.CatCalendario;
import com.example.vucem_catalogos_service.model.entity.CatDependencia;
import com.example.vucem_catalogos_service.persistence.repo.ICatCalendarioRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatDependenciaRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class CatDependenciaServiceImpl implements ICatDependenciaService {

    @Autowired
    private ICatDependenciaRepository iCatDependenciaRepository;

    @Autowired
    private ICatCalendarioRepository iCatCalendarioRepository;

    @Override
    public PageResponseDTO<CatDependenciaResponseDTO> listarDependencia(String search, Pageable pageable) {
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

        Page<CatDependenciaResponseDTO> page =
                iCatDependenciaRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatDependenciaResponseDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatDependenciaResponseDTO crearDependencia(CatDependenciaRequestDTO dto) {


        if (iCatDependenciaRepository.existsById(dto.getCveDependencia())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "El id ya existe"
            );
        }

        CatDependencia entity = new CatDependencia();

        entity.setId(dto.getCveDependencia());

        entity.setCveCalendario(
                iCatCalendarioRepository
                        .findById(dto.getCveCalendario())
                        .orElseThrow(() -> new RuntimeException("Calendario no encontrado")));

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setAcronimo(dto.getAcronimo());
        entity.setBlnTramitesVu(dto.getTramiteVU());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setNombre(dto.getNombreDependencia());

        CatDependencia saved = iCatDependenciaRepository.save(entity);

        return CatDependenciaResponseDTO.builder()
                .cveDependencia(saved.getId())
                .nombreDependencia(saved.getNombre())
                .acronimo(saved.getAcronimo())
                .idCalendario(saved.getCveCalendario().getCveCalendario())
                .nombreCalendario(saved.getCveCalendario().getNombre())
                .tramiteVU(saved.getBlnTramitesVu())
                .fecIniVigencia(saved.getFecIniVigencia())
                .fecFinVigencia(saved.getFecFinVigencia())
                .blnActivo(saved.getBlnActivo())
                .build();
    }

    @Override
    public CatDependenciaResponseDTO findById(Long id) {
        CatDependencia entity = iCatDependenciaRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "Registro no encontrado con id: " + id
                        )
                );

        return CatDependenciaResponseDTO.builder()
                .cveDependencia(entity.getId())
                .nombreDependencia(entity.getNombre())
                .acronimo(entity.getAcronimo())
                .idCalendario(entity.getCveCalendario().getCveCalendario())
                .nombreCalendario(entity.getCveCalendario().getNombre())
                .tramiteVU(entity.getBlnTramitesVu())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatDependenciaResponseDTO update(Long id, CatDependenciaRequestDTO dto) {
        CatDependencia entity = iCatDependenciaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        entity.setCveCalendario(
                iCatCalendarioRepository
                        .findById(dto.getCveCalendario())
                        .orElseThrow(() -> new RuntimeException("Calendario no encontrado")));

        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setAcronimo(dto.getAcronimo());
        entity.setBlnTramitesVu(dto.getTramiteVU());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setNombre(dto.getNombreDependencia());

        CatDependencia updated = iCatDependenciaRepository.save(entity);

        return CatDependenciaResponseDTO.builder()
                .cveDependencia(updated.getId())
                .nombreDependencia(updated.getNombre())
                .acronimo(updated.getAcronimo())
                .idCalendario(updated.getCveCalendario().getCveCalendario())
                .nombreCalendario(updated.getCveCalendario().getNombre())
                .tramiteVU(updated.getBlnTramitesVu())
                .fecIniVigencia(updated.getFecIniVigencia())
                .fecFinVigencia(updated.getFecFinVigencia())
                .blnActivo(updated.getBlnActivo())
                .build();
    }

    @Override
    public List<SelectDTO> listadoCalendarios() {
        List<CatCalendario> productos = iCatCalendarioRepository.findAll();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatCalendario producto : productos) {
            SelectDTO dto = new SelectDTO();
            dto.setCve(producto.getCveCalendario());
            dto.setNombre(producto.getNombre());
            resultado.add(dto);
        }
        return resultado;
    }
}
