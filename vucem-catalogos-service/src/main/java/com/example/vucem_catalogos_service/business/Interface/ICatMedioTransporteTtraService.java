package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatMedioTransporteTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatMedioTransporteTtraService {

    PageResponseDTO<CatMedioTransporteTtraDTO> list(String search, Pageable pageable);

    CatMedioTransporteTtraDTO findById(Integer id);

    CatMedioTransporteTtraDTO create(CatMedioTransporteTtraDTO dto);

    CatMedioTransporteTtraDTO update(Integer id, CatMedioTransporteTtraDTO dto);
}
