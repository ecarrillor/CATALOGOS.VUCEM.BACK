package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDiaNoLaborableService;
import com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDiaNoLaborable;
import com.example.vucem_catalogos_service.model.entity.CatDiaNoLaborableId;
import com.example.vucem_catalogos_service.persistence.repo.ICatCalendarioRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatDiaNoLaborableRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;

@Service
@Transactional
public class CatDiaNoLaborableServiceImpl implements ICatDiaNoLaborableService {

    @Autowired
    private ICatDiaNoLaborableRepository catDiaNoLaborableRepository;

    @Autowired
    private ICatCalendarioRepository catCalendarioRepository;

    @Override
    public PageResponseDTO<CatDiaNoLaborableDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatDiaNoLaborableDTO> page = catDiaNoLaborableRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatDiaNoLaborableDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatDiaNoLaborableDTO findById(Instant fecNoLaborable, String cveCalendario) {
        return catDiaNoLaborableRepository.findByDiaNoLaborableDTO(fecNoLaborable, cveCalendario)
                .orElseThrow(() -> new RuntimeException(
                        "CatDiaNoLaborable no encontrado para fecNoLaborable=" + fecNoLaborable + ", cveCalendario=" + cveCalendario));
    }

    @Override
    public CatDiaNoLaborableDTO create(CatDiaNoLaborableDTO dto) {
        CatDiaNoLaborableId id = new CatDiaNoLaborableId();
        id.setFecNoLaborable(dto.getFecNoLaborable());
        id.setCveCalendario(dto.getCveCalendario());

        CatDiaNoLaborable entity = new CatDiaNoLaborable();
        entity.setId(id);
        entity.setCveCalendario(
                catCalendarioRepository.findById(dto.getCveCalendario())
                        .orElseThrow(() -> new RuntimeException("CatCalendario no encontrado: " + dto.getCveCalendario()))
        );
        entity.setDescripcion(dto.getDescripcion());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        CatDiaNoLaborable saved = catDiaNoLaborableRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatDiaNoLaborableDTO update(Instant fecNoLaborable, String cveCalendario, CatDiaNoLaborableDTO dto) {
        CatDiaNoLaborableId pk = new CatDiaNoLaborableId();
        pk.setFecNoLaborable(fecNoLaborable);
        pk.setCveCalendario(cveCalendario);

        CatDiaNoLaborable entity = catDiaNoLaborableRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException(
                        "CatDiaNoLaborable no encontrado para fecNoLaborable=" + fecNoLaborable + ", cveCalendario=" + cveCalendario));

        if (dto.getDescripcion() != null) {
            entity.setDescripcion(dto.getDescripcion());
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

        CatDiaNoLaborable saved = catDiaNoLaborableRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatDiaNoLaborableDTO mapToDTO(CatDiaNoLaborable entity) {
        return CatDiaNoLaborableDTO.builder()
                .fecNoLaborable(entity.getId().getFecNoLaborable())
                .cveCalendario(entity.getId().getCveCalendario())
                .nombreCalendario(entity.getCveCalendario() != null ? entity.getCveCalendario().getNombre() : null)
                .descripcion(entity.getDescripcion())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
