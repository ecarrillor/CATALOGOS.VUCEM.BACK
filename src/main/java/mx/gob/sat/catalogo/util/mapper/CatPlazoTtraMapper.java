package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.plazottra.CatPlazoTtraResponse;
import mx.gob.sat.catalogo.model.entity.CatPlazoTtra;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatPlazoTtraMapper {
    @Mapping(source = "id.idTipoTramite", target = "idTipoTramite")
    @Mapping(source = "id.idePlazoVigencia", target = "idePlazoVigencia")
    CatPlazoTtraResponse toResponse(CatPlazoTtra entity);
    List<CatPlazoTtraResponse> toResponseList(List<CatPlazoTtra> entities);
}
