package com.example.vucem_catalogos_service.model.dto.JustificacionTramite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatJustificacionTramiteRequestDTO {

    private Long idJustificacionTramite;
    private Long idTipoTramite;
    private String descripcionJustificacion;
    private String descripcionContenido;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
