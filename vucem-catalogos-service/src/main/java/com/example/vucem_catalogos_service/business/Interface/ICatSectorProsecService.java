package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatSectorProsecDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatSectorProsecService {

    PageResponseDTO<CatSectorProsecDTO> list(String search, Pageable pageable);

    CatSectorProsecDTO findById(String cveSectorProsec);

    CatSectorProsecDTO create(CatSectorProsecDTO dto);

    CatSectorProsecDTO update(String cveSectorProsec, CatSectorProsecDTO dto);
}
