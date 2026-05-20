package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.subdivisionfraccion.CatSubdivisionFraccionResponse;
import mx.gob.sat.catalogo.model.entity.CatSubdivisionFraccion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatSubdivisionFraccionMapper {
    @Mapping(source = "cveFraccion.cveFraccion", target = "cveFraccion")
    CatSubdivisionFraccionResponse toResponse(CatSubdivisionFraccion entity);
    List<CatSubdivisionFraccionResponse> toResponseList(List<CatSubdivisionFraccion> entities);
}
