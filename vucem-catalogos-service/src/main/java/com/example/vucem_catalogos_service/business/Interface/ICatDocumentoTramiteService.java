package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatDocumentoTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatDocumentoTramiteService {

    PageResponseDTO<CatDocumentoTramiteDTO> list(String search, Pageable pageable);

    CatDocumentoTramiteDTO findById(Short idTipoDoc, Long idTipoTramite);

    CatDocumentoTramiteDTO create(CatDocumentoTramiteDTO dto);

    CatDocumentoTramiteDTO update(Short idTipoDoc, Long idTipoTramite, CatDocumentoTramiteDTO dto);

    List<SelectDTO> listadoDeDocumentos();
}
