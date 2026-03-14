package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatScianDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatScianService {

    PageResponseDTO<CatScianDTO> list(String search, Pageable pageable);

    CatScianDTO findById(String cveScian);

    CatScianDTO create(CatScianDTO dto);

    CatScianDTO update(String cveScian, CatScianDTO dto);
}
