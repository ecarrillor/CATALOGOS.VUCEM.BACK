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
public class CatTarifaDTO {
    private Long idTipoTramite;
    private String cveTipoTramite;
    private LocalDate fecIniVigencia;
    private String idTipoTarifa;
    private String cveTipoTarifa;
    private LocalDate fecFinVigencia;
    private BigDecimal tarifa;
    private Boolean blnActivo;
}
