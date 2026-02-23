package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPlazoTtraDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatPlazoTtraService {

    PageResponseDTO<CatPlazoTtraDTO> list(String search, Pageable pageable);

    CatPlazoTtraDTO findById(Integer idTipoTramite, String idePlazoVigencia);

    CatPlazoTtraDTO create(CatPlazoTtraDTO dto);

    CatPlazoTtraDTO update(Integer idTipoTramite, String idePlazoVigencia, CatPlazoTtraDTO dto);
}
