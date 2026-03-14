package com.example.vucem_catalogos_service.model.dto.AduanaTramite;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@Builder
public class CatAduanaTramiteResponseDTO {

    private Long idAduanaTramite;
    private String cveAduana;
    private String aduana;
    private String nombreTipoTramite;
    private String aliasAduana;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;

}

