package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.paisrestriccionttra.CatPaisRestriccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatPaisRestriccionTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatPaisRestriccionTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de paises restriccion TTRA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatPaisRestriccionTtraMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatPaisRestriccionTtra.
     * @return Response DTO.
     */
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    @Mapping(source = "cvePais.cvePais", target = "cvePais")
    CatPaisRestriccionTtraResponse toResponse(CatPaisRestriccionTtra entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatPaisRestriccionTtraResponse> toResponseList(List<CatPaisRestriccionTtra> entities);
}
