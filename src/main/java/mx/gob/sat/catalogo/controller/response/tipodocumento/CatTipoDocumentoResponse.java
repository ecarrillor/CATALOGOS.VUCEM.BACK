package mx.gob.sat.catalogo.controller.response.tipodocumento;

import lombok.Builder;
import lombok.Getter;

import java.time.Instant;

/**
 * <b>Class:</b> CatTipoDocumentoResponse.java <br>
 * <b>Description:</b>
 * <p>DTO de salida para el catalogo de tipos de documento.</p>
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
public class CatTipoDocumentoResponse {

    /** Identificador del tipo de documento. */
    private Short idTipoDocumento;

    /** Nombre del tipo de documento. */
    private String nombre;

    /** Fecha de captura. */
    private Instant fecCaptura;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    private Instant fecIniVigencia;

    /** Indicador de activo. */
    private Boolean blnActivo;

    /** Identificador de rango de resolucion de imagen. */
    private String ideRangoResolucionImagen;

    /** Tamanio maximo. */
    private Short tamanioMaximo;
}
