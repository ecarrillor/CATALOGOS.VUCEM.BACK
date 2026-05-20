package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.actividadeconomicasat.CatActividadEconomicaSatResponse;
import mx.gob.sat.catalogo.model.entity.CatActividadEconomicaSat;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatActividadEconomicaSatMapper {

    @Mapping(source = "idActividadEconomicaR.idActividadEconomicaSat", target = "idActividadEconomicaR")
    @Mapping(source = "cveTipoEmpresaRecif.cveTipoEmpresaRecif", target = "cveTipoEmpresaRecif")
    CatActividadEconomicaSatResponse toResponse(CatActividadEconomicaSat entity);

    List<CatActividadEconomicaSatResponse> toResponseList(List<CatActividadEconomicaSat> entities);
}
