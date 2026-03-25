package com.example.vucem_catalogos_service.core.util;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;
import java.util.Set;

public class SortValidator {

    private SortValidator() {}

    /**
     * Construye un Sort validado (columnas simples, sin normalización de texto).
     * Retorna Sort.unsorted() si sortBy es null/blank (el caller decide el default).
     */
    public static Sort buildSort(String sortBy, String sortDir, Map<String, String> allowedColumns) {
        if (sortBy == null || sortBy.isBlank()) {
            return Sort.unsorted();
        }

        if (!allowedColumns.containsKey(sortBy)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ordenamiento no permitido por la columna '" + sortBy + "'. " +
                    "Columnas permitidas: " + allowedColumns.keySet()
            );
        }

        String entityPath = allowedColumns.get(sortBy);
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        return Sort.by(direction, entityPath);
    }

    /**
     * Construye un Sort validado con soporte para columnas texto y fallback automático.
     *
     * <ul>
     *   <li>Columnas en {@code textColumns}: ordenan con {@code LOWER(TRIM(campo))}
     *       y los valores NULL / vacío / "." / "-" van siempre al final.</li>
     *   <li>Columnas normales: usan {@code Sort.by(direction, path)}.</li>
     *   <li>Si {@code sortBy} es null/blank: usa {@code defaultSortKey} con ASC.</li>
     *   <li>Si {@code sortDir} es null/blank/inválido: usa ASC.</li>
     * </ul>
     *
     * <p><b>Importante:</b> los valores de {@code allowedColumns} deben ser rutas JPQL
     * cualificadas con el alias de la query (ej. {@code "e.descNombreComun"},
     * {@code "gen.descGenero"}) para que {@code JpaSort.unsafe()} las inyecte
     * correctamente en el ORDER BY.</p>
     *
     * @param sortBy         clave frontend (ej. "descNombreComun"); null/blank → usa default
     * @param sortDir        "asc" | "desc"; null/blank → ASC
     * @param allowedColumns mapa clave-frontend → ruta JPQL cualificada
     * @param textColumns    claves que requieren LOWER(TRIM) + nulls-al-final
     * @param defaultSortKey clave de {@code allowedColumns} a usar cuando sortBy viene vacío
     * @return Sort nunca unsorted; siempre tiene al menos un criterio de orden
     */
    public static Sort buildSort(
            String sortBy,
            String sortDir,
            Map<String, String> allowedColumns,
            Set<String> textColumns,
            String defaultSortKey
    ) {
        Sort.Direction direction = "desc".equalsIgnoreCase(sortDir)
                ? Sort.Direction.DESC
                : Sort.Direction.ASC;

        String resolvedKey;
        if (sortBy == null || sortBy.isBlank()) {
            resolvedKey = defaultSortKey;
            direction = Sort.Direction.ASC;
        } else if (!allowedColumns.containsKey(sortBy)) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Ordenamiento no permitido por la columna '" + sortBy + "'. " +
                    "Columnas permitidas: " + allowedColumns.keySet()
            );
        } else {
            resolvedKey = sortBy;
        }

        String path = allowedColumns.get(resolvedKey);

        if (textColumns.contains(resolvedKey)) {
            return buildTextSort(path, direction);
        }
        return Sort.by(direction, path);
    }

    /**
     * Sort para columnas texto:
     * <ol>
     *   <li>Primero: registros con valores reales (no null, no vacío, no ".", no "-")</li>
     *   <li>Luego: orden alfabético insensible a mayúsculas y espacios laterales</li>
     *   <li>Al final: NULL, vacío, "." y "-"</li>
     * </ol>
     */
    private static Sort buildTextSort(String path, Sort.Direction direction) {
        String nullsAndGarbageLast =
                "CASE WHEN " + path + " IS NULL THEN 1 " +
                "WHEN TRIM(" + path + ") = '' THEN 1 " +
                "WHEN TRIM(" + path + ") = '.' THEN 1 " +
                "WHEN TRIM(" + path + ") = '-' THEN 1 " +
                "ELSE 0 END";
        String normalized = "LOWER(TRIM(" + path + "))";

        return JpaSort.unsafe(Sort.Direction.ASC, nullsAndGarbageLast)
                      .and(JpaSort.unsafe(direction, normalized));
    }
}
