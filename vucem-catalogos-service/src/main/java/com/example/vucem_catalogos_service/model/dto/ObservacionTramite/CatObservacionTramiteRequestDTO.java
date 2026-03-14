package com.example.vucem_catalogos_service.model.dto.ObservacionTramite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatObservacionTramiteRequestDTO {

    private Long numObservacionTramite;
    private Long idTipoTramite;
    private String DescripcionObservacion;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
