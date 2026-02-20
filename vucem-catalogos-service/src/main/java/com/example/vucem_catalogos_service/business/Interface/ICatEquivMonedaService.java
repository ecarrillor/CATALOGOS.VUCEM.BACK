package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatEquivMonedaDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatEquivMonedaService {
    PageResponseDTO<CatEquivMonedaDTO> list(String search, Pageable pageable);
    CatEquivMonedaDTO findById(Integer id);
    CatEquivMonedaDTO create(CatEquivMonedaDTO dto);
    CatEquivMonedaDTO update(Integer id, CatEquivMonedaDTO dto);

    List<SelectDTO> buscarMonedasDest(String term);


}
