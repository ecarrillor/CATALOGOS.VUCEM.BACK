package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatSubpartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatSubpartidaFraccionService {

    PageResponseDTO<CatSubpartidaFraccionDTO> list(String search, Pageable pageable);

    CatSubpartidaFraccionDTO findById(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion);

    CatSubpartidaFraccionDTO create(CatSubpartidaFraccionDTO dto);

    CatSubpartidaFraccionDTO update(String cveSubpartidaFraccion, String cveCapituloFraccion, String cvePartidaFraccion, CatSubpartidaFraccionDTO dto);
}
