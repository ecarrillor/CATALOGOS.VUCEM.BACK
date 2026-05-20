package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.unidadadministrativa.CatUnidadAdministrativaResponse;
import mx.gob.sat.catalogo.model.entity.CatUnidadAdministrativa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatUnidadAdministrativaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de unidades administrativas.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatUnidadAdministrativaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatUnidadAdministrativa.
     * @return Response DTO.
     */
    @Mapping(source = "cveUnidadAdministrativaR.cveUnidadAdministrativa", target = "cveUnidadAdministrativaR")
    @Mapping(source = "cveEntidad.cveEntidad", target = "cveEntidad")
    @Mapping(source = "cveEntidad.nombre", target = "nombreEntidad")
    @Mapping(source = "idDependencia.nombre", target = "nombreDependencia")
    CatUnidadAdministrativaResponse toResponse(CatUnidadAdministrativa entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatUnidadAdministrativaResponse> toResponseList(List<CatUnidadAdministrativa> entities);
}
