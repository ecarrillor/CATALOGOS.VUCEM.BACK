package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatSubdivisionFraccionDTO {
    private String cveSubdivision;
    private String cveFraccion;
    private String descripcionFraccion;
    private String codSubdivision;
    private String descripcionSubdivision;
    private BigDecimal precioEstimado;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
