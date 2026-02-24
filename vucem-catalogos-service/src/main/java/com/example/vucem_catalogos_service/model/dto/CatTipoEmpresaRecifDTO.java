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
public class CatTipoEmpresaRecifDTO {
    private String cveTipoEmpresaRecif;
    private String cveTipoEmpresaRecifR;
    private String descripcionPadre;
    private String descripcion;
    private Instant fecIniVigencia;
    private Instant fecFinVigencia;
    private Boolean blnActivo;
}
