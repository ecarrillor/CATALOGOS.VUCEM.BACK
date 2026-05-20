package mx.gob.sat.catalogo.controller.response.leyendatexto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

/**
 * <b>Class:</b> CatLeyendaTextoResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de leyendas de texto.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Response DTO
 */
@Getter
@Builder
public class CatLeyendaTextoResponse {

    /** Identificador de la leyenda de texto. */
    private Long idLeyendaTexto;

    /** Identificador de tipo de leyenda de texto. */
    private String ideTipoLeyendaTexto;

    /** Numero de anio. */
    private Short numAnio;

    /** Leyenda. */
    private String leyenda;

    /** Fecha de inicio de vigencia. */
    private LocalDate fecIniVigencia;

    /** Fecha de fin de vigencia. */
    private LocalDate fecFinVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;
}
