package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatUsoMercanciaTtraDTO {
    private Short id;
    private Long idTipoTramite;
    private String nombreTipoTramite;
    private String descUsoMercancia;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Short blnActivo;
}
