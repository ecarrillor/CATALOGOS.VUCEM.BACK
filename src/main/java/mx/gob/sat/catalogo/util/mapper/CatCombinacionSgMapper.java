package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.combinacionsg.CatCombinacionSgResponse;
import mx.gob.sat.catalogo.model.entity.CatCombinacionSg;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatCombinacionSgMapper {
    @Mapping(source = "cvcEspecie.cveCatalogo", target = "cvcEspecie")
    @Mapping(source = "cvcFuncionZootecnica.cveCatalogo", target = "cvcFuncionZootecnica")
    @Mapping(source = "cvcNombreComun.cveCatalogo", target = "cvcNombreComun")
    @Mapping(source = "cvcTipoProducto.cveCatalogo", target = "cvcTipoProducto")
    @Mapping(source = "cvePais.cvePais", target = "cvePais")
    CatCombinacionSgResponse toResponse(CatCombinacionSg entity);
    List<CatCombinacionSgResponse> toResponseList(List<CatCombinacionSg> entities);
}
