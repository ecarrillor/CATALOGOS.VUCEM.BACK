package mx.gob.sat.catalogo.controller.response.tarifa;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CatTarifaResponse {
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("ideTipoTarifa") private String ideTipoTarifa;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("tarifa") private BigDecimal tarifa;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
