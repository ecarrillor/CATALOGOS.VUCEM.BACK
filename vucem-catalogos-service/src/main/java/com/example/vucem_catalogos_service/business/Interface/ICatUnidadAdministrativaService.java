package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaDTO;
import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatUnidadAdministrativaService {
    List<CatUnidadAdministrativaNameDTO> findByAll();
    PageResponseDTO<CatUnidadAdministrativaDTO> list(String search, Pageable pageable);
    CatUnidadAdministrativaDTO findById(String cveUnidadAdministrativa);
    CatUnidadAdministrativaDTO create(CatUnidadAdministrativaDTO dto);
    CatUnidadAdministrativaDTO update(String cveUnidadAdministrativa, CatUnidadAdministrativaDTO dto);
    List<SelectDTO> listadoEntidades();
    List<SelectDTO> listadoDependencias();
}
