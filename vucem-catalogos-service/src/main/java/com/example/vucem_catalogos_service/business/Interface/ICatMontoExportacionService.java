package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO;
import com.example.vucem_catalogos_service.model.dto.PageResponseDTO;
import org.springframework.data.domain.Pageable;

import java.time.LocalDate;

public interface ICatMontoExportacionService {

    PageResponseDTO<CatMontoExportacionDTO> list(String search, String sortBy, String sortDir, Pageable pageable);

    CatMontoExportacionDTO findMontoExportacion(String id, LocalDate fecha);

    CatMontoExportacionDTO update(String id, LocalDate fecha, CatMontoExportacionDTO dto);
}
