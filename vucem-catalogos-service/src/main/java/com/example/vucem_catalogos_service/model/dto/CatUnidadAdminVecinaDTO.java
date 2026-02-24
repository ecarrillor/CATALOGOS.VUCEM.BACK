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
public class CatUnidadAdminVecinaDTO {
    private String cveUnidadAdministrativa;
    private String cveEntidad;
    private String nombreUnidadAdministrativa;
    private String nombreEntidad;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
