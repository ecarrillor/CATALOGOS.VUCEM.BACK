package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.vidasilvestre.CatVidaSilvestreResponse;
import mx.gob.sat.catalogo.model.entity.CatVidaSilvestre;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatVidaSilvestreMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de vida silvestre.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatVidaSilvestreMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatVidaSilvestre.
     * @return Response DTO.
     */
    @Mapping(source = "idGenero.idGenero", target = "idGenero")
    @Mapping(source = "idGenero.descGenero", target = "nombreGenero")
    @Mapping(source = "idEspecie.idEspecie", target = "idEspecie")
    @Mapping(source = "idEspecie.descEspecie", target = "descEspecie")
    CatVidaSilvestreResponse toResponse(CatVidaSilvestre entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatVidaSilvestreResponse> toResponseList(List<CatVidaSilvestre> entities);
}
