package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatSubdivisionFraccionDTO {
    private String cveSubdivision;
    private String cveFraccion;
    private String descripcionFraccion;
    private String codSubdivision;
    private String descripcion;
    private BigDecimal precioEstimado;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
