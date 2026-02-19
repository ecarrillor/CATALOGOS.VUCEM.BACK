package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatDelegMunDTO {
    private String cveDelegMun;
    private String nombre;
    private Instant fecCaptura;
    private Instant fecFinVigencia;
    private String satMunicipality;
    private Instant fecIniVigencia;
    private Boolean blnActivo;
}
