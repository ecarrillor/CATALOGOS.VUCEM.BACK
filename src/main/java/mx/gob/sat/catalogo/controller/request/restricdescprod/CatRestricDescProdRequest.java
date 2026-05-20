package mx.gob.sat.catalogo.controller.request.restricdescprod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatRestricDescProdRequest {
    @JsonProperty("idRestricDescProd") private Long idRestricDescProd;
    @JsonProperty("idRestriccionTtra") private Short idRestriccionTtra;
    @JsonProperty("idDescripcionProd") private Integer idDescripcionProd;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
