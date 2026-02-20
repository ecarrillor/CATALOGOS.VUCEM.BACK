package com.example.vucem_catalogos_service.business.Interface;


import com.example.vucem_catalogos_service.model.dto.CatArancelProsecDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatArancelProsecService {
    PageResponseDTO<CatArancelProsecDTO> list(String search, Pageable pageable);

    CatArancelProsecDTO findById(String id, String cveSectorProsec);

    CatArancelProsecDTO create(CatArancelProsecDTO dto);

    CatArancelProsecDTO update(String cveFraccion, String cveSectorProsec, CatArancelProsecDTO dto);

    List<SelectDTO> listadoFraccionArancelaria();
}
