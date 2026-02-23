package com.example.vucem_catalogos_service.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatDocumentoTramiteDTO {
    private Short idTipoDoc;
    private Integer idTipoTramite;
    private Short blnEspecifico;
    private String ideClasificacionDocumento;
    private String ideTipoSolicitanteRfe;
    private LocalDate fecIniVigencia;
    private LocalDate fecFinVigencia;
    private Boolean blnActivo;
    private Boolean blnSoloAnexar;
    private String ideReglaAnexado;
}
