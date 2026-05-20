package mx.gob.sat.catalogo.controller.request.parametro;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatParametroRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de parametros del sistema.</p>
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
public class CatParametroRequest {

    /** Clave del parametro. */
    @Size(max = 30)
    private String cveParametro;

    /** Descripcion del parametro. */
    @Size(max = 200)
    private String descripcion;

    /** Valor del parametro. */
    @Size(max = 2000)
    private String valor;

    /** Identificador de la dependencia asociada. */
    private Long idDependencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
