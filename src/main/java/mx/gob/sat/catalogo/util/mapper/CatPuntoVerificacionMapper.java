package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.puntoverificacion.CatPuntoVerificacionRequest;
import mx.gob.sat.catalogo.controller.response.puntoverificacion.CatPuntoVerificacionResponse;
import mx.gob.sat.catalogo.model.entity.CatPuntoVerificacion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatPuntoVerificacionMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de PuntoVerificacion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatPuntoVerificacionMapper {

    /** Convierte entidad a response DTO. */
    CatPuntoVerificacionResponse toResponse(CatPuntoVerificacion entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatPuntoVerificacionResponse> toResponseList(List<CatPuntoVerificacion> entities);

    /** Convierte request DTO a entidad. */
    CatPuntoVerificacion toEntity(CatPuntoVerificacionRequest request);
}
