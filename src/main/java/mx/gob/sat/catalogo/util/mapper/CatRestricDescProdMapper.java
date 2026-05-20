package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.restricdescprod.CatRestricDescProdResponse;
import mx.gob.sat.catalogo.model.entity.CatRestricDescProd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatRestricDescProdMapper {
    @Mapping(source = "idRestriccionTtra.idRestriccionTtra", target = "idRestriccionTtra")
    @Mapping(source = "idDescripcionProd.idDescripcionProd", target = "idDescripcionProd")
    CatRestricDescProdResponse toResponse(CatRestricDescProd entity);
    List<CatRestricDescProdResponse> toResponseList(List<CatRestricDescProd> entities);
}
