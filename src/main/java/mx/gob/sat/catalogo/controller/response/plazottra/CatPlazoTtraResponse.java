package mx.gob.sat.catalogo.controller.response.plazottra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CatPlazoTtraResponse {
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("idePlazoVigencia") private String idePlazoVigencia;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
