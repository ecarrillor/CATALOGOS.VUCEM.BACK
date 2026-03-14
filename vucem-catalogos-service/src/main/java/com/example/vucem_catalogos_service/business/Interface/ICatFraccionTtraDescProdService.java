package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.FraccionTtraDescProd.CatFraccionTtraDescProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatFraccionTtraDescProdService {

    PageResponseDTO<CatFraccionTtraDescProdResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable);

    CatFraccionTtraDescProdResponseDTO crear(CatFraccionTtraDescProdRequestDTO dto);

    CatFraccionTtraDescProdResponseDTO findById(Long id);

    CatFraccionTtraDescProdResponseDTO update(Long id, CatFraccionTtraDescProdRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<ClasifProductoTraDTO> listadoFraccionTtra(Long idTipoTramite);

    List<SelectDTO> listadoDescripcionProd();
}
