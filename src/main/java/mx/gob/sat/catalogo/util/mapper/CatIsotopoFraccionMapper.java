package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.isotopofraccion.CatIsotopoFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatIsotopoFraccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatIsotopoFraccionMapper {
    @Mapping(source = "cveFraccion.cveFraccion", target = "cveFraccion")
    CatIsotopoFraccionResponse toResponse(CatIsotopoFraccion entity);
    List<CatIsotopoFraccionResponse> toResponseList(List<CatIsotopoFraccion> entities);
}
