package mx.gob.sat.catalogo.controller.response.observacionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatObservacionTtraResponse {

    @JsonProperty("idObservacionTtra")
    private Long idObservacionTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("descObservacionDict")
    private String descObservacionDict;

    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
