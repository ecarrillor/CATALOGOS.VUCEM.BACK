package com.example.vucem_catalogos_service.model.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Builder
@Data
public class CatDelegMunSaveDTO {
    private String cveDelegMun;
    private String nombre;
    private String cveEntidad;
    private LocalDate fecFinVigencia;
    private String satMunicipality;
    private LocalDate fecIniVigencia;
    private Boolean blnActivo;
}
