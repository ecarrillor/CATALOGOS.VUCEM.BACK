package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.casfraccionttra.CatCasFraccionTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatCasFraccionTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatCasFraccionTtraMapper {
    @Mapping(source = "idCas.idCas", target = "idCas")
    @Mapping(source = "cveFraccion.cveFraccion", target = "cveFraccion")
    @Mapping(source = "idTipoTramite.idTipoTramite", target = "idTipoTramite")
    CatCasFraccionTtraResponse toResponse(CatCasFraccionTtra entity);
    List<CatCasFraccionTtraResponse> toResponseList(List<CatCasFraccionTtra> entities);
}
