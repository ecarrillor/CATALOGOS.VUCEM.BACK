package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.localidad.CatLocalidadResponse;
import mx.gob.sat.catalogo.model.entity.CatLocalidad;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatLocalidadMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de localidades.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatLocalidadMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatLocalidad.
     * @return Response DTO.
     */
    @Mapping(source = "cveDelegMun.cveDelegMun", target = "cveDelegMun")
    CatLocalidadResponse toResponse(CatLocalidad entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatLocalidadResponse> toResponseList(List<CatLocalidad> entities);
}
