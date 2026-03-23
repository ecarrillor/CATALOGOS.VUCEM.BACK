package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.*;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatVidaSilvestreService {

    PageResponseDTO<CatVidaSilvestreDTO> list(String search, Long tipo, String sortBy, String sortDir, Pageable pageable);

    CatVidaSilvestreDTO findById(Integer id);

    CatVidaSilvestreDTO create(CatVidaSilvestreRequestDTO dto, Long tipo);

    CatVidaSilvestreDTO update(Integer id, CatVidaSilvestreDTO dto);

    List<CatEspecie> listEspeciesActivas();

    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<ClasifProductoTraDTO> listadoGenero();

    ClasifProductoTraDTO lastVidaSilvestre();
}
