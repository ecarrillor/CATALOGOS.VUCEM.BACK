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
public class CatColoniaDTO {
    // Campos de salida (orden del constructor JPQL)
    private String cveColonia;
    private String nombre;
    private String municipio;
    private String localidad;
    private String cp;
    private LocalDate fecCaptura;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    // Campos FK: entrada para create/update, salida en consultas
    private String cveDelegMun;
    private String cveLocalidad;
    private String nombrePaís;
}
