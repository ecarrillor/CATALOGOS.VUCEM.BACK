package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.usomercanciattra.CatUsoMercanciaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatUsoMercanciaTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatUsoMercanciaTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de uso de mercancia ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatUsoMercanciaTtraMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatUsoMercanciaTtra.
     * @return Response DTO.
     */
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatUsoMercanciaTtraResponse toResponse(CatUsoMercanciaTtra entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatUsoMercanciaTtraResponse> toResponseList(List<CatUsoMercanciaTtra> entities);
}
