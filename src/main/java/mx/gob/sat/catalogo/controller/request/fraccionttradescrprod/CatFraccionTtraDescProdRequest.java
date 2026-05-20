package mx.gob.sat.catalogo.controller.request.fraccionttradescrprod;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatFraccionTtraDescProdRequest {
    @JsonProperty("idFraccionTtraDescProd") private Long idFraccionTtraDescProd;
    @JsonProperty("idDescripcionProd") private Integer idDescripcionProd;
    @JsonProperty("idFraccionGob") private Long idFraccionGob;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
