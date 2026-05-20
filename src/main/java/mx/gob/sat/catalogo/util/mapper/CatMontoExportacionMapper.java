package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.montoexportacion.CatMontoExportacionResponse;
import mx.gob.sat.catalogo.model.entity.CatMontoExportacion;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatMontoExportacionMapper {
    @Mapping(source = "id.rfcExportador", target = "rfcExportador")
    @Mapping(source = "id.fecMontoExportacion", target = "fecMontoExportacion")
    CatMontoExportacionResponse toResponse(CatMontoExportacion entity);
    List<CatMontoExportacionResponse> toResponseList(List<CatMontoExportacion> entities);
}
