package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPaisRestriccionTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatPaisRestriccionTtraService {
    PageResponseDTO<CatPaisRestriccionTtraDTO> list(String search, Pageable pageable);
    CatPaisRestriccionTtraDTO findById(Integer id);
    CatPaisRestriccionTtraDTO create(CatPaisRestriccionTtraDTO dto);
    CatPaisRestriccionTtraDTO update(Integer id, CatPaisRestriccionTtraDTO dto);
}
