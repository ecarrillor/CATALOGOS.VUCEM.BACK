package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.unidadmedida.CatUnidadMedidaRequest;
import mx.gob.sat.catalogo.controller.response.unidadmedida.CatUnidadMedidaResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadMedida;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatUnidadMedidaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de unidades de medida.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatUnidadMedidaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatUnidadMedida.
     * @return Response DTO.
     */
    CatUnidadMedidaResponse toResponse(CatUnidadMedida entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatUnidadMedidaResponse> toResponseList(List<CatUnidadMedida> entities);

    /**
     * Convierte request DTO a entidad.
     *
     * @param request Request DTO.
     * @return Entidad CatUnidadMedida.
     */
    CatUnidadMedida toEntity(CatUnidadMedidaRequest request);
}
