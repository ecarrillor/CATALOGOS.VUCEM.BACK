package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatEquivalenciaAelcDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.time.Instant;
import java.time.LocalDate;

public interface ICatEquivalenciaAELCService {

    PageResponseDTO<CatEquivalenciaAelcDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatEquivalenciaAelcDTO findById(LocalDate fecIniVigencia, String cveMoneda);

    CatEquivalenciaAelcDTO create(CatEquivalenciaAelcDTO dto);

    CatEquivalenciaAelcDTO update(LocalDate fecIniVigencia, String cveMoneda, CatEquivalenciaAelcDTO dto);
}
