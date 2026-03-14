package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CatFundamentoTtraDTO {
    private Short id;
    private Long idTipoTramite;
    private String nombreTipoTramite;
    private String ideTipoFundamentoTtra;
    private String descFundamento;
    private String descContenidoFundamento;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
