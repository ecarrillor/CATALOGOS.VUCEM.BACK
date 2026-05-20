package mx.gob.sat.catalogo.controller.response.plazomaximoauttramite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CatPlazoMaximoAutTramiteResponse {
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("plazoAnios") private Short plazoAnios;
    @JsonProperty("idePlazoMeses") private String idePlazoMeses;
    @JsonProperty("blnIlimitado") private Boolean blnIlimitado;
    @JsonProperty("plazo") private String plazo;
    @JsonProperty("blnAsignacionFechaFin") private Boolean blnAsignacionFechaFin;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
