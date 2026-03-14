package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatPatenteAduanalDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatPatenteAduanalService {
    PageResponseDTO<CatPatenteAduanalDTO> list(String search, Pageable pageable);
    CatPatenteAduanalDTO findById(String cvePatenteAduanal);
    CatPatenteAduanalDTO create(CatPatenteAduanalDTO dto);
    CatPatenteAduanalDTO update(String cvePatenteAduanal, CatPatenteAduanalDTO dto);
}
