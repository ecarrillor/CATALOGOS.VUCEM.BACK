package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.documentotramite.CatDocumentoTramiteResponse;
import mx.gob.sat.catalogo.model.entity.CatDocumentoTramite;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatDocumentoTramiteMapper {
    @Mapping(source = "id.idTipoDoc", target = "idTipoDoc")
    @Mapping(source = "id.idTipoTramite", target = "idTipoTramite")
    CatDocumentoTramiteResponse toResponse(CatDocumentoTramite entity);
    List<CatDocumentoTramiteResponse> toResponseList(List<CatDocumentoTramite> entities);
}
