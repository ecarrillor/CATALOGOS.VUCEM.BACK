package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.calendario.CatCalendarioRequest;
import mx.gob.sat.catalogo.controller.response.calendario.CatCalendarioResponse;
import mx.gob.sat.catalogo.model.entity.CatCalendario;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatCalendarioMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de calendarios.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCalendarioMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatCalendario.
     * @return Response DTO.
     */
    CatCalendarioResponse toResponse(CatCalendario entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatCalendarioResponse> toResponseList(List<CatCalendario> entities);

    /**
     * Convierte request DTO a entidad.
     *
     * @param request Request DTO.
     * @return Entidad CatCalendario.
     */
    CatCalendario toEntity(CatCalendarioRequest request);
}
