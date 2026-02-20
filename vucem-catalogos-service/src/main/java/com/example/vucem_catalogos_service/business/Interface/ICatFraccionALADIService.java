package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionALADI.CatFraccionALADIResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatFraccionALADIService {
    PageResponseDTO<CatFraccionALADIResponseDTO> listarFraccionAladi(String search, Pageable pageable);

    CatFraccionALADIResponseDTO crearFraacionAladi(CatFraccionALADIRequestDTO dto);

    CatFraccionALADIResponseDTO findById(Long id);

    CatFraccionALADIResponseDTO update(Long id, CatFraccionALADIRequestDTO dto);
}
