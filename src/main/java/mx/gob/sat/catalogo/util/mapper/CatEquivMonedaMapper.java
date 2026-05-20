package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.equivmoneda.CatEquivMonedaResponse;
import mx.gob.sat.catalogo.model.entity.CatEquivMoneda;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatEquivMonedaMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de equivalencias de moneda.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatEquivMonedaMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatEquivMoneda.
     * @return Response DTO.
     */
    @Mapping(source = "cveMonedaOrigen.cveMoneda", target = "cveMonedaOrigen")
    @Mapping(source = "cveMonedaOrigen.nombre", target = "nombreMonedaOrigen")
    @Mapping(source = "cveMonedaDestino.cveMoneda", target = "cveMonedaDestino")
    @Mapping(source = "cveMonedaDestino.nombre", target = "nombreMonedaDestino")
    CatEquivMonedaResponse toResponse(CatEquivMoneda entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatEquivMonedaResponse> toResponseList(List<CatEquivMoneda> entities);
}
