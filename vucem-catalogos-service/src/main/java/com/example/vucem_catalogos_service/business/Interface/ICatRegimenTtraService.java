package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatRegimenTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatRegimenTtraService {
    PageResponseDTO<CatRegimenTtraDTO> list(String search, Pageable pageable);
    CatRegimenTtraDTO findById(Short id);
    CatRegimenTtraDTO create(CatRegimenTtraDTO dto);
    CatRegimenTtraDTO update(Short id, CatRegimenTtraDTO dto);
}
