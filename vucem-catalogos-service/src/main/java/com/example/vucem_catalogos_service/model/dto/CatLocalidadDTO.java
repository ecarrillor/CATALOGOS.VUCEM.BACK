package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatLocalidadDTO {
    private String cveLocalidad;
    private String nombreLocalidad;
    private String cveDelegMun;
    private String nombreMunicipio;
    private String cp;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
