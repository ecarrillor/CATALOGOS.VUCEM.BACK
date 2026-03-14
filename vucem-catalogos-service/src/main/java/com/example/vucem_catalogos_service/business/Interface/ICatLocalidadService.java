package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatLocalidadDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatLocalidadService {
    PageResponseDTO<CatLocalidadDTO> list(String search, Pageable pageable);

    CatLocalidadDTO findById(String cveLocalidad);

    CatLocalidadDTO create(CatLocalidadDTO dto);

    CatLocalidadDTO update(String cveLocalidad, CatLocalidadDTO dto);
}
