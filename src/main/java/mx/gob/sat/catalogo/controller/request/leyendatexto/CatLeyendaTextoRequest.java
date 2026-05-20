package mx.gob.sat.catalogo.controller.request.leyendatexto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatLeyendaTextoRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de leyendas de texto.</p>
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
public class CatLeyendaTextoRequest {

    /** Identificador de tipo de leyenda de texto. */
    @NotNull
    @Size(max = 20)
    private String ideTipoLeyendaTexto;

    /** Numero de anio. */
    private Short numAnio;

    /** Leyenda. */
    @NotNull
    @Size(max = 2000)
    private String leyenda;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;
}
