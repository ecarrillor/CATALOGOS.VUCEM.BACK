package mx.gob.sat.catalogo.controller.request.sitdomidc;

import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatSitDomIdcRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de situacion domicilio IDC.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Request DTO
 */
@Getter
@Setter
public class CatSitDomIdcRequest {

    /** Descripcion. */
    @Size(max = 1000)
    private String descripcion;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
