package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaRequestDTO;
import com.example.vucem_catalogos_service.model.dto.Dependencia.CatDependenciaResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatDependenciaService {
    PageResponseDTO<CatDependenciaResponseDTO> listarDependencia(String search, Pageable pageable);

    CatDependenciaResponseDTO crearDependencia(CatDependenciaRequestDTO dto);

    CatDependenciaResponseDTO findById(Long id);

    CatDependenciaResponseDTO update(Long id, CatDependenciaRequestDTO dto);

    List<SelectDTO> listadoCalendarios();
}
