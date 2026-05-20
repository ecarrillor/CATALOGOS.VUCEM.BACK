package mx.gob.sat.catalogo.util;

import org.springframework.data.domain.Sort;

import java.util.Map;

/**
 * <b>Class:</b> SortValidator.java <br>
 * <b>Description:</b>
 * <p>Utilidad para validar y construir objetos {@code Sort} a partir de columnas permitidas.
 * Previene inyeccion de columnas arbitrarias en las consultas paginadas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Utilidad
 */
public final class SortValidator {

    private SortValidator() {
        throw new IllegalStateException("Clase SortValidator no puede ser instanciada");
    }

    /**
     * Construye un objeto {@code Sort} validado contra el mapa de columnas permitidas.
     * Si la columna no esta en el mapa o es nula/vacia, retorna {@code Sort.unsorted()}.
     *
     * @param sortBy Nombre de la columna por la que se desea ordenar.
     * @param sortDir Direccion del ordenamiento: {@code "asc"} o {@code "desc"}.
     * @param allowedColumns Mapa de nombre de columna permitida a nombre de campo JPA.
     * @return Objeto {@code Sort} valido o {@code Sort.unsorted()} si la columna no es valida.
     */
    public static Sort buildSort(final String sortBy, final String sortDir,
            final Map<String, String> allowedColumns) {
        if (sortBy == null || sortBy.isBlank()) {
            return Sort.unsorted();
        }
        final String mappedColumn = allowedColumns.get(sortBy);
        if (mappedColumn == null) {
            return Sort.unsorted();
        }
        final Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;
        return Sort.by(direction, mappedColumn);
    }
}
