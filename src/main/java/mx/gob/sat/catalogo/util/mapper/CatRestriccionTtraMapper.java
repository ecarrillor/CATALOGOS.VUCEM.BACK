package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.restriccionttra.CatRestriccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatRestriccionTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatRestriccionTtraMapper {

    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatRestriccionTtraResponse toResponse(CatRestriccionTtra entity);

    List<CatRestriccionTtraResponse> toResponseList(List<CatRestriccionTtra> entities);
}
