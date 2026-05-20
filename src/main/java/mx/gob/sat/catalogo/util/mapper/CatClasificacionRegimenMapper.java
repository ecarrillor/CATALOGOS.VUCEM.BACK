package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.clasificacionregimen.CatClasificacionRegimenResponse;
import mx.gob.sat.catalogo.model.entity.CatClasificacionRegimen;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

/**
 * Mapper para el catalogo de clasificacion de regimen.
 *
 * @author Javier Chávez Barrios
 * @since 18 de mayo del 2026
 */
@Mapper(componentModel = "spring")
public interface CatClasificacionRegimenMapper {
    @Mapping(source = "id.cveClasificacionRegimen", target = "cveClasificacionRegimen")
    @Mapping(source = "id.cveRegimen", target = "cveRegimen")
    CatClasificacionRegimenResponse toResponse(CatClasificacionRegimen entity);
    List<CatClasificacionRegimenResponse> toResponseList(List<CatClasificacionRegimen> entities);
}
