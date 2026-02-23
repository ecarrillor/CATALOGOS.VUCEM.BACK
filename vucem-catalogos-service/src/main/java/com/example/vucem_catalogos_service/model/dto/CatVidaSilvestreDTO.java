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
public class CatVidaSilvestreDTO {
    private Integer id;
    private String ideTipoVidaSilvestre;
    private Integer idEspecie;
    private String descEspecie;
    private String descNombreComun;
    private String descNombreCientifico;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private String ideClasifTaxonomica;
    private String funcionZootecnica;
}
