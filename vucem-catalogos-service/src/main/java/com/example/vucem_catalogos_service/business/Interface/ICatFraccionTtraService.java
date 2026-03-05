package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtra.CatFraccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatFraccionTtraService {

    PageResponseDTO<CatFraccionTtraResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable);

    CatFraccionTtraResponseDTO crear(CatFraccionTtraRequestDTO dto);

    CatFraccionTtraResponseDTO findById(Long id);

    CatFraccionTtraResponseDTO update(Long id, CatFraccionTtraRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<SelectDTO> listadoCategoriaTextil();

    List<ClasifProductoTraDTO> selectCategoriaTextil(Long id);
}
