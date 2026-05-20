package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.lenguaje.CatLenguajeRequest;
import mx.gob.sat.catalogo.controller.response.lenguaje.CatLenguajeResponse;
import mx.gob.sat.catalogo.model.entity.CatLenguaje;
import org.mapstruct.Mapper;

import java.util.List;

/**
 * <b>Class:</b> CatLenguajeMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de lenguajes.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatLenguajeMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatLenguaje.
     * @return Response DTO.
     */
    CatLenguajeResponse toResponse(CatLenguaje entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatLenguajeResponse> toResponseList(List<CatLenguaje> entities);

    /**
     * Convierte request DTO a entidad.
     *
     * @param request Request DTO.
     * @return Entidad CatLenguaje.
     */
    CatLenguaje toEntity(CatLenguajeRequest request);
}
