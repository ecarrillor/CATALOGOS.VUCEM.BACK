package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatActividadEconomicaSatService {

    PageResponseDTO<CatActividadEconomicaSatDTO> list(String search, Pageable pageable);

    CatActividadEconomicaSatDTO findById(Long id);

    CatActividadEconomicaSatDTO create(CatActividadEconomicaSatDTO dto);

    CatActividadEconomicaSatDTO update(Long id, CatActividadEconomicaSatDTO dto);
}
