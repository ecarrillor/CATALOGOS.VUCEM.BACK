package mx.gob.sat.catalogo.controller.request.isotopofraccion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatIsotopoFraccionRequest {
    @JsonProperty("idIsotopo") private Short idIsotopo;
    @JsonProperty("nombre") private String nombre;
    @JsonProperty("cveFraccion") private String cveFraccion;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
