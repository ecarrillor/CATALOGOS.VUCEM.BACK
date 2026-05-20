package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.plazomaximoauttramite.CatPlazoMaximoAutTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatPlazoMaximoAutTramite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatPlazoMaximoAutTramiteMapper {
    @Mapping(source = "id.idTipoTramite", target = "idTipoTramite")
    @Mapping(source = "id.fecIniVigencia", target = "fecIniVigencia")
    CatPlazoMaximoAutTramiteResponse toResponse(CatPlazoMaximoAutTramite entity);
    List<CatPlazoMaximoAutTramiteResponse> toResponseList(List<CatPlazoMaximoAutTramite> entities);
}
