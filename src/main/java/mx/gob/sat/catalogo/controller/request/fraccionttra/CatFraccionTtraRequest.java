package mx.gob.sat.catalogo.controller.request.fraccionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CatFraccionTtraRequest {
    @JsonProperty("idFraccionGob") private Long idFraccionGob;
    @JsonProperty("cveFraccion") private String cveFraccion;
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("descFraccionAlt") private String descFraccionAlt;
    @JsonProperty("ideClasifPartida") private String ideClasifPartida;
    @JsonProperty("blnFraccionControlada") private Boolean blnFraccionControlada;
    @JsonProperty("idCategoriaTextil") private Long idCategoriaTextil;
    @JsonProperty("factorConversion") private BigDecimal factorConversion;
    @JsonProperty("valorEquivalencia") private BigDecimal valorEquivalencia;
    @JsonProperty("cveUnidadMedida") private String cveUnidadMedida;
    @JsonProperty("reglaAplicable") private String reglaAplicable;
}
