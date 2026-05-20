package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.subpartidafraccion.CatSubpartidaFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatSubpartidaFraccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Mapper para el catalogo de subpartida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Mapper(componentModel = "spring")
public interface CatSubpartidaFraccionMapper {
    @Mapping(source = "id.cveSubpartidaFraccion", target = "cveSubpartidaFraccion")
    @Mapping(source = "id.cveCapituloFraccion", target = "cveCapituloFraccion")
    @Mapping(source = "id.cvePartidaFraccion", target = "cvePartidaFraccion")
    CatSubpartidaFraccionResponse toResponse(CatSubpartidaFraccion entity);
    List<CatSubpartidaFraccionResponse> toResponseList(List<CatSubpartidaFraccion> entities);
}
