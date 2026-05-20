package mx.gob.sat.catalogo.controller.response.restriccionttra;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatRestriccionTtraResponse {

    @JsonProperty("idRestriccionTtra")
    private Short idRestriccionTtra;

    @JsonProperty("idTipoTramite")
    private Long idTipoTramite;

    @JsonProperty("descRestriccion")
    private String descRestriccion;

    @JsonProperty("descContenidoRestriccion")
    private String descContenidoRestriccion;

    @JsonProperty("fecIniVigencia")
    private LocalDate fecIniVigencia;

    @JsonProperty("fecFinVigencia")
    private LocalDate fecFinVigencia;

    @JsonProperty("blnActivo")
    private Boolean blnActivo;

    @JsonProperty("ideSentDictamen")
    private String ideSentDictamen;

    @JsonProperty("ideTipoRestriccionTtra")
    private String ideTipoRestriccionTtra;

    @JsonProperty("ideMotivoRechazoDict")
    private String ideMotivoRechazoDict;
}
