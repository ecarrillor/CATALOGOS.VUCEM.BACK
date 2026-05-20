package mx.gob.sat.catalogo.util.mapper;
import mx.gob.sat.catalogo.controller.response.catalogodtr.CatCatalogoDTrResponse;
import mx.gob.sat.catalogo.model.entity.CatCatalogoDTr;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface CatCatalogoDTrMapper {
    @Mapping(source = "id.cveCatalogo", target = "cveCatalogo")
    @Mapping(source = "id.cveLenguaje", target = "cveLenguaje")
    CatCatalogoDTrResponse toResponse(CatCatalogoDTr entity);
    List<CatCatalogoDTrResponse> toResponseList(List<CatCatalogoDTr> entities);
}
