package com.example.vucem_catalogos_service.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
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
public class CatEquivalenciaAelcDTO {
    private LocalDate fecIniVigencia;
    private String cveMoneda;
    private String nombreMoneda;
    private BigDecimal valor;
    private LocalDate fecCaptura;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
