package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Parametro.CatParametroResponseDTO;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


public interface ICatParametroService {
    PageResponseDTO<CatParametroResponseDTO> listarParametro(String search, Pageable pageable);

    CatParametroResponseDTO crearParametro(CatParametroRequestDTO dto);

    CatParametroResponseDTO findById(String id);

    CatParametroResponseDTO update(String id, CatParametroRequestDTO dto);
}
