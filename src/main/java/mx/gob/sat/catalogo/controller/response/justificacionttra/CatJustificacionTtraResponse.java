package mx.gob.sat.catalogo.controller.response.justificacionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatJustificacionTtraResponse {

    @JsonProperty("idJustificacionTtra")
    private Long idJustificacionTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("descJustificacion")
    private String descJustificacion;

    @JsonProperty("descContenidoJustificacion")
    private String descContenidoJustificacion;

    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
