package mx.gob.sat.catalogo.controller.request.motivottra;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatMotivoTtraRequest {

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

    @NotNull
    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @JsonProperty("blnActivo")
    private Boolean blnActivo;
}
