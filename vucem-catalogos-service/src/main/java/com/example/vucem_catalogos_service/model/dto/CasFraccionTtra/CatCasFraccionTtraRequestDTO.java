package com.example.vucem_catalogos_service.model.dto.CasFraccionTtra;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CatCasFraccionTtraRequestDTO {

    private Short id;

    private Short idCas;

    private String cveFraccion;

    private Long idTipoTramite;

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
