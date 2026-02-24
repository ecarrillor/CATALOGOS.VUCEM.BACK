package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatRegimenTtraDTO {
    private Short id;
    private String cveRegimen;
    private String nombreRegimen;
    private Long idTipoTramite;
    private String nombreTipoTramite;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
