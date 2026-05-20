package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.equivalenciaaelc.CatEquivalenciaAelcResponse;
import mx.gob.sat.catalogo.model.entity.CatEquivalenciaAelc;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatEquivalenciaAelcMapper {
    @Mapping(source = "id.fecIniVigencia", target = "fecIniVigencia")
    @Mapping(source = "id.cveMoneda", target = "cveMoneda")
    CatEquivalenciaAelcResponse toResponse(CatEquivalenciaAelc entity);
    List<CatEquivalenciaAelcResponse> toResponseList(List<CatEquivalenciaAelc> entities);
}
