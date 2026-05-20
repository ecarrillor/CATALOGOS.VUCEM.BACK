package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.dependencia.CatDependenciaResponse;
import mx.gob.sat.catalogo.model.entity.CatDependencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatDependenciaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de dependencias.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatDependenciaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatDependencia.
     * @return Response DTO.
     */
    @Mapping(source = "id", target = "cveDependencia")
    @Mapping(source = "nombre", target = "nombreDependencia")
    @Mapping(source = "blnTramitesVu", target = "tramiteVU")
    @Mapping(source = "cveCalendario.nombre", target = "nombreCalendario")
    @Mapping(source = "cveCalendario.cveCalendario", target = "idCalendario")
    CatDependenciaResponse toResponse(CatDependencia entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatDependenciaResponse> toResponseList(List<CatDependencia> entities);
}
