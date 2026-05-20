package mx.gob.sat.catalogo.controller.request.observacionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatObservacionTtraRequest {

    @JsonProperty("idObservacionTtra")
    private Long idObservacionTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("descObservacionDict")
    private String descObservacionDict;

    @NotNull
    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
