package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.tarifa.CatTarifaResponse;
import mx.gob.sat.catalogo.model.entity.CatTarifa;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatTarifaMapper {
    @Mapping(source = "id.idTipoTramite", target = "idTipoTramite")
    @Mapping(source = "id.fecIniVigencia", target = "fecIniVigencia")
    @Mapping(source = "id.ideTipoTarifa", target = "ideTipoTarifa")
    CatTarifaResponse toResponse(CatTarifa entity);
    List<CatTarifaResponse> toResponseList(List<CatTarifa> entities);
}
