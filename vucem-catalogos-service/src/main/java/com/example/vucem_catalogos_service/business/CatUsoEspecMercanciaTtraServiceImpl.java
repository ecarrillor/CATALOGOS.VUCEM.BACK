package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatUsoEspecMercanciaTtraService;
import com.example.vucem_catalogos_service.model.dto.CatUsoEspecMercanciaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatTipoTramite;
import com.example.vucem_catalogos_service.model.entity.CatUsoEspecMercanciaTtra;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatUsoEspecMercanciaTtraRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CatUsoEspecMercanciaTtraServiceImpl implements ICatUsoEspecMercanciaTtraService {

    @Autowired
    private ICatUsoEspecMercanciaTtraRepository catUsoEspecMercanciaTtraRepository;

    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;

    @Override
    public PageResponseDTO<CatUsoEspecMercanciaTtraDTO> list(String search, Pageable pageable) {
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
        Page<CatUsoEspecMercanciaTtraDTO> page =
                catUsoEspecMercanciaTtraRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatUsoEspecMercanciaTtraDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatUsoEspecMercanciaTtraDTO findById(Short id) {
        return catUsoEspecMercanciaTtraRepository.findByUsoEspecMercanciaTtraDTO(id);
    }

    @Override
    public CatUsoEspecMercanciaTtraDTO create(CatUsoEspecMercanciaTtraDTO dto) {
        CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado"));

        CatUsoEspecMercanciaTtra entity = new CatUsoEspecMercanciaTtra();
        entity.setId(dto.getId());
        entity.setIdTipoTramite(tipoTramite);
        entity.setDescUsoEspMercancia(dto.getDescUsoEspMercancia());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setBlnReqRegistroSanitario(dto.getBlnReqRegistroSanitario());

        CatUsoEspecMercanciaTtra saved = catUsoEspecMercanciaTtraRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatUsoEspecMercanciaTtraDTO update(Short id, CatUsoEspecMercanciaTtraDTO dto) {
        CatUsoEspecMercanciaTtra entity = catUsoEspecMercanciaTtraRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Uso especial de mercancía de trámite no encontrado"));

        if (dto.getIdTipoTramite() != null) {
            CatTipoTramite tipoTramite = catTipoTramiteRepository.findById(dto.getIdTipoTramite())
                    .orElseThrow(() -> new RuntimeException("Tipo de trámite no encontrado"));
            entity.setIdTipoTramite(tipoTramite);
        }
        if (dto.getDescUsoEspMercancia() != null)
            entity.setDescUsoEspMercancia(dto.getDescUsoEspMercancia());
        if (dto.getFecIniVigencia() != null)
            entity.setFecIniVigencia(dto.getFecIniVigencia());
        if (dto.getFecFinVigencia() != null)
            entity.setFecFinVigencia(dto.getFecFinVigencia());
        if (dto.getBlnActivo() != null)
            entity.setBlnActivo(dto.getBlnActivo());
        if (dto.getBlnReqRegistroSanitario() != null)
            entity.setBlnReqRegistroSanitario(dto.getBlnReqRegistroSanitario());

        CatUsoEspecMercanciaTtra updated = catUsoEspecMercanciaTtraRepository.save(entity);
        return mapToDTO(updated);
    }

    private CatUsoEspecMercanciaTtraDTO mapToDTO(CatUsoEspecMercanciaTtra entity) {
        return CatUsoEspecMercanciaTtraDTO.builder()
                .id(entity.getId())
                .idTipoTramite(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getId() : null)
                .descServicio(entity.getIdTipoTramite() != null ? entity.getIdTipoTramite().getDescServicio() : null)
                .descUsoEspMercancia(entity.getDescUsoEspMercancia())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .blnReqRegistroSanitario(entity.getBlnReqRegistroSanitario())
                .build();
    }
}
