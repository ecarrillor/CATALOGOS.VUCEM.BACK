package mx.gob.sat.catalogo.controller.request.tipodocumento;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

/**
 * <b>Class:</b> CatTipoDocumentoRequest.java <br>
 * <b>Description:</b>
 * <p>DTO de entrada para el catalogo de tipos de documento.</p>
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
public class CatTipoDocumentoRequest {

    /** Nombre del tipo de documento. */
    @Size(max = 2000)
    private String nombre;

    /** Fecha de captura. */
    private Instant fecCaptura;

    /** Fecha de fin de vigencia. */
    private Instant fecFinVigencia;

    /** Fecha de inicio de vigencia. */
    @NotNull
    private Instant fecIniVigencia;

    /** Indicador de activo. */
    @NotNull
    private Boolean blnActivo;

    /** Identificador de rango de resolucion de imagen. */
    @Size(max = 20)
    private String ideRangoResolucionImagen;

    /** Tamanio maximo. */
    private Short tamanioMaximo;
}
