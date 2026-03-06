package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraRequestDTO;
import com.example.vucem_catalogos_service.model.dto.RestriccionTtra.CatRestriccionTtraResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatRestriccionTtraService {

    PageResponseDTO<CatRestriccionTtraResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable);

    CatRestriccionTtraResponseDTO crear(CatRestriccionTtraRequestDTO dto);

    CatRestriccionTtraResponseDTO findById(Short id);

    CatRestriccionTtraResponseDTO update(Short id, CatRestriccionTtraRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

}
