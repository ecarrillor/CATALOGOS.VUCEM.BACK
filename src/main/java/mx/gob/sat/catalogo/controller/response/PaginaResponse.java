package mx.gob.sat.catalogo.controller.response;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * <b>Class:</b> PaginaResponse.java <br>
 * <b>Description:</b>
 * <p>DTO generico de respuesta paginada. Envuelve el contenido de una pagina junto con
 * los metadatos de paginacion para no exponer la interfaz {@code Page} de Spring Data.</p>
 *
 * @param <T> Tipo de los elementos contenidos en la pagina.
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
public class PaginaResponse<T> {

    /** Lista de elementos de la pagina actual. */
    private List<T> contenido;

    /** Numero de pagina actual (base 0). */
    private int pagina;

    /** Cantidad de elementos por pagina. */
    private int tamano;

    /** Total de elementos en todos los resultados. */
    private long total;

    /** Numero total de paginas disponibles. */
    private int totalPaginas;

    /** Indica si es la ultima pagina disponible. */
    private boolean ultima;
}
