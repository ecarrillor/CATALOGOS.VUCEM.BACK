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
public class CatEquivMonedaDTO {
    private Integer id;
    private String cveMonedaOrigen;
    private String nombreMonedaOrigen;
    private String cveMonedaDestino;
    private String nombreMonedaDestino;
    private BigDecimal valorConversion;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
