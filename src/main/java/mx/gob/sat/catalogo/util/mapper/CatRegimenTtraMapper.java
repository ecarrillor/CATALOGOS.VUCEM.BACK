package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.regimenttra.CatRegimenTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatRegimenTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatRegimenTtraMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de regimenes ttra.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatRegimenTtraMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatRegimenTtra.
     * @return Response DTO.
     */
    @Mapping(source = "cveRegimen.cveRegimen", target = "cveRegimen")
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatRegimenTtraResponse toResponse(CatRegimenTtra entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatRegimenTtraResponse> toResponseList(List<CatRegimenTtra> entities);
}
