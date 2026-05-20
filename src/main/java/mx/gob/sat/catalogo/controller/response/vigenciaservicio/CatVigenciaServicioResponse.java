package mx.gob.sat.catalogo.controller.response.vigenciaservicio;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class CatVigenciaServicioResponse {
    @JsonProperty("idVigenciaServicio") private Short idVigenciaServicio;
    @JsonProperty("numVigencia") private String numVigencia;
    @JsonProperty("ideTipoVigencia") private String ideTipoVigencia;
    @JsonProperty("ideTipoServicioCeror") private String ideTipoServicioCeror;
    @JsonProperty("idBloque") private Short idBloque;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("cveCriterioOrigen") private String cveCriterioOrigen;
    @JsonProperty("cvePais") private String cvePais;
    @JsonProperty("idTratadoAcuerdo") private Short idTratadoAcuerdo;
}
