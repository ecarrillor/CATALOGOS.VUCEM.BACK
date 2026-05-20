package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.mediotransportettra.CatMedioTransporteTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatMedioTransporteTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatMedioTransporteTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de medios de transporte ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatMedioTransporteTtraMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatMedioTransporteTtra.
     * @return Response DTO.
     */
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatMedioTransporteTtraResponse toResponse(CatMedioTransporteTtra entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatMedioTransporteTtraResponse> toResponseList(List<CatMedioTransporteTtra> entities);
}
