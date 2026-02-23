package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.business.Interface.ICatDocumentoTramiteService;
import com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatDocumentoTramite;
import com.example.vucem_catalogos_service.model.entity.CatDocumentoTramiteId;
import com.example.vucem_catalogos_service.persistence.repo.ICatDocumentoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class CatDocumentoTramiteServiceImpl implements ICatDocumentoTramiteService {

    @Autowired
    private ICatDocumentoTramiteRepository catDocumentoTramiteRepository;

    @Override
    public PageResponseDTO<CatDocumentoTramiteDTO> list(String search, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Page<CatDocumentoTramiteDTO> page = catDocumentoTramiteRepository.search(texto, activo, pageable);

        return PageResponseDTO.<CatDocumentoTramiteDTO>builder()
                .content(page.getContent())
                .page(page.getNumber())
                .size(page.getSize())
                .totalElements(page.getTotalElements())
                .totalPages(page.getTotalPages())
                .last(page.isLast())
                .build();
    }

    @Override
    public CatDocumentoTramiteDTO findById(Short idTipoDoc, Integer idTipoTramite) {
        return catDocumentoTramiteRepository.findByDocumentoTramiteDTO(idTipoDoc, idTipoTramite)
                .orElseThrow(() -> new RuntimeException(
                        "CatDocumentoTramite no encontrado para idTipoDoc=" + idTipoDoc + ", idTipoTramite=" + idTipoTramite));
    }

    @Override
    public CatDocumentoTramiteDTO create(CatDocumentoTramiteDTO dto) {
        CatDocumentoTramiteId id = new CatDocumentoTramiteId();
        id.setIdTipoDoc(dto.getIdTipoDoc());
        id.setIdTipoTramite(dto.getIdTipoTramite());

        CatDocumentoTramite entity = new CatDocumentoTramite();
        entity.setId(id);
        entity.setBlnEspecifico(dto.getBlnEspecifico());
        entity.setIdeClasificacionDocumento(dto.getIdeClasificacionDocumento());
        entity.setIdeTipoSolicitanteRfe(dto.getIdeTipoSolicitanteRfe());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(dto.getBlnActivo());
        entity.setBlnSoloAnexar(dto.getBlnSoloAnexar());
        entity.setIdeReglaAnexado(dto.getIdeReglaAnexado());

        CatDocumentoTramite saved = catDocumentoTramiteRepository.save(entity);
        return mapToDTO(saved);
    }

    @Override
    public CatDocumentoTramiteDTO update(Short idTipoDoc, Integer idTipoTramite, CatDocumentoTramiteDTO dto) {
        CatDocumentoTramiteId pk = new CatDocumentoTramiteId();
        pk.setIdTipoDoc(idTipoDoc);
        pk.setIdTipoTramite(idTipoTramite);

        CatDocumentoTramite entity = catDocumentoTramiteRepository.findById(pk)
                .orElseThrow(() -> new RuntimeException(
                        "CatDocumentoTramite no encontrado para idTipoDoc=" + idTipoDoc + ", idTipoTramite=" + idTipoTramite));

        if (dto.getBlnEspecifico() != null) {
            entity.setBlnEspecifico(dto.getBlnEspecifico());
        }
        if (dto.getIdeClasificacionDocumento() != null) {
            entity.setIdeClasificacionDocumento(dto.getIdeClasificacionDocumento());
        }
        if (dto.getIdeTipoSolicitanteRfe() != null) {
            entity.setIdeTipoSolicitanteRfe(dto.getIdeTipoSolicitanteRfe());
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
        if (dto.getBlnSoloAnexar() != null) {
            entity.setBlnSoloAnexar(dto.getBlnSoloAnexar());
        }
        if (dto.getIdeReglaAnexado() != null) {
            entity.setIdeReglaAnexado(dto.getIdeReglaAnexado());
        }

        CatDocumentoTramite saved = catDocumentoTramiteRepository.save(entity);
        return mapToDTO(saved);
    }

    private CatDocumentoTramiteDTO mapToDTO(CatDocumentoTramite entity) {
        return CatDocumentoTramiteDTO.builder()
                .idTipoDoc(entity.getId().getIdTipoDoc())
                .idTipoTramite(entity.getId().getIdTipoTramite())
                .blnEspecifico(entity.getBlnEspecifico())
                .ideClasificacionDocumento(entity.getIdeClasificacionDocumento())
                .ideTipoSolicitanteRfe(entity.getIdeTipoSolicitanteRfe())
                .fecIniVigencia(entity.getFecIniVigencia())
                .fecFinVigencia(entity.getFecFinVigencia())
                .blnActivo(entity.getBlnActivo())
                .blnSoloAnexar(entity.getBlnSoloAnexar())
                .ideReglaAnexado(entity.getIdeReglaAnexado())
                .build();
    }
}
