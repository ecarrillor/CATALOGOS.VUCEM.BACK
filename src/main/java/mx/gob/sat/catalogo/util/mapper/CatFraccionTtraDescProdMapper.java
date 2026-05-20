package mx.gob.sat.catalogo.util.mapper;

import mx.gob.sat.catalogo.controller.response.fraccionttradescrprod.CatFraccionTtraDescProdResponse;
import mx.gob.sat.catalogo.model.entity.CatFraccionTtraDescProd;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CatFraccionTtraDescProdMapper {
    @Mapping(source = "idDescripcionProd.idDescripcionProd", target = "idDescripcionProd")
    @Mapping(source = "idFraccionGob.idFraccionGob", target = "idFraccionGob")
    CatFraccionTtraDescProdResponse toResponse(CatFraccionTtraDescProd entity);
    List<CatFraccionTtraDescProdResponse> toResponseList(List<CatFraccionTtraDescProd> entities);
}
