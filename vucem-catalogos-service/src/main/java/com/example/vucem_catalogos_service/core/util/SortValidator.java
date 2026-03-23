package com.example.vucem_catalogos_service.core.util;

import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.Map;

public class SortValidator {

    private SortValidator() {}

    /**
     * Construye un {@link Sort} validado contra la lista de columnas permitidas.
     *
     * @param sortBy         nombre de columna enviado por el frontend (ej. "descNombreComun")
     * @param sortDir        dirección: "asc" o "desc" (por defecto "asc")
     * @param allowedColumns mapa de nombre-frontend → ruta-entidad JPA permitida
     * @return Sort listo para usar en PageRequest.of(page, size, sort)
     * @throws ResponseStatusException 400 si sortBy no está en allowedColumns
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
}
