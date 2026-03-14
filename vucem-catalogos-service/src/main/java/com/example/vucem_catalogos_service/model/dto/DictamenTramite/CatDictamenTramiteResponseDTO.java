package com.example.vucem_catalogos_service.model.dto.DictamenTramite;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
@Data
@AllArgsConstructor
@Builder
public class CatDictamenTramiteResponseDTO {

    private Long idTipoTramite;
    private String tipoTramite;
    private Long idTipoDictamen;
    private String tipoDictamen;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
}
