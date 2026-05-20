package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.motivottra.CatMotivoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatMotivoTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMotivoTtraMapper {

    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatMotivoTtraResponse toResponse(CatMotivoTtra entity);

    List<CatMotivoTtraResponse> toResponseList(List<CatMotivoTtra> entities);
}
