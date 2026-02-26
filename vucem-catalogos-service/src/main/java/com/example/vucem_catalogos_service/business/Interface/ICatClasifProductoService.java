package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatClasifProductoDTO;
import com.example.vucem_catalogos_service.model.dto.ClasifProductoTraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatClasifProductoService {
    PageResponseDTO<CatClasifProductoDTO> list(String search, Long idTipoTramite, Pageable pageable);
    CatClasifProductoDTO findById(Long id);
    CatClasifProductoDTO create(CatClasifProductoDTO dto);
    CatClasifProductoDTO update(Long id, CatClasifProductoDTO dto);
    List<ClasifProductoTraDTO> listadoTipoTramite();

    List<ClasifProductoTraDTO> listadoClasifPrR(Long idTipoTramite);
}
