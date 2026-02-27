package com.example.vucem_catalogos_service.model.dto.RestriccionTtra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatRestriccionTtraRequestDTO {

    private Short id;

    private Long idTipoTramite;

    private String descRestriccion;

    private String descContenidoRestriccion;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blnActivo;

    private String ideSentDictamen;

    private String ideTipoRestriccionTtra;

    private String ideMotivoRechazoDict;
}
