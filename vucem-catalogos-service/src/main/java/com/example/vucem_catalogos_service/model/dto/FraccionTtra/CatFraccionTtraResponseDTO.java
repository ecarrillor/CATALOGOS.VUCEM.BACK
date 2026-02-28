package com.example.vucem_catalogos_service.model.dto.FraccionTtra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatFraccionTtraResponseDTO {

    private Long id;

    private String cveFraccion;
    private String descripcionFraccion;

    private Long idTipoTramite;
    private String descTipoTramite;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;

    private String descFraccionAlt;

    private String ideClasifPartida;

    private Boolean blnFraccionControlada;

    private Long idCategoriaTextil;
    private String descripcionCategoriaTextil;

    private BigDecimal factorConversion;

    private BigDecimal valorEquivalencia;

    private String cveUnidadMedida;

    private String reglaAplicable;
}
