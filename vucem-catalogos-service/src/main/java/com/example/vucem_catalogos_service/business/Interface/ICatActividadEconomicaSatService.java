package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatActividadEconomicaSatDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatActividadEconomicaSatService {

    PageResponseDTO<CatActividadEconomicaSatDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatActividadEconomicaSatDTO findById(Long id);

    CatActividadEconomicaSatDTO create(CatActividadEconomicaSatDTO dto);

    CatActividadEconomicaSatDTO update(Long id, CatActividadEconomicaSatDTO dto);

    List<SelectDTO> listadoActRel();

    List<SelectDTO> listadoAcDesc();
}
