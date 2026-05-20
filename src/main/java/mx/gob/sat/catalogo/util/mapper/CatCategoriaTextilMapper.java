package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.categoriatextil.CatCategoriaTextilResponse;
import mx.gob.sat.catalogo.model.entity.CatCategoriaTextil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

/**
 * <b>Class:</b> CatCategoriaTextilMapper.java <br>
 * <b>Description:</b>
 * <p>Mapper MapStruct para el catalogo de categorias textiles.</p>
 *
 * @author Javier Chávez Barrios
 * @email jchavezb@ultrasist.com.mx
 *
 * @created 18 de mayo del 2026
 * @version 1.0
 * @category Mapper
 */
@Mapper(componentModel = "spring")
public interface CatCategoriaTextilMapper {

    /**
     * Convierte entidad a response DTO.
     *
     * @param entity Entidad CatCategoriaTextil.
     * @return Response DTO.
     */
    @Mapping(source = "cveUnidadMedida.cveUnidadMedida", target = "cveUnidadMedida")
    @Mapping(source = "cveUnidadMedida.descripcion", target = "nombreUnidadMedida")
    @Mapping(source = "cveUnidadMedidaEquivalente.cveUnidadMedida", target = "cveUnidadMedidaEquivalente")
    @Mapping(source = "cveUnidadMedidaEquivalente.descripcion", target = "nombreUnidadMedidaEquivalente")
    CatCategoriaTextilResponse toResponse(CatCategoriaTextil entity);

    /**
     * Convierte lista de entidades a lista de response DTOs.
     *
     * @param entities Lista de entidades.
     * @return Lista de response DTOs.
     */
    List<CatCategoriaTextilResponse> toResponseList(List<CatCategoriaTextil> entities);
}
