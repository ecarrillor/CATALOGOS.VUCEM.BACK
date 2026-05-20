package mx.gob.sat.catalogo.controller.request.tratadobloquepai;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDate;

/**
 * Request para el catalogo de tratado bloque pais.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Getter
@Setter
public class CatTratadoBloquePaiRequest {
    @JsonProperty("cvePais") private String cvePais;
    @JsonProperty("idTratadoAcuerdo") private Short idTratadoAcuerdo;
    @JsonProperty("fecCaptura") private LocalDate fecCaptura;
    @JsonProperty("fecIniVigencia") private LocalDate fecIniVigencia;
    @JsonProperty("fecFinVigencia") private LocalDate fecFinVigencia;
    @JsonProperty("blnActivo") private Boolean blnActivo;
    @JsonProperty("blnEnvioElectronico") private Boolean blnEnvioElectronico;
    @JsonProperty("blnMuestraCertificado") private Boolean blnMuestraCertificado;
}
