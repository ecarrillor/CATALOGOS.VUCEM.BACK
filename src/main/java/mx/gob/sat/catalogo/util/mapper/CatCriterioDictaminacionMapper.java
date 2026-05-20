package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.criteriodictaminacion.CatCriterioDictaminacionRequest;
import mx.gob.sat.catalogo.controller.response.criteriodictaminacion.CatCriterioDictaminacionResponse;
import mx.gob.sat.catalogo.model.entity.CatCriterioDictaminacion;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatCriterioDictaminacionMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de CriterioDictaminacion.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCriterioDictaminacionMapper {

    /** Convierte entidad a response DTO. */
    CatCriterioDictaminacionResponse toResponse(CatCriterioDictaminacion entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatCriterioDictaminacionResponse> toResponseList(List<CatCriterioDictaminacion> entities);

    /** Convierte request DTO a entidad. */
    CatCriterioDictaminacion toEntity(CatCriterioDictaminacionRequest request);
}
