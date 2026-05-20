package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.semanalaboral.CatSemanaLaboralRequest;
import mx.gob.sat.catalogo.controller.response.semanalaboral.CatSemanaLaboralResponse;
import mx.gob.sat.catalogo.model.entity.CatSemanaLaboral;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatSemanaLaboralMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de SemanaLaboral.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatSemanaLaboralMapper {

    /** Convierte entidad a response DTO. */
    CatSemanaLaboralResponse toResponse(CatSemanaLaboral entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatSemanaLaboralResponse> toResponseList(List<CatSemanaLaboral> entities);

    /** Convierte request DTO a entidad. */
    CatSemanaLaboral toEntity(CatSemanaLaboralRequest request);
}
