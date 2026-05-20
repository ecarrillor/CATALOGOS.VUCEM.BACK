package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.dianolaborable.CatDiaNoLaborableResponse;
import mx.gob.sat.catalogo.model.entity.CatDiaNoLaborable;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatDiaNoLaborableMapper {
    @Mapping(source = "id.fecNoLaborable", target = "fecNoLaborable")
    @Mapping(source = "id.cveCalendario", target = "cveCalendario")
    CatDiaNoLaborableResponse toResponse(CatDiaNoLaborable entity);
    List<CatDiaNoLaborableResponse> toResponseList(List<CatDiaNoLaborable> entities);
}
