package com.example.vucem_catalogos_service.business;

import com.example.vucem_catalogos_service.core.util.SortValidator;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import com.example.vucem_catalogos_service.model.entity.*;
import com.example.vucem_catalogos_service.business.Interface.ICatDocumentoTramiteService;
import com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.persistence.repo.ICatDocumentoTramiteRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoDocumentoRepository;
import com.example.vucem_catalogos_service.persistence.repo.ICatTipoTramiteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class CatDocumentoTramiteServiceImpl implements ICatDocumentoTramiteService {

    private static final Map<String, String> ALLOWED_SORT_COLUMNS = Map.of(
            "idTipoDoc", "td.id",
            "nombreTipoDoc", "td.nombre",
            "idTipoTramite", "tr.id",
            "descModalidad", "tr.descModalidad"
    );

    @Autowired
    private ICatDocumentoTramiteRepository catDocumentoTramiteRepository;
    @Autowired
    private ICatTipoDocumentoRepository catTipoDocumentoRepository;
    @Autowired
    private ICatTipoTramiteRepository catTipoTramiteRepository;
    @Override
    public PageResponseDTO<CatDocumentoTramiteDTO> list(String search, String sortBy, String sortDir, Pageable pageable) {
        Boolean activo = null;
        String texto = null;

        if ("activo".equalsIgnoreCase(search)) {
            activo = true;
        } else if ("inactivo".equalsIgnoreCase(search)) {
            activo = false;
        } else {
            texto = search;
        }

        Sort sort = SortValidator.buildSort(sortBy, sortDir, ALLOWED_SORT_COLUMNS);
        Pageable sortedPageable = sort.isSorted()
                ? PageRequest.of(pageable.getPageNumber(), pageable.getPageSize(), sort)
                : pageable;

        Page<CatDocumentoTramiteDTO> page = catDocumentoTramiteRepository.search(texto, activo, sortedPageable);

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
    public CatDocumentoTramiteDTO findById(Short idTipoDoc, Long idTipoTramite) {
        return catDocumentoTramiteRepository.findByDocumentoTramiteDTO(idTipoDoc, idTipoTramite)
                .orElseThrow(() -> new RuntimeException(
                        "CatDocumentoTramite no encontrado para idTipoDoc=" + idTipoDoc + ", idTipoTramite=" + idTipoTramite));
    }

    @Override
    public CatDocumentoTramiteDTO create(CatDocumentoTramiteDTO dto) {
        if (dto.getIdTipoDoc() == null || dto.getIdTipoTramite() == null) {
            throw new RuntimeException("idTipoDoc e idTipoTramite son obligatorios");
        }

        CatTipoDocumento tipoDocumento = catTipoDocumentoRepository
                .findById(dto.getIdTipoDoc())
                .orElseThrow(() -> new RuntimeException(
                        "TipoDocumento no encontrado: " + dto.getIdTipoDoc()));

        CatTipoTramite tipoTramite = catTipoTramiteRepository
                .findById(Long.valueOf(dto.getIdTipoTramite()))
                .orElseThrow(() -> new RuntimeException(
                        "TipoTramite no encontrado: " + dto.getIdTipoTramite()));

        CatDocumentoTramiteId id = new CatDocumentoTramiteId(
                dto.getIdTipoDoc(),
                dto.getIdTipoTramite()
        );

        CatDocumentoTramite entity = new CatDocumentoTramite();

        entity.setId(id);

        entity.setIdTipoDoc(tipoDocumento);
        entity.setIdTipoTramite(tipoTramite);

        entity.setBlnEspecifico(dto.getBlnEspecifico());
        entity.setIdeClasificacionDocumento(dto.getIdeClasificacionDocumento());
        entity.setIdeTipoSolicitanteRfe(dto.getIdeTipoSolicitanteRfe());
        entity.setFecIniVigencia(dto.getFecIniVigencia());
        entity.setFecFinVigencia(dto.getFecFinVigencia());
        entity.setBlnActivo(true);
        entity.setBlnSoloAnexar(true);
        entity.setIdeReglaAnexado(null);//preguntar como se llena

        CatDocumentoTramite saved = catDocumentoTramiteRepository.save(entity);

        return mapToDTO(saved);
    }

    @Override
    public CatDocumentoTramiteDTO update(Short idTipoDoc, Long idTipoTramite, CatDocumentoTramiteDTO dto) {
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

    @Override
    public List<SelectDTO> listadoDeDocumentos() {
        List<CatTipoDocumento> catTipoDocumentos = catTipoDocumentoRepository.findByBlnActivoTrue();
        List<SelectDTO> resultado = new ArrayList<>();

        for (CatTipoDocumento d : catTipoDocumentos) {
            SelectDTO dto = new SelectDTO();
            dto.setId(Long.valueOf(d.getId()));
            dto.setNombre(d.getNombre());
            resultado.add(dto);
        }
        return resultado;
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
