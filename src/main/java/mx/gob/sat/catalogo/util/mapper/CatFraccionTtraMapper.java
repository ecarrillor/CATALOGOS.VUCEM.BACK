package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.fraccionttra.CatFraccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatFraccionTtraMapper {
    @Mapping(source = "cveFraccion.cveFraccion", target = "cveFraccion")
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    @Mapping(source = "idCategoriaTextil.idCategoriaTextil", target = "idCategoriaTextil")
    CatFraccionTtraResponse toResponse(CatFraccionTtra entity);
    List<CatFraccionTtraResponse> toResponseList(List<CatFraccionTtra> entities);
}
