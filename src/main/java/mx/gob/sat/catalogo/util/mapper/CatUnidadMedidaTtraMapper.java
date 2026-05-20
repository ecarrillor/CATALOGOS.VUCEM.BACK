package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.unidadmedidattra.CatUnidadMedidaTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadMedidaTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatUnidadMedidaTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de unidad de medida ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatUnidadMedidaTtraMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatUnidadMedidaTtra.
     * @return Response DTO.
     */
    @Mapping(source = "cveUnidadMedida.cveUnidadMedida", target = "cveUnidadMedida")
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatUnidadMedidaTtraResponse toResponse(CatUnidadMedidaTtra entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatUnidadMedidaTtraResponse> toResponseList(List<CatUnidadMedidaTtra> entities);
}
