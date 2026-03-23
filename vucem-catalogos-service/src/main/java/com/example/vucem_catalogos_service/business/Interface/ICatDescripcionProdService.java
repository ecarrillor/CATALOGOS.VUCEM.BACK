package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdRequestDTO;
import com.example.vucem_catalogos_service.model.dto.DescripcionProd.CatDescripcionProdResponseDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatDescripcionProdService {

    PageResponseDTO<CatDescripcionProdResponseDTO> listAll(String search, Long idTipoTramite, String sortBy, String sortDir, Pageable pageable);

    CatDescripcionProdResponseDTO crear(CatDescripcionProdRequestDTO dto,  Long idTipoTramite);

    CatDescripcionProdResponseDTO findById(Integer id, Long idTipoTramite);

    CatDescripcionProdResponseDTO update(Integer id, CatDescripcionProdRequestDTO dto);

    List<ClasifProductoTraDTO> listadoTipoTramite();

    ClasifProductoTraDTO lastDescripcionProd();
}
