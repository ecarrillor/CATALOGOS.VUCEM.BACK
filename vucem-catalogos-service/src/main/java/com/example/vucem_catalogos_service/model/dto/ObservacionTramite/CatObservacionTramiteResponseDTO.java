package com.example.vucem_catalogos_service.model.dto.ObservacionTramite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatObservacionTramiteResponseDTO {

    private Long numObservacionTramite;
    private String tipoTramite;
    private String descripcionObservacion;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
