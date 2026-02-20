package com.example.vucem_catalogos_service.model.dto.DictamenTramite;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatDictamenTramiteRequestDTO {


    private Long idTipoTramite;
    private Long idTipoDictamen;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
