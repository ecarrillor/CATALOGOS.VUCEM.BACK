package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.parametro.CatParametroResponse;
import mx.gob.sat.catalogo.model.entity.CatParametro;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatParametroMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de parametros del sistema.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatParametroMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatParametro.
     * @return Response DTO.
     */
    @Mapping(source = "idDependencia.id", target = "idDependencia")
    @Mapping(source = "idDependencia.nombre", target = "dependencia")
    CatParametroResponse toResponse(CatParametro entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatParametroResponse> toResponseList(List<CatParametro> entities);
}
