package com.example.vucem_catalogos_service.model.dto.RestriccionTtra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatRestriccionTtraResponseDTO {

    private Short idRestriccionTtra;

    private Long idTipoTramite;
    private String descTipoTramite;

    private String descRestriccion;

    private String descContenidoRestriccion;

    private Instant fecIniVigencia;

    private Instant fecFinVigencia;

    private Boolean blnActivo;

    private String ideSentDictamen;

    private String ideTipoRestriccionTtra;

    private String ideMotivoRechazoDict;
}
