package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatTipoProductoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatTipoProductoTtraService {

    PageResponseDTO<CatTipoProductoTtraDTO> list(String search, Pageable pageable);

    CatTipoProductoTtraDTO findById(Short id);

    CatTipoProductoTtraDTO create(CatTipoProductoTtraDTO dto);

    CatTipoProductoTtraDTO update(Short id, CatTipoProductoTtraDTO dto);
}
