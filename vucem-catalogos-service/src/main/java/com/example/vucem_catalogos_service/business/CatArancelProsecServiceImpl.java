package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatArancelProsecService;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatArancelProsecDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.persistence.repo.ICatArancelProsecRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatFraccionArancelariaRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatSectorProsecRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatArancelProsecServiceImpl implements ICatArancelProsecService {
    @Autowired
    private ICatArancelProsecRepository catArancelProsecRepository;
    @Autowired
    private ICatFraccionArancelariaRepository catFraccionArancelariaRepository;
    @Autowired
    private ICatSectorProsecRepository catSectorProsecRepository;

    @Override
    public PageResponseDTO<CatArancelProsecDTO> list(String search, Pageable pageable) {
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
        Page<CatArancelProsecDTO> page =
                catArancelProsecRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatArancelProsecDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatArancelProsecDTO findById(String id, String cveSectorProsec) {
        return catArancelProsecRepository.findByCatAracel(id, cveSectorProsec);

    }

    @Override
    public CatArancelProsecDTO create(CatArancelProsecDTO dto) {
        CatFraccionArancelaria fraccion =
                catFraccionArancelariaRepository.findById(dto.getCveFraccion())
                        .orElseThrow(() -> new RuntimeException("Fracción no encontrada"));

        CatSectorProsec sector =
                catSectorProsecRepository.findById(dto.getCveSectorProsec())
                        .orElseThrow(() -> new RuntimeException("Sector PROSEC no encontrado"));

        CatArancelProsecId id = new CatArancelProsecId();
        id.setCveFraccion(dto.getCveFraccion());
        id.setCveSectorProsec(dto.getCveSectorProsec());

        // 🔹 Crear entidad
        CatArancelProsec entity = new CatArancelProsec();
        entity.setId(id);

        entity.setCveFraccion(fraccion);
        entity.setCveSectorProsec(sector);

        entity.setTasa(dto.getTasa());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatArancelProsec saved = catArancelProsecRepository.save(entity);

        return mapToDTO(saved);
    }




    private CatArancelProsecDTO mapToDTO(CatArancelProsec entity) {
        return CatArancelProsecDTO.builder()
                .cveFraccion(entity.getId().getCveFraccion())
                .cveSectorProsec(entity.getId().getCveSectorProsec())
                .sectorProsec(entity.getCveSectorProsec().getNombre()) // si existe
                .tasa(entity.getTasa())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }

    @Override
    public CatArancelProsecDTO update(String cveFraccion,
                                      String cveSectorProsec,
                                      CatArancelProsecDTO dto) {

        CatArancelProsecId id = new CatArancelProsecId();
        id.setCveFraccion(cveFraccion);
        id.setCveSectorProsec(cveSectorProsec);

        CatArancelProsec entity = catArancelProsecRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Registro no encontrado"));

        // 🔹 SOLO si viene en el request

        if (dto.getTasa() != null)
            entity.setTasa(dto.getTasa());

        if (dto.getFecIniVigencia() != null)
            entity.setFecIniVigencia(dto.getFecIniVigencia());

        if (dto.getFecFinVigencia() != null)
            entity.setFecFinVigencia(dto.getFecFinVigencia());

        if (dto.getBlnActivo() != null)
            entity.setBlnActivo(dto.getBlnActivo());

        CatArancelProsec updated = catArancelProsecRepository.save(entity);

        return mapToDTO(updated);
    }



}
