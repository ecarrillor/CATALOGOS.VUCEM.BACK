package mx.gob.sat.catalogo.controller.request.catalogodtr;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

@Getter
@Setter
public class CatCatalogoDTrRequest {
    @JsonProperty("cveCatalogo") private String cveCatalogo;
    @JsonProperty("cveLenguaje") private String cveLenguaje;
    @JsonProperty("descripcion") private String descripcion;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
