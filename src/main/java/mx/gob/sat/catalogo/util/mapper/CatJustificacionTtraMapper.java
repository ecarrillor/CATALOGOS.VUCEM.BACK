package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.justificacionttra.CatJustificacionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatJustificacionTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatJustificacionTtraMapper {

    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatJustificacionTtraResponse toResponse(CatJustificacionTtra entity);

    List<CatJustificacionTtraResponse> toResponseList(List<CatJustificacionTtra> entities);
}
