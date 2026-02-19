package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CatAprobCertSeResponseDTO;
import com.example.vucem_catalogos_service.model.dto.CatArancelProsecDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatArancelProsecService {
    PageResponseDTO<CatArancelProsecDTO> list(String search, Pageable pageable);

    CatArancelProsecDTO findById(String id, String cveSectorProsec);

    CatArancelProsecDTO create(CatArancelProsecDTO dto);

    CatArancelProsecDTO update(String cveFraccion, String cveSectorProsec, CatArancelProsecDTO dto);
}
