package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.CasFraccionTtra.CatCasFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatCasFraccionTtraService {

    PageResponseDTO<CatCasFraccionTtraResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable);

    CatCasFraccionTtraResponseDTO crear(CatCasFraccionTtraRequestDTO dto);

    CatCasFraccionTtraResponseDTO findById(Short id);

    CatCasFraccionTtraResponseDTO update(Short id, CatCasFraccionTtraRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<SelectDTO> listadoCas();
}
