package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.fraccionhtsusa.CatFraccionHtsUsaResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionHtsUsa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatFraccionHtsUsaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de fracciones HTS USA.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatFraccionHtsUsaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatFraccionHtsUsa.
     * @return Response DTO.
     */
    @Mapping(source = "cveUnidadMedida.cveUnidadMedida", target = "cveUnidadMedida")
    CatFraccionHtsUsaResponse toResponse(CatFraccionHtsUsa entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatFraccionHtsUsaResponse> toResponseList(List<CatFraccionHtsUsa> entities);
}
