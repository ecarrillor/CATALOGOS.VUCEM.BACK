package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatVigenciaServicioService {
    PageResponseDTO<CatVigenciaServicioDTO> list(String search, Pageable pageable);
    CatVigenciaServicioDTO findById(Short id);
    CatVigenciaServicioDTO create(CatVigenciaServicioDTO dto);
    CatVigenciaServicioDTO update(Short id, CatVigenciaServicioDTO dto);
}
