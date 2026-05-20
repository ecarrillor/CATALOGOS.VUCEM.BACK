package mx.gob.sat.catalogo.controller.request.documentotramite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatDocumentoTramiteRequest {
    @JsonProperty("idTipoDoc") private Short idTipoDoc;
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("blnEspecifico") private Boolean blnEspecifico;
    @JsonProperty("ideClasificacionDocumento") private String ideClasificacionDocumento;
    @JsonProperty("ideTipoSolicitanteRfe") private String ideTipoSolicitanteRfe;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("blnSoloAnexar") private Boolean blnSoloAnexar;
    @JsonProperty("ideReglaAnexado") private String ideReglaAnexado;
}
