package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatPlazoMaximoAutTramiteDTO {
    private Long idTipoTramite;
    private LocalDate fecIniVigencia;
    private String descServicio;
    private LocalDate fecFinVigencia;
    private Short plazoAnios;
    private String idePlazoMeses;
    private Boolean blnIlimitado;
    private String plazo;
    private Boolean blnAsignacionFechaFin;
    private Boolean blnActivo;
}
