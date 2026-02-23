package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;

public interface ICatDiaNoLaborableService {

    PageResponseDTO<CatDiaNoLaborableDTO> list(String search, Pageable pageable);

    CatDiaNoLaborableDTO findById(LocalDate fecNoLaborable, String cveCalendario);

    CatDiaNoLaborableDTO create(CatDiaNoLaborableDTO dto);

    CatDiaNoLaborableDTO update(LocalDate fecNoLaborable, String cveCalendario, CatDiaNoLaborableDTO dto);
}
