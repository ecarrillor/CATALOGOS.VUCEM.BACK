package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatColoniaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;

import java.util.List;

public interface ICatColoniaService {
    PageResponseDTO<CatColoniaDTO> list(String nombre, String cp, String municipio, String nombrePais, Boolean blnActivo, Integer page, Integer size);
    CatColoniaDTO findById(String cveColonia);
    CatColoniaDTO create(CatColoniaDTO dto);
    CatColoniaDTO update(String cveColonia, CatColoniaDTO dto);
    List<CatColoniaDTO> findByCp(String cp);
}
