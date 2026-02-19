package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatDelegMunService {
    PageResponseDTO<CatDelegMunDTO> list(String search, Pageable pageable);
    CatDelegMunDTO findById(String cveDelegMun);
    CatDelegMunDTO create(CatDelegMunDTO dto);
    CatDelegMunDTO update(String cveDelegMun, CatDelegMunDTO dto);
}
