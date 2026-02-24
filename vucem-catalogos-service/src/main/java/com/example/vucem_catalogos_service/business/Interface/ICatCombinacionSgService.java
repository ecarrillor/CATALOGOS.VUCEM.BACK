package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatCombinacionSgDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatCombinacionSgService {
    PageResponseDTO<CatCombinacionSgDTO> list(String search, Pageable pageable);
    CatCombinacionSgDTO findById(Long id);
    CatCombinacionSgDTO create(CatCombinacionSgDTO dto);
    CatCombinacionSgDTO update(Long id, CatCombinacionSgDTO dto);
}
