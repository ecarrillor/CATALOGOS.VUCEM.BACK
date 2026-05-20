package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.colonia.CatColoniaResponse;
import mx.gob.sat.catalogo.model.entity.CatColonia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatColoniaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de colonias.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatColoniaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatColonia.
     * @return Response DTO.
     */
    @Mapping(source = "cveDelegMun.cveDelegMun", target = "cveDelegMun")
    @Mapping(source = "cveLocalidad.cveLocalidad", target = "cveLocalidad")
    CatColoniaResponse toResponse(CatColonia entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatColoniaResponse> toResponseList(List<CatColonia> entities);
}
