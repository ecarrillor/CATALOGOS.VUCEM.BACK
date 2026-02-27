package com.example.vucem_catalogos_service.business.Interface;

import com.example.vucem_catalogos_service.model.dto.CatMontoExportacionDTO;

import java.time.LocalDate;

public interface ICatMontoExportacionService {
    CatMontoExportacionDTO findMontoExportacion(String id, LocalDate fecha);

    CatMontoExportacionDTO update(String id, LocalDate fecha, CatMontoExportacionDTO dto);
}
