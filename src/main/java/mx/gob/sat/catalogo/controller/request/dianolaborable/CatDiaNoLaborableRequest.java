package mx.gob.sat.catalogo.controller.request.dianolaborable;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CatDiaNoLaborableRequest {
    @JsonProperty("fecNoLaborable") private LocalDate fecNoLaborable;
    @JsonProperty("cveCalendario") private String cveCalendario;
    @JsonProperty("descripcion") private String descripcion;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
