package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.tipotramite.CatTipoTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatTipoTramite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatTipoTramiteMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de tipos de tramite.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatTipoTramiteMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatTipoTramite.
     * @return Response DTO.
     */
    @Mapping(source = "idDependencia.nombre", target = "nombreDependencia")
    CatTipoTramiteResponse toResponse(CatTipoTramite entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatTipoTramiteResponse> toResponseList(List<CatTipoTramite> entities);
}
