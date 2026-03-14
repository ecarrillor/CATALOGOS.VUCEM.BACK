package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DictamenTramite.CatDictamenTramiteResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICaTDictamenTramiteService {
    PageResponseDTO<CatDictamenTramiteResponseDTO> listarDictamenTramite(String search, Pageable pageable);

    CatDictamenTramiteResponseDTO crearDictamenTramite(CatDictamenTramiteRequestDTO dto);

    CatDictamenTramiteResponseDTO findById(Long tt, Long dc);

    CatDictamenTramiteResponseDTO update(Long tt, Long dc, CatDictamenTramiteRequestDTO dto);

    List<SelectDTO> listadoTipoDictamen();
}
