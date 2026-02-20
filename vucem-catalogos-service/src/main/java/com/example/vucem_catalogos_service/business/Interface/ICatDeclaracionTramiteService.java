package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatDeclaracionTramiteDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatDeclaracionTramiteService {
    PageResponseDTO<CatDeclaracionTramiteDTO> list(String search, Pageable pageable);
    CatDeclaracionTramiteDTO findById(String cveDeclaracion, Integer idTipoTramite);
    CatDeclaracionTramiteDTO create(CatDeclaracionTramiteDTO dto);
    CatDeclaracionTramiteDTO update(String cveDeclaracion, Integer idTipoTramite, CatDeclaracionTramiteDTO dto);
    List<SelectDTO> listadoDeclaraciones();
    List<SelectDTO> listadoTiposTramite();
}
