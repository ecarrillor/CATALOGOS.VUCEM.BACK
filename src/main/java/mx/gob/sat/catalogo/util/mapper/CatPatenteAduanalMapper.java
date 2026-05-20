package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.patenteaduanal.CatPatenteAduanalRequest;
import mx.gob.sat.catalogo.controller.response.patenteaduanal.CatPatenteAduanalResponse;
import mx.gob.sat.catalogo.model.entity.CatPatenteAduanal;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatPatenteAduanalMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de PatenteAduanal.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatPatenteAduanalMapper {

    /** Convierte entidad a response DTO. */
    CatPatenteAduanalResponse toResponse(CatPatenteAduanal entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatPatenteAduanalResponse> toResponseList(List<CatPatenteAduanal> entities);

    /** Convierte request DTO a entidad. */
    CatPatenteAduanal toEntity(CatPatenteAduanalRequest request);
}
