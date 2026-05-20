package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.suplencia.CatSuplenciaResponse;
import mx.gob.sat.catalogo.model.entity.CatSuplencia;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatSuplenciaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de suplencias.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatSuplenciaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatSuplencia.
     * @return Response DTO.
     */
    @Mapping(source = "idDependencia.nombre", target = "nombreDependencia")
    CatSuplenciaResponse toResponse(CatSuplencia entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatSuplenciaResponse> toResponseList(List<CatSuplencia> entities);
}
