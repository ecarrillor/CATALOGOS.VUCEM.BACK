package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.criterioorigen.CatCriterioOrigenRequest;
import mx.gob.sat.catalogo.controller.response.criterioorigen.CatCriterioOrigenResponse;
import mx.gob.sat.catalogo.model.entity.CatCriterioOrigen;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatCriterioOrigenMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de CriterioOrigen.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCriterioOrigenMapper {

    /** Convierte entidad a response DTO. */
    CatCriterioOrigenResponse toResponse(CatCriterioOrigen entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatCriterioOrigenResponse> toResponseList(List<CatCriterioOrigen> entities);

    /** Convierte request DTO a entidad. */
    CatCriterioOrigen toEntity(CatCriterioOrigenRequest request);
}
