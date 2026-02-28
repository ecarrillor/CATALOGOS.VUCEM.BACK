package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatDescripcionProdService {

    PageResponseDTO<CatDescripcionProdResponseDTO> listAll(String search, Pageable pageable);

    CatDescripcionProdResponseDTO crear(CatDescripcionProdRequestDTO dto);

    CatDescripcionProdResponseDTO findById(Integer id);

    CatDescripcionProdResponseDTO update(Integer id, CatDescripcionProdRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();
}
