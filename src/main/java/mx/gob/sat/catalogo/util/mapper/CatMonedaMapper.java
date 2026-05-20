package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.request.moneda.CatMonedaRequest;
import mx.gob.sat.catalogo.controller.response.moneda.CatMonedaResponse;
import mx.gob.sat.catalogo.model.entity.CatMoneda;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

/**
 * <b>Class:</b> CatMonedaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para convertir entidades de moneda a DTOs y viceversa.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface CatMonedaMapper {

    /**
     * Convierte una entidad {@code CatMoneda} a su DTO de respuesta.
     *
     * @param entidad Entidad JPA de moneda.
     * @return DTO de respuesta.
     */
    CatMonedaResponse toResponse(CatMoneda entidad);

    /**
     * Convierte una lista de entidades a lista de DTOs de respuesta.
     *
     * @param entidades Lista de entidades JPA.
     * @return Lista de DTOs de respuesta.
     */
    List<CatMonedaResponse> toResponseList(List<CatMoneda> entidades);

    /**
     * Convierte un DTO de request a entidad JPA.
     *
     * @param request DTO de request con los datos de la moneda.
     * @return Entidad JPA.
     */
    CatMoneda toEntity(CatMonedaRequest request);
}
