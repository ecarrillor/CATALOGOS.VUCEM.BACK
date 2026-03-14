package com.example.vucem_catalogos_service.model.dto.MotivoTramite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatMotivoTramiteRequestDTO {

    private Long idMotivoTramite;
    private Long idTipoTramite;
    private String tipoMotivo;
    private String descripcionMotivo;
    private String descripcionContenido;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
