package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTarifaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.time.Instant;

public interface ICatTarifaService {

    PageResponseDTO<CatTarifaDTO> list(String search, Pageable pageable);

    CatTarifaDTO findById(Long idTipoTramite, Instant fecIniVigencia, String ideTipoTarifa);

    CatTarifaDTO create(CatTarifaDTO dto);

    CatTarifaDTO update(Long idTipoTramite, Instant fecIniVigencia, String ideTipoTarifa, CatTarifaDTO dto);
}
