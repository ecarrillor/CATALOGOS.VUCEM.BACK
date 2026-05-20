package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.especie.CatEspecieRequest;
import mx.gob.sat.catalogo.controller.response.especie.CatEspecieResponse;
import mx.gob.sat.catalogo.model.entity.CatEspecie;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatEspecieMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de Especie.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatEspecieMapper {

    /** Convierte entidad a response DTO. */
    CatEspecieResponse toResponse(CatEspecie entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatEspecieResponse> toResponseList(List<CatEspecie> entities);

    /** Convierte request DTO a entidad. */
    CatEspecie toEntity(CatEspecieRequest request);
}
