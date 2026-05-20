package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.vigenciaservicio.CatVigenciaServicioResponse;
import mx.gob.sat.catalogo.model.entity.CatVigenciaServicio;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatVigenciaServicioMapper {
    @Mapping(source = "idBloque.idTratadoAcuerdo", target = "idBloque")
    @Mapping(source = "cveCriterioOrigen.cveCriterioOrigen", target = "cveCriterioOrigen")
    CatVigenciaServicioResponse toResponse(CatVigenciaServicio entity);
    List<CatVigenciaServicioResponse> toResponseList(List<CatVigenciaServicio> entities);
}
