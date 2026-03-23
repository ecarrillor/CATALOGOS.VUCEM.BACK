package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatUnidadMedidaTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatUnidadMedidaTtraService {

    PageResponseDTO<CatUnidadMedidaTtraDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatUnidadMedidaTtraDTO findById(Integer id);

    CatUnidadMedidaTtraDTO create(CatUnidadMedidaTtraDTO dto);

    CatUnidadMedidaTtraDTO update(Integer id, CatUnidadMedidaTtraDTO dto);
}
