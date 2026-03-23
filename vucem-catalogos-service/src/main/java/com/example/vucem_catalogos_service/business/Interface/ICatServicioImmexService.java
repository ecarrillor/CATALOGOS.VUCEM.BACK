package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatServicioImmexDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatServicioImmexService {
    PageResponseDTO<CatServicioImmexDTO> list(String search, String sortBy, String sortDir, Pageable pageable);
    CatServicioImmexDTO findById(Short id);
    CatServicioImmexDTO create(CatServicioImmexDTO dto);
    CatServicioImmexDTO update(Short id, CatServicioImmexDTO dto);
}
