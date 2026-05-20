package mx.gob.sat.catalogo.controller.response.unidadadminvecina;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * <b>Class:</b> CatUnidadAdminVecinaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de respuesta para el catalogo de unidades administrativas vecinas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response
 */
@Getter
@Setter
public class CatUnidadAdminVecinaResponse {
    @JsonProperty("cveUnidadAdministrativa") private String cveUnidadAdministrativa;
    @JsonProperty("cveEntidad") private String cveEntidad;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
