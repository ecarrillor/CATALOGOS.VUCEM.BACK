package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.dictamentramite.CatDictamenTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatDictamenTramite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatDictamenTramiteMapper {
    @Mapping(source = "id.idTipoTramite", target = "idTipoTramite")
    @Mapping(source = "id.idTipoDictamen", target = "idTipoDictamen")
    CatDictamenTramiteResponse toResponse(CatDictamenTramite entity);
    List<CatDictamenTramiteResponse> toResponseList(List<CatDictamenTramite> entities);
}
