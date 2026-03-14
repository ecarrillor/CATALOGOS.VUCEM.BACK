package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTipoRfcDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatTipoRfcService {

    PageResponseDTO<CatTipoRfcDTO> list(String search, Pageable pageable);

    CatTipoRfcDTO findById(String rfc, String ideTipoRfc);

    CatTipoRfcDTO create(CatTipoRfcDTO dto);

    CatTipoRfcDTO update(String rfc, String ideTipoRfc, CatTipoRfcDTO dto);
}
