package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.fraccionarancelaria.CatFraccionArancelariaResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionArancelaria;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatFraccionArancelariaMapper {
    CatFraccionArancelariaResponse toResponse(CatFraccionArancelaria entity);
    List<CatFraccionArancelariaResponse> toResponseList(List<CatFraccionArancelaria> entities);
}
