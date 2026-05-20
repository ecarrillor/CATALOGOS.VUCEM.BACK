package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.observacionttra.CatObservacionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatObservacionTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatObservacionTtraMapper {

    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatObservacionTtraResponse toResponse(CatObservacionTtra entity);

    List<CatObservacionTtraResponse> toResponseList(List<CatObservacionTtra> entities);
}
