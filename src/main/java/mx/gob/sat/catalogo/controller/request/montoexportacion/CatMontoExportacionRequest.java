package mx.gob.sat.catalogo.controller.request.montoexportacion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class CatMontoExportacionRequest {
    @JsonProperty("rfcExportador") private String rfcExportador;
    @JsonProperty("fecMontoExportacion") private LocalDate fecMontoExportacion;
    @JsonProperty("razonSocial") private String razonSocial;
    @JsonProperty("monto") private BigDecimal monto;
    @JsonProperty("fecModificacion") private LocalDate fecModificacion;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
