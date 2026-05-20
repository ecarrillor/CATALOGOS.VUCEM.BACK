package mx.gob.sat.catalogo.controller.request.unidadadminaduana;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadAdminAduanaRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de peticion para el catalogo de unidades administrativas de aduana.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request
 */
@Getter
@Setter
public class CatUnidadAdminAduanaRequest {
    @JsonProperty("cveUnidadAdministrativa") private String cveUnidadAdministrativa;
    @JsonProperty("cveAduana") private String cveAduana;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
