package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.MotivoTramite.CatMotivoTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatMotivoTramiteService {
    PageResponseDTO<CatMotivoTramiteResponseDTO> listarMotivoTramite(String search, Pageable pageable);

    CatMotivoTramiteResponseDTO crearCategoriaTextil(CatMotivoTramiteRequestDTO dto);

    CatMotivoTramiteResponseDTO findById(Long id);

    CatMotivoTramiteResponseDTO update(Long id, CatMotivoTramiteRequestDTO dto);
}
