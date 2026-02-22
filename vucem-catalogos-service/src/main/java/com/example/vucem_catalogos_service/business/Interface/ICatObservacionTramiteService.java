package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.ObservacionTramite.CatObservacionTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatObservacionTramiteService {
    PageResponseDTO<CatObservacionTramiteResponseDTO> listarObservacionTramite(String search, Pageable pageable);

    CatObservacionTramiteResponseDTO crearObservacionTramite(CatObservacionTramiteRequestDTO dto);

    CatObservacionTramiteResponseDTO findById(Long id);

    CatObservacionTramiteResponseDTO update(Long id, CatObservacionTramiteRequestDTO dto);
}
