package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.declaracion.CatDeclaracionRequest;
import mx.gob.sat.catalogo.controller.response.declaracion.CatDeclaracionResponse;
import mx.gob.sat.catalogo.model.entity.CatDeclaracion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatDeclaracionMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de Declaracion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatDeclaracionMapper {

    /** Convierte entidad a response DTO. */
    CatDeclaracionResponse toResponse(CatDeclaracion entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatDeclaracionResponse> toResponseList(List<CatDeclaracion> entities);

    /** Convierte request DTO a entidad. */
    CatDeclaracion toEntity(CatDeclaracionRequest request);
}
