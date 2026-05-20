package mx.gob.sat.catalogo.controller.response.subdivisionfraccion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CatSubdivisionFraccionResponse {
    @JsonProperty("cveSubdivision") private String cveSubdivision;
    @JsonProperty("codSubdivision") private String codSubdivision;
    @JsonProperty("descripcion") private String descripcion;
    @JsonProperty("precioEstimado") private BigDecimal precioEstimado;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("cveFraccion") private String cveFraccion;
}
