package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.RestricDescProd.CatRestricDescProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatRestricDescProdService {

    PageResponseDTO<CatRestricDescProdResponseDTO> listAll(String search, Long idTipoTramite, Pageable pageable);

    CatRestricDescProdResponseDTO crear(CatRestricDescProdRequestDTO dto);

    CatRestricDescProdResponseDTO findById(Long id);

    CatRestricDescProdResponseDTO update(Long id, CatRestricDescProdRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<ClasifProductoTraDTO> listadoRestriccionTtra(Long idTipoTramite);

    List<SelectDTO> listadoDescripcionProd();

    ClasifProductoTraDTO lastRestricDescProd();
}
