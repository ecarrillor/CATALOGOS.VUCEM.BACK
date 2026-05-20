package mx.gob.sat.catalogo.controller.request.tratadobloque;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Request para el catalogo de tratado bloque.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
public class CatTratadoBloqueRequest {
    @JsonProperty("idTratadoAcuerdo") private Short idTratadoAcuerdo;
    @JsonProperty("idBloque") private Short idBloque;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
