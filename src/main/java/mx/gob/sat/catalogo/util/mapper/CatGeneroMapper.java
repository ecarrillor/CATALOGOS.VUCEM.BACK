package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.genero.CatGeneroRequest;
import mx.gob.sat.catalogo.controller.response.genero.CatGeneroResponse;
import mx.gob.sat.catalogo.model.entity.CatGenero;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatGeneroMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de generos.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatGeneroMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatGenero.
     * @return Response DTO.
     */
    CatGeneroResponse toResponse(CatGenero entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatGeneroResponse> toResponseList(List<CatGenero> entities);

    /**
     * Convierte request DTO a entidad.
     *
     * @param request Request DTO.
     * @return Entidad CatGenero.
     */
    CatGenero toEntity(CatGeneroRequest request);
}
