package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.scian.CatScianRequest;
import mx.gob.sat.catalogo.controller.response.scian.CatScianResponse;
import mx.gob.sat.catalogo.model.entity.CatScian;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatScianMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de Scian.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatScianMapper {

    /** Convierte entidad a response DTO. */
    CatScianResponse toResponse(CatScian entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatScianResponse> toResponseList(List<CatScian> entities);

    /** Convierte request DTO a entidad. */
    CatScian toEntity(CatScianRequest request);
}
