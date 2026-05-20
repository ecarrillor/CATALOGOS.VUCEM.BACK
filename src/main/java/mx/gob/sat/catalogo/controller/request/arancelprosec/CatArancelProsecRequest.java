package mx.gob.sat.catalogo.controller.request.arancelprosec;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatArancelProsecRequest {
    @JsonProperty("cveFraccion") private String cveFraccion;
    @JsonProperty("cveSectorProsec") private String cveSectorProsec;
    @JsonProperty("tasa") private Long tasa;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
