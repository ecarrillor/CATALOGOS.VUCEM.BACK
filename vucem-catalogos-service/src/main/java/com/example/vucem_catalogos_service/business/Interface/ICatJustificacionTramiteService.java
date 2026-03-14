package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.JustificacionTramite.CatJustificacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatJustificacionTramiteService {
    PageResponseDTO<CatJustificacionTramiteResponseDTO> listarJustificacionTramite(String search, Pageable pageable);

    CatJustificacionTramiteResponseDTO crearJustificacionTramite(CatJustificacionTramiteRequestDTO dto);

    CatJustificacionTramiteResponseDTO findById(Long id);

    CatJustificacionTramiteResponseDTO update(Long id, CatJustificacionTramiteRequestDTO dto);
}
