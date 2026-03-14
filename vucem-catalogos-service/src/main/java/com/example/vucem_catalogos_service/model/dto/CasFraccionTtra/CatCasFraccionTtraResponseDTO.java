package com.example.vucem_catalogos_service.model.dto.CasFraccionTtra;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CatCasFraccionTtraResponseDTO {

    private Short id;

    private Short idCas;
    private String descCas;

    private String cveFraccion;
    private String descripcionFraccion;

    private Long idTipoTramite;
    private String descTipoTramite;

    private Boolean blnRotterdam;

    private LocalDate fecIniVigencia;

    private LocalDate fecFinVigencia;

    private Boolean blActivo;

    private String descFraccionAlt;

    private Short cvnWasser;

    private Short cvnArmas;

    private Short cvnMontreal;

    private Short cvnEstocolmo;

    private Short formaDesc;

    private String ideIdentificadorRegla;
}
