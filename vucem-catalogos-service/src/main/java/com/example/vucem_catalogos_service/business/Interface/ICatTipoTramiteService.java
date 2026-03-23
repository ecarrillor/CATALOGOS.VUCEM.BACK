package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTipoTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatTipoTramiteService {

    PageResponseDTO<CatTipoTramiteDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatTipoTramiteDTO findById(Long id);

    CatTipoTramiteDTO create(CatTipoTramiteDTO dto);

    CatTipoTramiteDTO update(Long id, CatTipoTramiteDTO dto);
}
