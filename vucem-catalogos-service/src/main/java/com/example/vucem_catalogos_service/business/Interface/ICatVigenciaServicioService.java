package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatVigenciaServicioDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatVigenciaServicioService {
    PageResponseDTO<CatVigenciaServicioDTO> list(String search, String sortBy, String sortDir, Pageable pageable);
    CatVigenciaServicioDTO findById(Short id);
    CatVigenciaServicioDTO create(CatVigenciaServicioDTO dto);
    CatVigenciaServicioDTO update(Short id, CatVigenciaServicioDTO dto);

    List<SelectDTO> listadoCriterioOrigen();
}
