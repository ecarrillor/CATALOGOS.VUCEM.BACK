package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdminVecinaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatUnidadAdminVecinaService {
    PageResponseDTO<CatUnidadAdminVecinaDTO> list(String search, Pageable pageable);
    CatUnidadAdminVecinaDTO findById(String cveUnidadAdministrativa, String cveEntidad);
    CatUnidadAdminVecinaDTO create(CatUnidadAdminVecinaDTO dto);
    CatUnidadAdminVecinaDTO update(String cveUnidadAdministrativa, String cveEntidad, CatUnidadAdminVecinaDTO dto);

    List<SelectDTO> listUnidadAdministrativa();
}
