package mx.gob.sat.catalogo.controller.request.servicioimmex;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatServicioImmexRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de servicios IMMEX.</p>
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
public class CatServicioImmexRequest {

    /** Nombre del servicio IMMEX. */
    @Size(max = 200)
    private String nombre;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Identificador de tipo de servicio IMMEX. */
    @Size(max = 20)
    private String ideTipoServicioImmex;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
