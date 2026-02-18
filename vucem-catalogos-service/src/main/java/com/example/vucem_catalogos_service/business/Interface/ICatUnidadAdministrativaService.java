package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatUnidadAdministrativaNameDTO;

import java.util.List;

public interface ICatUnidadAdministrativaService {
    List <CatUnidadAdministrativaNameDTO> findByAll();
}
