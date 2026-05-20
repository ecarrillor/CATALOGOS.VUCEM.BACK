package mx.gob.sat.catalogo.controller.response.declaraciontramite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatDeclaracionTramiteResponse {
    @JsonProperty("cveDeclaracion") private String cveDeclaracion;
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
