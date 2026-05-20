package mx.gob.sat.catalogo.controller.response.motivottra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatMotivoTtraResponse {

    @JsonProperty("idMotivoTtra")
    private Long idMotivoTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("ideTipoMotivoTtra")
    private String ideTipoMotivoTtra;

    @JsonProperty("descMotivo")
    private String descMotivo;

    @JsonProperty("descContenidoMotivo")
    private String descContenidoMotivo;

    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
