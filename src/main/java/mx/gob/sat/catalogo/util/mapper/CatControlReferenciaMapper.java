package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.controlreferencia.CatControlReferenciaResponse;
import mx.gob.sat.catalogo.model.entity.CatControlReferencia;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * Mapper MapStruct para el catalogo de control de referencia.
 */
@Mapper(componentModel = "spring")
public interface CatControlReferenciaMapper {

    /** Convierte entidad a response DTO. */
    CatControlReferenciaResponse toResponse(CatControlReferencia entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatControlReferenciaResponse> toResponseList(List<CatControlReferencia> entities);
}
