package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreDTO;
import com.example.vucem_catalogos_service.model.dto.CatVidaSilvestreResponseDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.entity.CatEspecie;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatVidaSilvestreService {

    PageResponseDTO<CatVidaSilvestreDTO> list(String search, Long tipo, Pageable pageable);

    CatVidaSilvestreDTO findById(Integer id);

    CatVidaSilvestreDTO create(CatVidaSilvestreResponseDTO dto, Long tipo);

    CatVidaSilvestreDTO update(Integer id, CatVidaSilvestreDTO dto);

    List<CatEspecie> listEspeciesActivas();

    List<ClasifProductoTraDTO> listadoTipoTramite();
}
