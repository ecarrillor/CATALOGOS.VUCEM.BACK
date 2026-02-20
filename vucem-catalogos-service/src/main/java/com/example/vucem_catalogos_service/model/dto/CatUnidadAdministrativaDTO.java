package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatUnidadAdministrativaDTO {
    private String cveUnidadAdministrativa;
    private String ideTipoUnidadAdministrativa;
    private String cveUnidadAdministrativaR;
    private Short nivel;
    private String acronimo;
    private String nombre;
    private String descripcion;
    private String cveEntidad;
    private String nombreEntidad;
    private Short idDependencia;
    private String nombreDependencia;
    private Boolean blnFronteriza;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
