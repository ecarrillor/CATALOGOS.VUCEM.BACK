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
public class CatVidaSilvestreRequestDTO {

    private Integer id;

    private Long idTipoTramite;

    private Integer idGenero;

    private Integer idEspecie;

    private String descNombreComun;

    private String descNombreCientifico;

    private String ideClasifTaxonomica;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;
}
