package mx.gob.sat.catalogo.controller.response.clasificacionregimen;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Response para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
public class CatClasificacionRegimenResponse {
    @JsonProperty("cveClasificacionRegimen") private String cveClasificacionRegimen;
    @JsonProperty("cveRegimen") private String cveRegimen;
    @JsonProperty("nombre") private String nombre;
    @JsonProperty("codRegimen") private String codRegimen;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
