package com.example.vucem_catalogos_service.model.dto.AduanaTramite;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;


@Data
@AllArgsConstructor
public class CatAduanaTramiteRequestDTO {

    private Long idAduanaTramite;
    private String cveAduana;
    private Integer idTipoTramite;
    private String tipoTramite;
    private String aliasAduana;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;

}
