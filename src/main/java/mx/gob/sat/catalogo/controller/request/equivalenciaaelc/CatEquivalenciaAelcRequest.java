package mx.gob.sat.catalogo.controller.request.equivalenciaaelc;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CatEquivalenciaAelcRequest {
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("cveMoneda") private String cveMoneda;
    @JsonProperty("valor") private BigDecimal valor;
    @JsonProperty("fecCaptura") private LocalDate fecCaptura;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
