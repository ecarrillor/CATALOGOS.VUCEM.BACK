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
    private Instant fecIniVigencia;
    private String ideTipoTarifa;
    private String nombreTipoTramite;
    private LocalDate fecFinVigencia;
    private BigDecimal tarifa;
    private Boolean blnActivo;
}
