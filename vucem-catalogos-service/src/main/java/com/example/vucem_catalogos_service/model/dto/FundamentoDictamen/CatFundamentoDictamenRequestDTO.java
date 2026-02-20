package com.example.vucem_catalogos_service.model.dto.FundamentoDictamen;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatFundamentoDictamenRequestDTO {

    private Long cveFundamentoDictamen;
    private String descripcion;
    private Boolean blnSentidoFundamento;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
