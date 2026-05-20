package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.partidafraccion.CatPartidaFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatPartidaFraccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Mapper para el catalogo de partida fraccion.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Mapper(componentModel = "spring")
public interface CatPartidaFraccionMapper {
    @Mapping(source = "id.cveCapituloFraccion", target = "cveCapituloFraccion")
    @Mapping(source = "id.cvePartidaFraccion", target = "cvePartidaFraccion")
    CatPartidaFraccionResponse toResponse(CatPartidaFraccion entity);
    List<CatPartidaFraccionResponse> toResponseList(List<CatPartidaFraccion> entities);
}
