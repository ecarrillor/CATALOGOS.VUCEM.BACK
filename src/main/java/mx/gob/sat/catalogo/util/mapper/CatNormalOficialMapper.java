package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.normaloficial.CatNormalOficialResponse;
import mx.gob.sat.catalogo.model.entity.CatNormalOficial;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatNormalOficialMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de normas oficiales.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatNormalOficialMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatNormalOficial.
     * @return Response DTO.
     */
    @Mapping(source = "cvePais.nombre", target = "nombrePais")
    @Mapping(source = "idNormaOficialR.idNormalOficial", target = "idNormaOficialR")
    CatNormalOficialResponse toResponse(CatNormalOficial entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatNormalOficialResponse> toResponseList(List<CatNormalOficial> entities);
}
