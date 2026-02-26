package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatAduanaClasifProdDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatAduanaClasifProdService {
    PageResponseDTO<CatAduanaClasifProdDTO> list(String search, Long idClasifProducto, Pageable pageable);
    CatAduanaClasifProdDTO findById(Long id);
    CatAduanaClasifProdDTO create(CatAduanaClasifProdDTO dto);
    CatAduanaClasifProdDTO update(Long id, CatAduanaClasifProdDTO dto);
    List<ClasifProductoTraDTO> listadoTipoTramite();
    List<SelectDTO> listadoAduana();
    List<ClasifProductoTraDTO> listadoClasifProducto(Long idTipoTramite);
}
