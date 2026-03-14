package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatPaisDTO {
    private String cvePais;
    private String nombre;
    private String cveMoneda;
    private String cvePaisWco;
    private LocalDate fecFinVigencia;
    private LocalDate fecIniVigencia;
    private Boolean blnActivo;
}
