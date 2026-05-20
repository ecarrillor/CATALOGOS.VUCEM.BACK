package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.tratamientoespecial.CatTratamientoEspecialRequest;
import mx.gob.sat.catalogo.controller.response.tratamientoespecial.CatTratamientoEspecialResponse;
import mx.gob.sat.catalogo.model.entity.CatTratamientoEspecial;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatTratamientoEspecialMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de TratamientoEspecial.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatTratamientoEspecialMapper {

    /** Convierte entidad a response DTO. */
    CatTratamientoEspecialResponse toResponse(CatTratamientoEspecial entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatTratamientoEspecialResponse> toResponseList(List<CatTratamientoEspecial> entities);

    /** Convierte request DTO a entidad. */
    CatTratamientoEspecial toEntity(CatTratamientoEspecialRequest request);
}
