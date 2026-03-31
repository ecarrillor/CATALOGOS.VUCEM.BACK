package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatLocalidadService;
import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.CatDelegMunSaveDTO;
import com.example.vucem_catalogos_service.model.dto.CatLocalidadDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDelegMun;
import com.example.vucem_catalogos_service.model.entity.CatLocalidad;
import com.example.vucem_catalogos_service.persistence.repo.ICatDelegMunRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatLocalidadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;
import java.util.Set;

@Service
@Transactional
public class CatLocalidadServiceImpl implements ICatLocalidadService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "cveLocalidad", "cveLocalidad",
            "nombreLocalidad", "nombre",
            "cp", "cp",
            "nombreMunicipio", "e.cveDelegMun.nombre"
    );

    private static final String DEFAULT_SORT_KEY = "cveLocalidad";

    private static final Set<String> TEXT_COLUMNS = Set.of(
    );

    @Autowired
    private ICatLocalidadRepository catLocalidadRepository;

    @Autowired
    private ICatDelegMunRepository catDelegMunRepository;

    @Override
    public PageResponseDTO<CatLocalidadDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
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

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS, TEXT_COLUMNS, DEFAULT_SORT_KEY);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), Sort.by(Sort.Direction.ASC, "cveLocalidad"));

        Page<CatLocalidadDTO> page =
                catLocalidadRepository.search(texto, activo, sortedPageable);

        return PageResponseDTO.<CatLocalidadDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatLocalidadDTO findById(String cveLocalidad) {
        return catLocalidadRepository.findByLocalidadDTO(cveLocalidad);
    }

    @Override
    public CatLocalidadDTO create(CatLocalidadDTO dto) {
        CatLocalidad entity = new CatLocalidad();

        entity.setCveLocalidad(dto.getCveLocalidad());

        entity.setNombre(dto.getNombreLocalidad());

        if (dto.getCveDelegMun() != null) {
            entity.setCveDelegMun(
                    catDelegMunRepository.findById(dto.getCveDelegMun())
                            .orElseThrow(() -> new RuntimeException(
                                    "Municipio no encontrado: " + dto.getCveDelegMun()))
            );
        }
        entity.setCp(dto.getCp());

        // 🔥 Fechas
        entity.setFecCaptura(LocalDate.now());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());

        entity.setBlnActivo(dto.getBlnActivo() != null ? dto.getBlnActivo() : true);

        entity.setSatTownCode(generarSatTownCode(entity));

        CatLocalidad saved = catLocalidadRepository.save(entity);

        return mapToDTO(saved);
    }

    @Override
    public CatLocalidadDTO update(String cveLocalidad, CatLocalidadDTO dto) {
        CatLocalidad entity = catLocalidadRepository.findById(cveLocalidad)
                .orElseThrow(() -> new RuntimeException(
                        "Localidad no encontrada: " + cveLocalidad));

        if (dto.getNombreLocalidad() != null) {
            entity.setNombre(dto.getNombreLocalidad());
        }

        if (dto.getCp() != null) {
            entity.setCp(dto.getCp());
        }

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }

        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }

        if (dto.getCveDelegMun() != null || dto.getCveLocalidad() != null) {
            entity.setSatTownCode(generarSatTownCode(entity));
        }

        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        if (dto.getCveDelegMun() != null) {
            entity.setCveDelegMun(
                    catDelegMunRepository.findById(dto.getCveDelegMun())
                            .orElseThrow(() -> new RuntimeException(
                                    "Municipio no encontrado: " + dto.getCveDelegMun()))
            );
        }

        CatLocalidad updated = catLocalidadRepository.save(entity);

        return mapToDTO(updated);
    }



    private CatLocalidadDTO mapToDTO(CatLocalidad entity) {
        return CatLocalidadDTO.builder()
                .cveLocalidad(entity.getCveLocalidad())
                .nombreLocalidad(entity.getNombre())
                .cveDelegMun(
                        entity.getCveDelegMun() != null
                                ? entity.getCveDelegMun().getCveDelegMun()
                                : null
                )
                .nombreMunicipio(
                        entity.getCveDelegMun() != null
                                ? entity.getCveDelegMun().getNombre()
                                : null
                )
                .cp(entity.getCp())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    private String generarSatTownCode(CatLocalidad entity) {

        String municipio = entity.getCveDelegMun().getCveDelegMun();
        String claveLoc = entity.getCveLocalidad();

        String ultimosTres = claveLoc.length() >= 3
                ? claveLoc.substring(claveLoc.length() - 3)
                : claveLoc;

        return municipio + ultimosTres;
    }
}
