package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.delegmun.CatDelegMunResponse;
import mx.gob.sat.catalogo.model.entity.CatDelegMun;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatDelegMunMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de delegaciones y municipios.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatDelegMunMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatDelegMun.
     * @return Response DTO.
     */
    @Mapping(source = "cveEntidad.cveEntidad", target = "cveEntidad")
    @Mapping(source = "cveEntidad.nombre", target = "nombreEntidad")
    CatDelegMunResponse toResponse(CatDelegMun entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatDelegMunResponse> toResponseList(List<CatDelegMun> entities);
}
