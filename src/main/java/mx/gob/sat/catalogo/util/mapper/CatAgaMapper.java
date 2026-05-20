package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.aga.CatAgaRequest;
import mx.gob.sat.catalogo.controller.response.aga.CatAgaResponse;
import mx.gob.sat.catalogo.model.entity.CatAga;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatAgaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de AGA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatAgaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatAga.
     * @return Response DTO.
     */
    CatAgaResponse toResponse(CatAga entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatAgaResponse> toResponseList(List<CatAga> entities);

    /**
     * Convierte request DTO a entidad.
     *
     * @param request Request DTO.
     * @return Entidad CatAga.
     */
    CatAga toEntity(CatAgaRequest request);
}
