package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.regimen.CatRegimenRequest;
import mx.gob.sat.catalogo.controller.response.regimen.CatRegimenResponse;
import mx.gob.sat.catalogo.model.entity.CatRegimen;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatRegimenMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de regimenes.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatRegimenMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatRegimen.
     * @return Response DTO.
     */
    CatRegimenResponse toResponse(CatRegimen entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatRegimenResponse> toResponseList(List<CatRegimen> entities);

    /**
     * Convierte request DTO a entidad.
     *
     * @param request Request DTO.
     * @return Entidad CatRegimen.
     */
    CatRegimen toEntity(CatRegimenRequest request);
}
