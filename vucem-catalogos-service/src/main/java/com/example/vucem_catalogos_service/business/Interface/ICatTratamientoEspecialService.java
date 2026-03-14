package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTratamientoEspecialDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatTratamientoEspecialService {

    PageResponseDTO<CatTratamientoEspecialDTO> list(String search, Pageable pageable);

    CatTratamientoEspecialDTO findById(Short id);

    CatTratamientoEspecialDTO create(CatTratamientoEspecialDTO dto);

    CatTratamientoEspecialDTO update(Short id, CatTratamientoEspecialDTO dto);
}
