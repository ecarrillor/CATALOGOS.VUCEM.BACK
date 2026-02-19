package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPaisDTO;
import com.example.vucem_catalogos_service.model.dto.CatPaisSaveDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICatPaisService {
    PageResponseDTO<CatPaisDTO> list(String search, Pageable pageable);
    CatPaisSaveDTO findById(String cvePais);
    CatPaisSaveDTO create(CatPaisSaveDTO dto);
    CatPaisSaveDTO update(String cvePais, CatPaisSaveDTO dto);

    List<SelectDTO> listMoneda();
}
