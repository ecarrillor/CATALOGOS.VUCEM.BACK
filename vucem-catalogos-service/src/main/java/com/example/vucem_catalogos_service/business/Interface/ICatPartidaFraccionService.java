package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPartidaFraccionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatCapituloFraccion;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatPartidaFraccionService {
    PageResponseDTO<CatPartidaFraccionDTO> list(String search, Pageable pageable);
    CatPartidaFraccionDTO findById(String cveCapituloFraccion, String cvePartidaFraccion);
    CatPartidaFraccionDTO create(CatPartidaFraccionDTO dto);
    CatPartidaFraccionDTO update(String cveCapituloFraccion, String cvePartidaFraccion, CatPartidaFraccionDTO dto);
    List<CatCapituloFraccion> listCapitulosFraccionActivos();
}
