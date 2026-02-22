package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatSeccionTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatSeccionTtraService {
    PageResponseDTO<CatSeccionTtraDTO> list(String search, Pageable pageable);
    CatSeccionTtraDTO findById(Integer id);
    CatSeccionTtraDTO create(CatSeccionTtraDTO dto);
    CatSeccionTtraDTO update(Integer id, CatSeccionTtraDTO dto);
}
