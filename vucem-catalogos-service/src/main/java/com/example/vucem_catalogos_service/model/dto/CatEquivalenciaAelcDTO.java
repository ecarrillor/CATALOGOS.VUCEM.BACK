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
public class CatEquivalenciaAelcDTO {
    private Instant fecIniVigencia;
    private String cveMoneda;
    private BigDecimal valor;
    private Instant fecCaptura;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
