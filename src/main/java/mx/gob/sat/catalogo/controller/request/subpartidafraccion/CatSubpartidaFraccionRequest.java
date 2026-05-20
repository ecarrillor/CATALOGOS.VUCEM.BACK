package mx.gob.sat.catalogo.controller.request.subpartidafraccion;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Request para el catalogo de subpartida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
public class CatSubpartidaFraccionRequest {
    @JsonProperty("cveSubpartidaFraccion") private String cveSubpartidaFraccion;
    @JsonProperty("cveCapituloFraccion") private String cveCapituloFraccion;
    @JsonProperty("cvePartidaFraccion") private String cvePartidaFraccion;
    @JsonProperty("nombre") private String nombre;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
}
