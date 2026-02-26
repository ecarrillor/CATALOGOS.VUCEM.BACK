package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatFundamentoTtraService;
import com.example.vucem_catalogos_service.model.dto.CatFundamentoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatFundamentoTtra;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.persistence.repo.ICatFundamentoTtraRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatFundamentoTtraServiceImpl implements ICatFundamentoTtraService {

    @Autowired
    private ICatFundamentoTtraRepository catFundamentoTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatFundamentoTtraDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatFundamentoTtraDTO> page = catFundamentoTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatFundamentoTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatFundamentoTtraDTO findById(Short id) {
        return catFundamentoTtraRepository.findByFundamentoTtraDTO(id)
                .orElseThrow(() -> new RuntimeException("CatFundamentoTtra no encontrado con id: " + id));
    }

    @Override
    public CatFundamentoTtraDTO create(CatFundamentoTtraDTO dto) {
        CatFundamentoTtra entity = new CatFundamentoTtra();
        entity.setId(dto.getId());
        entity.setIdeTipoFundamentoTtra(dto.getIdeTipoFundamentoTtra());
        entity.setDescFundamento(dto.getDescFundamento());
        entity.setDescContenidoFundamento(dto.getDescContenidoFundamento());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTipoTramite no encontrado con id: " + dto.getIdTipoTramite()));
            entity.setIdTipoTramite(tipoTramite);
        }

        CatFundamentoTtra saved = catFundamentoTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatFundamentoTtraDTO update(Short id, CatFundamentoTtraDTO dto) {
        CatFundamentoTtra entity = catFundamentoTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("CatFundamentoTtra no encontrado con id: " + id));

        if (dto.getIdeTipoFundamentoTtra() != null) entity.setIdeTipoFundamentoTtra(dto.getIdeTipoFundamentoTtra());
        if (dto.getDescFundamento() != null) entity.setDescFundamento(dto.getDescFundamento());
        if (dto.getDescContenidoFundamento() != null) entity.setDescContenidoFundamento(dto.getDescContenidoFundamento());
        if (dto.getFecIniVigencia() != null) entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null) entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null) entity.setBlnActivo(dto.getBlnActivo());

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .orElseThrow(() -> new RuntimeException(
                            "CatTipoTramite no encontrado con id: " + dto.getIdTipoTramite()));
            entity.setIdTipoTramite(tipoTramite);
        }

        CatFundamentoTtra saved = catFundamentoTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatFundamentoTtraDTO mapToDTO(CatFundamentoTtra entity) {
        return CatFundamentoTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .nombreTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getDescSubservicio() : null)
                .ideTipoFundamentoTtra(entity.getIdeTipoFundamentoTtra())
                .descFundamento(entity.getDescFundamento())
                .descContenidoFundamento(entity.getDescContenidoFundamento())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .build();
    }
}
