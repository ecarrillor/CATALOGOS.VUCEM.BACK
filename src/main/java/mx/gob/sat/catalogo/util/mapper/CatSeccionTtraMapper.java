package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.seccionttra.CatSeccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatSeccionTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatSeccionTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de SeccionTtra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatSeccionTtraMapper {

    /** Convierte entidad a response DTO. */
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatSeccionTtraResponse toResponse(CatSeccionTtra entity);

    /** Convierte lista de entidades a lista de response DTOs. */
    List<CatSeccionTtraResponse> toResponseList(List<CatSeccionTtra> entities);
}
