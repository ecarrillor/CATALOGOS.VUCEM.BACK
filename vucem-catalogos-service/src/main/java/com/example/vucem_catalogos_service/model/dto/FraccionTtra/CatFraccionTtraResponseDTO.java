package com.example.vucem_catalogos_service.model.dto.FraccionTtra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

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

    private Instant fecIniVigencia;

    private Instant fecFinVigencia;

    private Boolean blnActivo;

    private String descFraccionAlt;

    private String ideClasifPartida;

    private Short blnFraccionControlada;

    private Long idCategoriaTextil;
    private String descripcionCategoriaTextil;

    private BigDecimal factorConversion;

    private BigDecimal valorEquivalencia;

    private String cveUnidadMedida;

    private String reglaAplicable;
}
