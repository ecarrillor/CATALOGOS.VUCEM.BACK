package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.tipoproductottra.CatTipoProductoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoProductoTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatTipoProductoTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de tipos de producto ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatTipoProductoTtraMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatTipoProductoTtra.
     * @return Response DTO.
     */
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatTipoProductoTtraResponse toResponse(CatTipoProductoTtra entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatTipoProductoTtraResponse> toResponseList(List<CatTipoProductoTtra> entities);
}
