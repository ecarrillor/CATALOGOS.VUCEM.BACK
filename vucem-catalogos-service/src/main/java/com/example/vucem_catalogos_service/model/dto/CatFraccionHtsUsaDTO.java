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
public class CatFraccionHtsUsaDTO {
    private Long id;
    private String cveFraccionHtsUsa;
    private String descripcion;
    private LocalDate fecCaptura;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private String ideTipoBienFraccion;
    private Boolean blnExenta;
    private Boolean blnActivo;
    private String cveUnidadMedida;
    private String nombreUnidadMedida;
}
