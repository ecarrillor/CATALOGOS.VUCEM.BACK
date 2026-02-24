package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatDiaNoLaborableDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import com.example.vucem_catalogos_service.model.dto.SelectDTO;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;
import java.util.List;

public interface ICatDiaNoLaborableService {

    PageResponseDTO<CatDiaNoLaborableDTO> list(String search, Pageable pageable);

    CatDiaNoLaborableDTO findById(LocalDate fecNoLaborable, String cveCalendario);

    CatDiaNoLaborableDTO create(CatDiaNoLaborableDTO dto);

    CatDiaNoLaborableDTO update(LocalDate fecNoLaborable, String cveCalendario, CatDiaNoLaborableDTO dto);

    List<SelectDTO> listCalendario();
}
