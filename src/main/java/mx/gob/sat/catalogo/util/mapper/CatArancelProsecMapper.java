package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.arancelprosec.CatArancelProsecResponse;
import mx.gob.sat.catalogo.model.entity.CatArancelProsec;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatArancelProsecMapper {
    @Mapping(source = "id.cveFraccion", target = "cveFraccion")
    @Mapping(source = "id.cveSectorProsec", target = "cveSectorProsec")
    CatArancelProsecResponse toResponse(CatArancelProsec entity);
    List<CatArancelProsecResponse> toResponseList(List<CatArancelProsec> entities);
}
