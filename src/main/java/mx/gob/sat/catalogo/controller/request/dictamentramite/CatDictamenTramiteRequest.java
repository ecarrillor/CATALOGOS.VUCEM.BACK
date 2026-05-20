package mx.gob.sat.catalogo.controller.request.dictamentramite;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatDictamenTramiteRequest {
    @JsonProperty("idTipoTramite") private Long idTipoTramite;
    @JsonProperty("idTipoDictamen") private Long idTipoDictamen;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
