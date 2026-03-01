package com.example.vucem_catalogos_service.model.dto.FraccionTtra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatFraccionTtraRequestDTO {

    private Long id;

    private String cveFraccion;

    private Long idTipoTramite;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;

    private String descFraccionAlt;

    private String ideClasifPartida;

    private Boolean blnFraccionControlada;

    private Long idCategoriaTextil;

    private BigDecimal factorConversion;

    private BigDecimal valorEquivalencia;

    private String cveUnidadMedida;

    private String reglaAplicable;
}
