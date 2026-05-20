package mx.gob.sat.catalogo.controller.request.justificacionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatJustificacionTtraRequest {

    @JsonProperty("idJustificacionTtra")
    private Long idJustificacionTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("descJustificacion")
    private String descJustificacion;

    @JsonProperty("descContenidoJustificacion")
    private String descContenidoJustificacion;

    @NotNull
    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
