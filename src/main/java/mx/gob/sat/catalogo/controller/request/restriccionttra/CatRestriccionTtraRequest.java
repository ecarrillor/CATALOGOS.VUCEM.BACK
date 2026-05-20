package mx.gob.sat.catalogo.controller.request.restriccionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatRestriccionTtraRequest {

    @JsonProperty("idRestriccionTtra")
    private Short idRestriccionTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("descRestriccion")
    private String descRestriccion;

    @JsonProperty("descContenidoRestriccion")
    private String descContenidoRestriccion;

    @NotNull
    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @NotNull
    @JsonProperty("blnActivo")
    private Boolean blnActivo;

    @JsonProperty("ideSentDictamen")
    private String ideSentDictamen;

    @JsonProperty("ideTipoRestriccionTtra")
    private String ideTipoRestriccionTtra;

    @JsonProperty("ideMotivoRechazoDict")
    private String ideMotivoRechazoDict;
}
