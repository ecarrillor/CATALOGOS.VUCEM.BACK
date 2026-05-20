package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.catalogod.CatCatalogoDResponse;
import mx.gob.sat.catalogo.model.entity.CatCatalogoD;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatCatalogoDMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de catalogos D (detalle).</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCatalogoDMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatCatalogoD.
     * @return Response DTO.
     */
    @Mapping(source = "cveCatalogoH.cveCatalogoH", target = "cveCatalogoH")
    @Mapping(source = "cveCatalogoH.nomCatalogo", target = "nomCatalogoH")
    @Mapping(source = "cveCatalogoR.cveCatalogo", target = "cveCatalogoR")
    CatCatalogoDResponse toResponse(CatCatalogoD entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatCatalogoDResponse> toResponseList(List<CatCatalogoD> entities);
}
