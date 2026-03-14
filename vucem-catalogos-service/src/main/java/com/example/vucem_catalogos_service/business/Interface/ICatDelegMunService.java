package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatDelegMunDTO;
import com.example.vucem_catalogos_service.model.dto.CatDelegMunSaveDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

public interface ICatDelegMunService {
    PageResponseDTO<CatDelegMunDTO> list(String search, Pageable pageable);
    CatDelegMunDTO findById(String cveDelegMun);
    CatDelegMunSaveDTO create(CatDelegMunSaveDTO dto);
    CatDelegMunSaveDTO update(String cveDelegMun, CatDelegMunSaveDTO dto);
}
