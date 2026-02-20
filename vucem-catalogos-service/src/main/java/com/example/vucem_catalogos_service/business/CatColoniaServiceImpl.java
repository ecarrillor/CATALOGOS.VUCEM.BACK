package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatColoniaService;
import com.example.vucem_catalogos_service.model.dto.CatColoniaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatColonia;
import com.example.vucem_catalogos_service.persistence.repo.ICatColoniaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatDelegMunRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatLocalidadRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional
public class CatColoniaServiceImpl implements ICatColoniaService {

    @Autowired
    private ICatColoniaRepository catColoniaRepository;

    @Autowired
    private ICatDelegMunRepository catDelegMunRepository;

    @Autowired
    private ICatLocalidadRepository catLocalidadRepository;

    @Override
    public PageResponseDTO<CatColoniaDTO> list(String nombre, String cp, String municipio, String nombrePais, Boolean blnActivo, Integer page, Integer size) {
        if (page == null && size == null) {
            List<CatColoniaDTO> all = catColoniaRepository.searchAll(nombre, cp, municipio, nombrePais, blnActivo);
            return PageResponseDTO.<CatColoniaDTO>builder()
                    .content(all)
                    .page(0)
                    .size(all.size())
                    .totalElements((long) all.size())
                    .totalPages(1)
                    .last(true)
                    .build();
        }

        Pageable pageable = PageRequest.of(page != null ? page : 0, size != null ? size : 20);
        Page<CatColoniaDTO> pg = catColoniaRepository.search(nombre, cp, municipio, nombrePais, blnActivo, pageable);

        return PageResponseDTO.<CatColoniaDTO>builder()
                .content(pg.getContent())
                .page(pg.getNumber())
                .size(pg.getSize())
                .totalElements(pg.getTotalElements())
                .totalPages(pg.getTotalPages())
                .last(pg.isLast())
                .build();
    }

    @Override
    public CatColoniaDTO findById(String cveColonia) {
        CatColoniaDTO dto = catColoniaRepository.findByColoniaDTO(cveColonia);
        if (dto == null) {
            throw new RuntimeException("Colonia no encontrada: " + cveColonia);
        }
        return dto;
    }

    @Override
    public CatColoniaDTO create(CatColoniaDTO dto) {
        CatColonia entity = new CatColonia();
        entity.setCveColonia(dto.getCveColonia());
        entity.setNombre(dto.getNombre());
        entity.setFecCaptura(LocalDate.now());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setCp(dto.getCp());

        String cveColonia = dto.getCveColonia();
        String ultimosTres = cveColonia.length() >= 3
                ? cveColonia.substring(cveColonia.length() - 3)
                : cveColonia;
        entity.setSatColonyCd(dto.getCp() + ultimosTres);

        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getCveDelegMun() != null) {
            entity.setCveDelegMun(
                    catDelegMunRepository.findById(dto.getCveDelegMun())
                            .orElseThrow(() -> new RuntimeException("Municipio no encontrado: " + dto.getCveDelegMun()))
            );
        }

        if (dto.getCveLocalidad() != null) {
            entity.setCveLocalidad(
                    catLocalidadRepository.findById(dto.getCveLocalidad())
                            .orElseThrow(() -> new RuntimeException("Localidad no encontrada: " + dto.getCveLocalidad()))
            );
        }

        CatColonia saved = catColoniaRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatColoniaDTO update(String cveColonia, CatColoniaDTO dto) {
        CatColonia entity = catColoniaRepository.findById(cveColonia)
                .orElseThrow(() -> new RuntimeException("Colonia no encontrada: " + cveColonia));

        if (dto.getNombre() != null) {
            entity.setNombre(dto.getNombre());
        }

        if (dto.getFecCaptura() != null) {
            entity.setFecCaptura(dto.getFecCaptura());
        }

        if (dto.getFecIniVigencia() != null) {
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        }

        if (dto.getFecFinVigencia() != null) {
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        }

        if (dto.getCp() != null) {
            entity.setCp(dto.getCp());
            String clave = entity.getCveColonia();
            String ultimosTres = clave.length() >= 3
                    ? clave.substring(clave.length() - 3)
                    : clave;
            entity.setSatColonyCd(dto.getCp() + ultimosTres);
        }

        if (dto.getBlnActivo() != null) {
            entity.setBlnActivo(dto.getBlnActivo());
        }

        if (dto.getCveDelegMun() != null) {
            entity.setCveDelegMun(
                    catDelegMunRepository.findById(dto.getCveDelegMun())
                            .orElseThrow(() -> new RuntimeException("Municipio no encontrado: " + dto.getCveDelegMun()))
            );
        }

        if (dto.getCveLocalidad() != null) {
            entity.setCveLocalidad(
                    catLocalidadRepository.findById(dto.getCveLocalidad())
                            .orElseThrow(() -> new RuntimeException("Localidad no encontrada: " + dto.getCveLocalidad()))
            );
        }

        CatColonia updated = catColoniaRepository.save(entity);
        return mapToDTO(updated);
    }

    @Override
    public List<CatColoniaDTO> findByCp(String cp) {
        return catColoniaRepository.findByCp(cp);
    }

    private CatColoniaDTO mapToDTO(CatColonia entity) {
        return CatColoniaDTO.builder()
                .cveColonia(entity.getCveColonia())
                .nombre(entity.getNombre())
                .municipio(entity.getCveDelegMun() != null ? entity.getCveDelegMun().getNombre() : null)
                .localidad(entity.getCveLocalidad() != null ? entity.getCveLocalidad().getNombre() : null)
                .cp(entity.getCp())
                .fecCaptura(entity.getFecCaptura())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .cveDelegMun(entity.getCveDelegMun() != null ? entity.getCveDelegMun().getCveDelegMun() : null)
                .cveLocalidad(entity.getCveLocalidad() != null ? entity.getCveLocalidad().getCveLocalidad() : null)
                .build();
    }
}
