package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.declaraciontramite.CatDeclaracionTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatDeclaracionTramite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatDeclaracionTramiteMapper {
    @Mapping(source = "id.cveDeclaracion", target = "cveDeclaracion")
    @Mapping(source = "id.idTipoTramite", target = "idTipoTramite")
    CatDeclaracionTramiteResponse toResponse(CatDeclaracionTramite entity);
    List<CatDeclaracionTramiteResponse> toResponseList(List<CatDeclaracionTramite> entities);
}
